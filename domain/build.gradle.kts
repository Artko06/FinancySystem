plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

dependencies {
    val kotlinStandardLibVersion = "2.0.21"
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinStandardLibVersion") // Стандартная библиотека Kotlin

    val coroutinesLibVersion = "1.7.3"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesLibVersion") // Корутины

    val versionBCrypt = "0.4"
    implementation("org.mindrot:jbcrypt:$versionBCrypt")
}