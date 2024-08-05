## [2.1.0](https://github.com/lancomsystems/openapi-parser/compare/v2.0.2...v2.1.0) (2024-08-05)


### New

* added float and binary to SchemaFormat ([df78317](https://github.com/lancomsystems/openapi-parser/commit/df783175c239a5d0e83b70d6d61c5109f02fe778))
* added OpenApi.sort ([ef6cb9b](https://github.com/lancomsystems/openapi-parser/commit/ef6cb9b96292877d29552928f4cf8bc5918a351f))
* added support for ArrayNode Extensions ([7c1b241](https://github.com/lancomsystems/openapi-parser/commit/7c1b241ade43b2a44259f0f017f27404f08a6069))
* added support for TextNode Extensions ([a729dc4](https://github.com/lancomsystems/openapi-parser/commit/a729dc4abfa2b17d617c60dd57b2b550ffefd14f))
* added toString to references ([9ee355c](https://github.com/lancomsystems/openapi-parser/commit/9ee355c943ffed693b2dee2afc87e061e8d394e5))


### Bug Fixes

* fixed operationId generation ([c1eafd1](https://github.com/lancomsystems/openapi-parser/commit/c1eafd1106649e1a9de19d6f1fc0665450ecb873))
* removed full references ([f0e0b31](https://github.com/lancomsystems/openapi-parser/commit/f0e0b31dcd5e3cd78334b4a40b74e05fb49ec504))


### Dependency Updates

* Update dependency com.github.spullara.mustache.java:compiler to v0.9.14 ([f4569c9](https://github.com/lancomsystems/openapi-parser/commit/f4569c931b52fb0d269545e38a93c7b42f17ed45))
* Update dependency gradle to v8.9 ([d8eec04](https://github.com/lancomsystems/openapi-parser/commit/d8eec04e9a840bac9503f87d3455c1d5bf338f87))
* Update gradle/actions action to v4 ([9995f6e](https://github.com/lancomsystems/openapi-parser/commit/9995f6e91d7fa128c49dfdeee72df55cdc05b056))
* Update junit5 monorepo to v5.10.3 ([d7bd715](https://github.com/lancomsystems/openapi-parser/commit/d7bd715e545e4dd5783435d96f1ef4f263a5dea3))
* Update plugin org.jetbrains.kotlin.jvm to v2 ([b6866c8](https://github.com/lancomsystems/openapi-parser/commit/b6866c8def9d005347a24c341400a6f356ed9674))

## [2.0.2](https://github.com/lancomsystems/openapi-parser/compare/v2.0.1...v2.0.2) (2024-06-06)


### Bug Fixes

* fixed parsing of "multipleOf" ([adb56fa](https://github.com/lancomsystems/openapi-parser/commit/adb56fad8dda37f28f5bdef4a3d26d46a1d3aeb0))


### Dependency Updates

* Update dependency gradle to v8.8 ([14d30b0](https://github.com/lancomsystems/openapi-parser/commit/14d30b084446e7a90e5dc7eb07c377e77dd05a9e))

## [2.0.1](https://github.com/lancomsystems/openapi-parser/compare/v2.0.0...v2.0.1) (2024-05-31)


### Bug Fixes

* added support for securitySchemes in CleanupUnusedComponents ([b44b25a](https://github.com/lancomsystems/openapi-parser/commit/b44b25a36b1d06917c4e159459c6a3a79a614bf2))

## [2.0.0](https://github.com/lancomsystems/openapi-parser/compare/v1.1.0...v2.0.0) (2024-05-23)


### Breaking Changes

* added simple openapi classes ([2be186c](https://github.com/lancomsystems/openapi-parser/commit/2be186cb2e47ec7df5be22531bed7e3c7946b159))


### New

* added cleanupUnusedComponents ([f5ca98e](https://github.com/lancomsystems/openapi-parser/commit/f5ca98ec96391673f50fbde2154aedc2303399bf))


### Dependency Updates

* Update dependency com.github.spullara.mustache.java:compiler to v0.9.13 ([7aca52d](https://github.com/lancomsystems/openapi-parser/commit/7aca52d598ecaad3c6d707edd6b9c74bc4f84c7e))
* Update dependency gradle to v8.7 ([4ef9469](https://github.com/lancomsystems/openapi-parser/commit/4ef9469de7c22c933d254d721b971093fe6a696b))
* Update gradle/wrapper-validation-action action to v3 ([51b0908](https://github.com/lancomsystems/openapi-parser/commit/51b0908ac6374f62622d0813db8fb7488320d9ab))
* Update junit5 monorepo to v5.10.2 ([069b6a0](https://github.com/lancomsystems/openapi-parser/commit/069b6a0ba76e27b33316db39300b82e124e23d38))
* Update plugin io.github.gradle-nexus.publish-plugin to v2 ([5d73680](https://github.com/lancomsystems/openapi-parser/commit/5d73680b7d500bae00f29ceb924534925e289c81))
* Update plugin org.jetbrains.kotlin.jvm to v1.9.24 ([3c3f823](https://github.com/lancomsystems/openapi-parser/commit/3c3f823fd2121627554916439ca2a045b3bf6ce7))

## [1.1.0](https://github.com/lancomsystems/openapi-parser/compare/v1.0.2...v1.1.0) (2024-01-22)


### New

* refactored mergeOpenApi() ([b1bbcc1](https://github.com/lancomsystems/openapi-parser/commit/b1bbcc1861ae63706d103727c26216e3476c755e))


### Refactorings

* fixed compiler warning ([495528a](https://github.com/lancomsystems/openapi-parser/commit/495528a6736f8aed3a67f5ad17660ae23827fcb4))

## [1.0.2](https://github.com/lancomsystems/openapi-parser/compare/v1.0.1...v1.0.2) (2024-01-11)


### Dependency Updates

* Update com.fasterxml.jackson to v2.16.1 ([f1155f1](https://github.com/lancomsystems/openapi-parser/commit/f1155f12455f0904592beac408b9460d851997fb))


### Build System Improvements

* use io.github.gradle-nexus.publish-plugin ([d27de1e](https://github.com/lancomsystems/openapi-parser/commit/d27de1e12c1d6598e38b8b35ccc718159a3187e8))

## [1.0.1](https://github.com/lancomsystems/openapi-parser/compare/v1.0.0...v1.0.1) (2024-01-10)


### Dependency Updates

* Update actions/setup-java action to v4 ([3814045](https://github.com/lancomsystems/openapi-parser/commit/3814045cc7523ddb54b11e67d906b2f94116e276))
* Update plugin org.jetbrains.kotlin.jvm to v1.9.22 ([05c9bb5](https://github.com/lancomsystems/openapi-parser/commit/05c9bb5c3b18ff2181e49b6753828d95af3a1d14))

## 1.0.0 (2024-01-10)


### New

* released first version of openapi-parser ([601a9c1](https://github.com/lancomsystems/openapi-parser/commit/601a9c1634f41870412f142885907cdc24362198))
