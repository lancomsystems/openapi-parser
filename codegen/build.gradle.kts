plugins {
    application
    kotlin("jvm") version "1.9.22"
}

dependencies {
    implementation("com.github.spullara.mustache.java:compiler:0.9.11")
}

kotlin {
    jvmToolchain(11)
}

val wrapper by tasks.creating
val prepareKotlinBuildScriptModel by tasks.creating

application {
    mainClass = "de.lancom.openapi.codegen.CodegenKt"
}
