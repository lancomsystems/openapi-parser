# OpenAPI 3.0 Parser in Kotlin

Latest Version: 1.0.2

## Project Description

This open-source project provides an OpenAPI 3.0 Parser implemented in Kotlin, utilizing immutable data classes. In contrast to [Swagger Parser](https://github.com/swagger-api/swagger-parser), this parser employs immutable data classes for clearer and safer data manipulation.

## License

This project is licensed under the Apache License 2.0. See the [License file](LICENSE) for details.

## Features

- **OpenAPI 3.0 Parsing:** The parser allows reading of OpenAPI 3.0 specification files into kotlin data classes.
- **Serialisation:** Allows to convert OpenAPI 3.0 entities into YAML or JSON.
- **Immutable Data Classes:** Instead of mutable data structures, like in swagger-parser, immutable data classes are used to ensure code safety and clarity.
- **OpenAPI as Code:** Allows you to define OpenAPI 3.0 Entities in Kotlin (or Java) Code.
- **Automatic Code Generation:** The majority of the code is automatically generated to ensure code maintainability and consistency.
- **Nullable** The Implementation uses a Class called `Field<T>` to handle attributes that are set to `null` and to distinguish them from attributes that are not set

## Unsupported Features

- **OpenAPI Validation:** Validation is currently not supported.
- **Conversation from/to swagger-parser:** You can simply take an intermediate step via YAML/JSON.
- **Kotlin Multiplatform:** Currently, only JVM is supported, but adding other Platforms should be easy.
- **Other YAML/JSON Libraries:** Due to the automatic code generation, it would be easy to create interfaces for a basic implementation as well as different data classes for different YAML/JSON implementations. There is already a wrapper class (`Wrapper`)

Feel free to contribute and extend the project, merge requests are very welcome!

## Code Generation
The majority of the code is automatically generated, and you can update the generated code using the command `./gradlew codegen:run`.
You can find the Code Generation in Folder `codegen/`, the generated classes can be found in `src/generated/kotlin` and the generated unit tests in `src/generatedtest/kotlin`.
Code Generation is implemented using [Mustache Templates](https://github.com/spullara/mustache.java), the templates can be found in Folder `codegen/src/main/resources/templates`.

## Installation

To use the project, you can include it in your Gradle or Maven project as follows:

### Gradle

`build.gradle`
```gradle
implementation 'io.github.lancomsystems.openapi.parser:openapi-parser:1.0.2'
```

`build.gradle.kts`
```gradle
implementation("io.github.lancomsystems.openapi.parser:openapi-parser:1.0.2")'
```

### Maven
```maven
<dependency>
    <groupId>io.github.lancomsystems.openapi.parser</groupId>
    <artifactId>openapi-parser</artifactId>
    <version>1.0.2</version>
</dependency>
```
