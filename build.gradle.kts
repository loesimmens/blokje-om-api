plugins {
	alias(libs.plugins.kotlinJvm)
	alias(libs.plugins.spring)
	alias(libs.plugins.springBoot)
	alias(libs.plugins.springDependencyManagement)
	alias(libs.plugins.jpa)
	alias(libs.plugins.xsd2java)
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
	implementation(libs.jacksonKotlinModule)
	implementation(libs.jacksonDataFormatXML)
	implementation(libs.postgresql)
	implementation(libs.flyway)
	implementation(libs.h2)

	testImplementation(libs.springBootStarterTest)
	testImplementation(libs.kotlinTestJunit5)
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

xsd2java {
	schemas {
		create("boardgames") {
			schemaDirPath = file("src/main/resources/xsd").toPath()
			packageName = "com.boardgamegeek.xmlapi.boardgames"
		}
	}

	arguments = listOf("-verbose")
	outputDir = file("${project.layout.buildDirectory.get()}/generated-sources/xsd2java")
}
