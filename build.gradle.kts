plugins {
    id("java")
    id("com.diffplug.eclipse.apt") version "3.26.0"
    id("com.diffplug.spotless") version "5.8.2"
    id("org.springframework.boot") version "2.4.1"
    id("org.seasar.doma.compile") version "1.1.0"
}

apply(plugin = "io.spring.dependency-management")

spotless {
    java {
        googleJavaFormat("1.7")
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

springBoot {
    mainClass.set("sample.Application")
}

repositories {
    mavenCentral()
    mavenLocal()
    maven(url = "https://repo.spring.io/milestone")
}

dependencies {
    val domaVersion: String by project
    val domaSpringBootVersion: String by project
    annotationProcessor("org.seasar.doma:doma-processor:${domaVersion}")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.seasar.doma:doma-core:${domaVersion}")
    implementation("org.seasar.doma.boot:doma-spring-boot-starter:${domaSpringBootVersion}")
    implementation("com.h2database:h2:1.4.200")
    implementation("org.webjars:jquery:3.5.1")
    implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect:2.5.1")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

eclipse {
    classpath {
        file {
            whenMerged {
                val classpath = this as org.gradle.plugins.ide.eclipse.model.Classpath
                classpath.entries.removeAll {
                    when (it) {
                        is org.gradle.plugins.ide.eclipse.model.Output -> it.path == ".apt_generated"
                        else -> false
                    }
                }
            }
            withXml {
                val node = asNode()
                node.appendNode("classpathentry", mapOf("kind" to "src", "output" to "bin/main", "path" to ".apt_generated"))
            }
        }
    }
    jdt {
        javaRuntimeName = "JavaSE-11"
    }
}

tasks {
    test {
        useJUnitPlatform()
    }
}