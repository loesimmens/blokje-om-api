plugins {
	alias(libs.plugins.kotlinJvm)
	alias(libs.plugins.spring)
	alias(libs.plugins.springBoot)
	alias(libs.plugins.springDependencyManagement)
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
	implementation(libs.springBootStarter)
	implementation(libs.kotlinReflect)
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
