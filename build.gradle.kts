plugins {
    kotlin("jvm") version "1.9.25"
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
    `java-library`
    `maven-publish`
    signing
}

kotlin {
    jvmToolchain(11)
}

java {
    withJavadocJar()
    withSourcesJar()
}

val libraryVersion: String by project

group = "io.github.lancomsystems.openapi.parser"
version = libraryVersion

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.16.1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.16.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")
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

val mavenUsername = System.getenv("MAVEN_USERNAME")
val mavenPassword = System.getenv("MAVEN_PASSWORD")
if (mavenUsername != null && mavenPassword != null) {
    nexusPublishing {
        repositories {
            sonatype {
                nexusUrl = uri("https://s01.oss.sonatype.org/service/local/")
                snapshotRepositoryUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                username = mavenUsername
                password = mavenPassword
            }
        }
    }
} else if (mavenUsername != null || mavenPassword != null) {
    throw GradleException("missing MAVEN_USERNAME or MAVEN_PASSWORD!")
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
