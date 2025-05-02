plugins {
    kotlin("jvm") version "1.9.0" // убираем alias — он локален, JitPack его не поймёт
    kotlin("plugin.serialization") version "1.9.0"
    `java-library`
    `maven-publish`
}

group = "com.github.poplopok" // ← ОБЯЗАТЕЛЬНО для JitPack!
version = "1.0.6"             // ← Должен совпадать с Git-тегом

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "com.github.poplopok"       // ← для JitPack
            artifactId = "logger"                 // ← название библиотеки
            version = "1.0.6"                     // ← как Git-тег
        }
    }
}

repositories {
    mavenCentral()
    maven("https://jitpack.io") // ← если используешь сторонние либы через JitPack
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    implementation("redis.clients:jedis:4.4.3")
    implementation("org.apache.commons:commons-math3:3.6.1") // напрямую вместо alias
    implementation("com.google.guava:guava:32.1.2-jre")       // напрямую

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.10.2")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<Jar> {
    manifest {
        attributes["Implementation-Title"] = "My Kotlin Library"
        attributes["Implementation-Version"] = version
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
