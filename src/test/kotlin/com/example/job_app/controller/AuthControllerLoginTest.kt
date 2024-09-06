package com.example.job_app.controller

import com.example.job_app.TestConfig
import com.example.job_app.domain.account.Account
import com.example.job_app.domain.account.AccountRepository
import com.example.job_app.domain.account.Role
import com.example.job_app.presentation.controller.LoginRequest
import com.example.job_app.usecase.jwt.JwtService
import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.assertions.json.shouldEqualJson
import io.kotest.matchers.shouldBe
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
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
internal class AuthControllerLoginTest(
    @Autowired private val mockMvc: MockMvc,
    @Autowired private val objectMapper: ObjectMapper,
    @Autowired private val accountRepository: AccountRepository,
    @Autowired private val passwordEncoder: PasswordEncoder,
    @Autowired @MockBean
    private val jwtService: JwtService
) {
    @Test
    fun loginSuccessfully() {
        val testAccount = Account(
            email = "test@mail.com",
            name = "Toy",
            _password = passwordEncoder.encode("password"),
            registeredDatetime = LocalDateTime.now(),
            role = Role.USER
        )
        accountRepository.insert(testAccount)

        val request = LoginRequest(
            email = "test@mail.com",
            password = "password"
        )
        whenever(jwtService.generateToken(any())) doReturn "jwt-token"

        val response = mockMvc.perform(
            MockMvcRequestBuilders.get("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isOk)
            .andReturn().response.let {
                it.characterEncoding = "UTF-8"
                it.contentAsString
            }

        response shouldBe "jwt-token"
    }

    @Test
    fun wrongPassword() {
        val testAccount = Account(
            email = "test@mail.com",
            name = "Toy",
            _password = passwordEncoder.encode("password"),
            registeredDatetime = LocalDateTime.now(),
            role = Role.USER
        )
        accountRepository.insert(testAccount)

        val request = LoginRequest(
            email = "test@mail.com",
            password = "wrongpassword"
        )

        val response = mockMvc.perform(
            MockMvcRequestBuilders.get("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isBadRequest)
            .andReturn().response.let {
                it.characterEncoding = "UTF-8"
                it.contentAsString
            }

        response shouldEqualJson """{
                | "errorCode": "login.wrongPassword",
                | "message": "Wrong password"
                |}
        """.trimMargin()
    }

    @Test
    fun unregisteredEmail() {
        val request = LoginRequest(
            email = "test@mail.com",
            password = "wrongpassword"
        )

        val response = mockMvc.perform(
            MockMvcRequestBuilders.get("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isBadRequest)
            .andReturn().response.let {
                it.characterEncoding = "UTF-8"
                it.contentAsString
            }

        response shouldEqualJson """{
                | "errorCode": "login.emailNotFound",
                | "message": "Email not found"
                |}
        """.trimMargin()
    }
}
