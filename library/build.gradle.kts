plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.healthybear.library"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = 24
        ndk {
            abiFilters += listOf("armeabi, armeabi-v7a", "arm64-v8a", "x86", "x86_64")
        }
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
//        compose = true
        dataBinding = true
        viewBinding = true
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    api(libs.retrofit)
    api(libs.retrofit.adapters)
    api(libs.retrofit.converter.moshi)
    implementation(libs.logging.interceptor)
    api(libs.moshi)
    api(libs.moshi.adapters)
    api(libs.moshi.kotlin)
    api(libs.coil)
    api(libs.coil.gif)
    api(libs.coil.svg)
    api(libs.coil.video)
    api(libs.core)
    api(libs.color)
    api(libs.input)
    api(libs.files)
    api(libs.datetime)
    api(libs.bottomsheets)
    api(libs.lifecycle)
    ksp(libs.moshi.kotlin.codegen)
    implementation(libs.okhttp)
    api(libs.eventbus)
    api(libs.glide)
    ksp(libs.glide.compiler)
    api(libs.lottie)
    api(libs.crashreport)
    api(libs.utilcodex)
    implementation(libs.mmkv)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}