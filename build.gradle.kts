plugins {
    kotlin("jvm") version "1.9.21"
}

kotlin {
    jvmToolchain(11)
}

val libraryVersion: String by project

group = "de.lancom.openapi"
version = libraryVersion

dependencies {
}
