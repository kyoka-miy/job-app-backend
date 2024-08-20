plugins {
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
	id("org.springframework.boot") version "3.3.2"
	id("io.spring.dependency-management") version "1.1.6"
	id("org.flywaydb.flyway") version "8.0.1"
	id("nu.studer.jooq") version "7.1.1"
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
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	jooqGenerator("com.mysql:mysql-connector-j")
	jooqGenerator("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")
}

flyway {
	url = "jdbc:mysql://localhost:3307/job_app?autoReconnect=true&allowPublicKeyRetrieval=true&useSSL=false"
	user = "user"
	password = "password"
}

jooq {
	configurations {
		create("main") {
			jooqConfiguration.apply {
				jdbc.apply {
					url = System.getenv("JDBC_DATABASE_URL") ?: "jdbc:mysql://localhost:3307/job_app?autoReconnect=true&allowPublicKeyRetrieval=true&useSSL=false"
					user = System.getenv("JDBC_DATABASE_USERNAME") ?: "user"
					password = System.getenv("JDBC_DATABASE_PASSWORD") ?: "password"
				}
				generator.apply {
					name = "org.jooq.codegen.KotlinGenerator"
					database.apply {
						name = "org.jooq.meta.mysql.MySQLDatabase"
						inputSchema = "dzjfxl0e6xe15m02"
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
