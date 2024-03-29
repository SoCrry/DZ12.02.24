plugins {
    kotlin("jvm") version "1.9.22"
}

group = "ru.netology"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation ("junit:junit:4.13.2")
}


kotlin {
    jvmToolchain(17)
}