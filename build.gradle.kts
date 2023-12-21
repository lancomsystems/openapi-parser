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
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.16.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.16.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.1")
}

sourceSets {
    main {
        kotlin {
            srcDirs("src/generated/kotlin")
        }
    }
    test {
        kotlin {
            srcDirs("src/generatedtest/kotlin")
        }
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
