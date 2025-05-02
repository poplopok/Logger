plugins {
    alias(libs.plugins.kotlin.jvm)
    `maven-publish`
    kotlin("plugin.serialization") version "1.9.0"
    `java-library`
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            // Задайте groupId, artifactId и version
            groupId = "com.mad"               // Ваш groupId
            artifactId = "my-kotlin-library"  // Ваш artifactId
            version = "1.0.0"                 // Версия библиотеки
        }
    }
    repositories {
        mavenLocal()  // Публикуем в локальный репозиторий (или настройте публикацию на удаленный репозиторий)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("redis.clients:jedis:4.4.3")

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    api(libs.commons.math3)
    implementation(libs.guava)
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<Jar> {
    manifest {
        attributes["Implementation-Title"] = "My Kotlin Library"
        attributes["Implementation-Version"] = "1.0.0"
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}
