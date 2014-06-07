JPet Store Sample
========================================

This sample shows how easy to integrate [Doma][doma] with  [Spring Boot][spring-boot] .

And this sample is useful as reference to combine following libraries.

- Spring Security 
- Thymeleaf
- Hibernate Validator

Java 8 is needed to run this sample.

Clone
--------

```sh
git clone https://github.com/domaframework/spring-boot-jpetstore.git
```

Run
--------

```sh
cd spring-boot-jpetstore
```

```sh
./gradlew bootRun
```

Access
--------

```
http://localhost:8080/
```

Edit with Eclipse
--------

### About Eclipse Version

Use Kepler SR2 (4.3.2) and a feature patch.

For further details ,  see https://wiki.eclipse.org/JDT/Eclipse_Java_8_Support_For_Kepler

### Generate all Eclipse files

```sh
./gradlew eclipse
```

License
-------

Apache License, Version 2.0

[doma]: https://github.com/domaframework/doma
[spring-boot]: https://github.com/spring-projects/spring-boot

