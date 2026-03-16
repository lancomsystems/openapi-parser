plugins {
    kotlin("jvm") version "2.0.0"
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
    `java-library`
    `maven-publish`
    signing
}

kotlin {
    jvmToolchain(21)
}

java {
    withJavadocJar()
    withSourcesJar()
}

val libraryVersion: String by project

group = "io.github.lancomsystems.openapi.parser"
version = libraryVersion

dependencies {
    implementation("tools.jackson.core:jackson-databind:3.0.4")
    implementation("tools.jackson.dataformat:jackson-dataformat-yaml:3.0.4")
    implementation("tools.jackson.module:jackson-module-kotlin:3.0.4")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.3")
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

val sonatypeUsername = System.getenv("SONATYPE_USERNAME") ?: System.getenv("MAVEN_USERNAME")
val sonatypePassword = System.getenv("SONATYPE_PASSWORD") ?: System.getenv("MAVEN_PASSWORD")
if (sonatypeUsername != null && sonatypePassword != null) {
    nexusPublishing {
        repositories {
            sonatype {
                // Route through Central Portal's OSSRH Staging API compatibility service.
                nexusUrl = uri("https://ossrh-staging-api.central.sonatype.com/service/local/")
                snapshotRepositoryUrl = uri("https://central.sonatype.com/repository/maven-snapshots/")
                username = sonatypeUsername
                password = sonatypePassword
            }
        }
    }
} else if (sonatypeUsername != null || sonatypePassword != null) {
    throw GradleException(
        "missing SONATYPE_USERNAME or SONATYPE_PASSWORD (legacy MAVEN_USERNAME/MAVEN_PASSWORD is still supported)!",
    )
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "openapi-parser"
            from(components["java"])
            pom {
                name = "openapi-parser"
                description =
                    "This open-source project provides an OpenAPI 3.0 Parser implemented in Kotlin, utilizing immutable data classes"
                url = "https://github.com/lancomsystems/openapi-parser"
                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }
                developers {
                    developer {
                        id = "drieks"
                        name = "Dennis Rieks"
                        email = "dennis.rieks@lancom.de"
                    }
                }
                scm {
                    connection = "scm:git:git://git@github.com:lancomsystems/openapi-parser.git"
                    url = "https://github.com/lancomsystems/openapi-parser"
                }
            }
        }
    }
}

signing {
    Triple(
        System.getenv("GPG_SIGNING_KEY_ID"),
        System.getenv("GPG_SIGNING_KEY"),
        System.getenv("GPG_SIGNING_PASSWORD"),
    ).let { (id, key, passwd) ->
        if (id != null && key != null && passwd != null) {
            useInMemoryPgpKeys(id, key, passwd)
            sign(publishing.publications)
        } else if (id != null || key != null || passwd != null) {
            throw GradleException("missing sign id, key or passwd!")
        }
    }
}

tasks.javadoc {
    (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
}
