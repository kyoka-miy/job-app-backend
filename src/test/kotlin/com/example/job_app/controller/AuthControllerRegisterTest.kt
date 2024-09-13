package com.example.job_app.controller

import com.example.job_app.TestConfig
import com.example.job_app.domain.account.Account
import com.example.job_app.domain.account.AccountRepository
import com.example.job_app.domain.account.Role
import com.example.job_app.presentation.controller.AccountCreateRequest
import com.example.job_app.usecase.jwt.JwtService
import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.assertions.json.shouldEqualJson
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import jakarta.transaction.Transactional
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime

@Import(TestConfig::class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
internal class AuthControllerRegisterTest(
    @Autowired private val mockMvc: MockMvc,
    @Autowired private val objectMapper: ObjectMapper,
    @Autowired private val accountRepository: AccountRepository,
    @Autowired @MockBean
    private val passwordEncoder: PasswordEncoder,
    @Autowired @MockBean
    private val jwtService: JwtService
) {

    private val datetime: LocalDateTime = LocalDateTime.of(2024, 1, 1, 0, 0, 0)

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun registerUserSuccessfully() {
        val request = AccountCreateRequest(
            email = "test@mail.com",
            name = "username",
            password = "password"
        )
        whenever(jwtService.generateToken(any())) doReturn "jwt-token"
        whenever(passwordEncoder.encode(any())) doReturn "encode-password"
        val mock = Mockito.mockStatic(LocalDateTime::class.java, Mockito.CALLS_REAL_METHODS)
        mock.`when`<Any> { LocalDateTime.now() }.thenReturn(datetime)

        val response = mockMvc.perform(
            MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isOk)
            .andReturn().response.let {
                it.characterEncoding = "UTF-8"
                it.contentAsString
            }

        response shouldBe "jwt-token"

        accountRepository.findByEmail("test@mail.com").shouldNotBeNull().should {
            it.email shouldBe "test@mail.com"
            it.name shouldBe "username"
            it.role shouldBe Role.USER
            it.password shouldBe "encode-password"
            it.registeredDatetime shouldBe LocalDateTime.of(2024, 1, 1, 0, 0, 0)
        }
    }

    @Test
    fun emailIsAlreadyRegistered() {
        val testAccount = Account(
            email = "test@mail.com",
            name = "Toy",
            _password = "password",
            registeredDatetime = LocalDateTime.now(),
            role = Role.USER
        )
        accountRepository.insert(testAccount)
        val request = AccountCreateRequest(
            email = "test@mail.com",
            name = "username",
            password = "password"
        )
        val response = mockMvc.perform(
            MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isBadRequest)
            .andReturn().response.let {
                it.characterEncoding = "UTF-8"
                it.contentAsString
            }

        response shouldEqualJson """{
                | "errorCode": "accountRegister.emailDuplicate",
                | "message": "This email is already registered"
                |}
        """.trimMargin()
    }

    @Test
    fun validationError() {
        val request = AccountCreateRequest(
            email = "invalidmail",
            name = "username",
            password = "password"
        )
        val response = mockMvc.perform(
            MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isBadRequest)
            .andReturn().response.let {
                it.characterEncoding = "UTF-8"
                it.contentAsString
            }

        response shouldEqualJson """{
                | "errorCode": "invalidRequest",
                | "message": "Invalid email format"
                |}
        """.trimMargin()
    }
}
