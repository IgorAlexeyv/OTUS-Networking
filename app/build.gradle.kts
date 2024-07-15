plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlinSerialization)
    id("kotlin-kapt")
    alias(libs.plugins.hilt)
}

kapt {
    correctErrorTypes = true
}

android {
    namespace = "otus.gbp.networking"
    compileSdk = 35

    defaultConfig {
        applicationId = "otus.gbp.networking"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.kotlin.dateTime)
    implementation(libs.kotlin.serialization.core)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.coroutines.android)

    implementation(libs.net.okhttp)
    implementation(libs.net.okhttp.logging)
    implementation(libs.net.retrofit)
    implementation(libs.net.retrofit.kotlin)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.lifecycle.viewmodel)

    testImplementation(libs.junit.junit)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlin.mockito)
    testImplementation(libs.kotlin.coroutines.test)
    testImplementation(libs.androidx.testing)
}