plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    //id("com.didi.dokit")
}

android {
    namespace = "com.libill.demos"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.libill.demos"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        kapt {
            arguments {
                arg("AROUTER_MODULE_NAME", project.name)
            }
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        aidl = true
        viewBinding = true
    }

}

dependencies {
    implementation(fileTree("libs"))
    //implementation(project(":base"))
    implementation(project(":testJ"))
    implementation(project(":testcode"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(files("src/main/libs/test.jar"))
    implementation(files("src/main/libs/testJ.jar"))

    implementation(platform(libs.okhttp3.bom))
    implementation(libs.okhttp3)
    implementation(libs.okhttp3.logging.interceptor)

    implementation(libs.glide)
    implementation(libs.lifecycleProcess)
    implementation(libs.kpermissions)

    implementation(libs.arouter)
    kapt(libs.arouterCompiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    debugImplementation(libs.dokitx)
    debugImplementation("com.android.volley:volley:1.2.0")
}