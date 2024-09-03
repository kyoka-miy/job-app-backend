import org.flywaydb.gradle.task.FlywayInfoTask
import org.flywaydb.gradle.task.FlywayMigrateTask
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.flywaydb.flyway") version "8.0.1"
    id("nu.studer.jooq") version "7.1.1"
    id("org.jlleitschuh.gradle.ktlint") version "11.6.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jooq") {
        exclude(group = "org.jooq", module = "jooq")
    }

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    jooqGenerator("com.mysql:mysql-connector-j")
    jooqGenerator("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")

    implementation("org.jooq:jooq:3.16.4") // Or the specific version you're using
    implementation("org.jooq:jooq-meta:3.16.4")
    implementation("org.jooq:jooq-codegen:3.16.4")
}

ktlint {
    verbose.set(true)
    outputToConsole.set(true)
    ignoreFailures.set(false)
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
    }
    filter {
        exclude { it.file.absolutePath.contains("/generated/") }
    }
}

flyway {
    url = System.getenv("JDBC_DATABASE_URL") ?: "jdbc:mysql://localhost:3307/job_app?autoReconnect=true&allowPublicKeyRetrieval=true&useSSL=false"
    user = System.getenv("JDBC_DATABASE_USERNAME") ?: "user"
    password = System.getenv("JDBC_DATABASE_PASSWORD") ?: "password"
}
// flywayInfoとflywayMigrateのときは、classesが依存しているgenerateJooqを無効化する
// ついでにktlintFormatのときも無効にする
gradle.taskGraph.whenReady {
    if (hasTask(tasks["flywayMigrate"]) || hasTask(tasks["flywayInfo"]) || hasTask(tasks["ktlintFormat"])) {
        tasks["generateJooq"].enabled = false
    }
}
jooq {
    configurations {
        create("main") {
            jooqConfiguration.apply {
                jdbc.apply {
                    url = "jdbc:mysql://localhost:3307/job_app?autoReconnect=true&allowPublicKeyRetrieval=true&useSSL=false"
                    user = "user"
                    password = "password"
                }
                generator.apply {
                    name = "org.jooq.codegen.DefaultGenerator"
                    database.apply {
                        name = "org.jooq.meta.mysql.MySQLDatabase"
                        inputSchema = "job_app"
                        excludes = "flyway_schema_history"
                    }
                    generate.apply {
                        isDeprecated = false
                        isTables = true
                    }
                    target.apply {
                        packageName = "com.example.ktknowledgeTodo.infra.jooq"
                        directory = layout.buildDirectory.dir("generated/source/jooq/main").get().toString()
                    }
                }
            }
        }
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<BootJar> {
    archiveFileName.set("job-app.jar")
}
