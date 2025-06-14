import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.coTouchlabSkie)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    cocoapods {
        version = "1.0.0"
        summary = "KMM Shared Module"
        homepage = "https://example.com"
        ios.deploymentTarget = "13.0"
        framework {
            baseName = "shared"
            isStatic = true
        }
    }

    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.core)
            // Core HTTP client
            implementation(libs.ktor.client.core)

            // Serialization (with Kotlinx)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)

            // Logging
            implementation(libs.ktor.client.logging)

            // DateTime
            implementation(libs.kotlinx.datetime)
        }
        androidMain.dependencies {
            implementation(libs.androidx.lifecycle.viewmodel.ktx)
            implementation(libs.ktor.client.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.example.dailypulse"
    compileSdk = 35
    defaultConfig {
        minSdk = 27
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
