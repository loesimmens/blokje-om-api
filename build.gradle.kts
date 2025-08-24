plugins {
	alias(libs.plugins.kotlinJvm)
	alias(libs.plugins.spring)
	alias(libs.plugins.springBoot)
	alias(libs.plugins.springDependencyManagement)
	alias(libs.plugins.jpa)
}

group = "nl.blokje-om"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(libs.kotlinReflect)
	implementation(libs.kotlinLogging)
	implementation(libs.springBootStarter)
	implementation(libs.springBootStarterDataJpa)
	implementation(libs.springBootStarterWeb)
	implementation(libs.springBootStarterMail)
	implementation(libs.springBootStarterThymeleaf)
	implementation(libs.jacksonKotlinModule)
	implementation(libs.jacksonDataFormatXML)
	implementation(libs.postgresql)
	implementation(libs.flyway)
	implementation(libs.h2)

    developmentOnly(libs.springBootStarterDockerCompose)

	testImplementation(libs.springBootStarterTest)
	testImplementation(libs.kotlinTestJunit5)
	testImplementation(libs.mockk)
	testRuntimeOnly(libs.junitPlatformLauncher)
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

version = System.getenv("GITHUB_REF_NAME")?.replace("/", "-")?.lowercase() ?: "develop"

tasks.withType<org.springframework.boot.gradle.tasks.bundling.BootBuildImage> {
    imageName.set("ghcr.io/loesimmens/blokje-om-api:${version}")
    if (project.hasProperty("publishImage")) {
        publish.set(true)
        docker {
            publishRegistry {
                username.set(System.getenv("GITHUB_ACTOR"))
                password.set(System.getenv("GITHUB_TOKEN"))
            }
        }
    }
}
