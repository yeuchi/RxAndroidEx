plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id ("dagger.hilt.android.plugin")
    id ("kotlin-kapt")
}

android {
    namespace = "com.ctyeung.rxandroidex"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ctyeung.rxandroidex"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    implementation ("com.google.android.material:material:1.3.0-alpha02")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.6.0")
    implementation ("com.google.code.gson:gson:2.8.5")
    implementation ("com.squareup.retrofit2:converter-gson:2.5.0")

    //JakeWharton - RxBinding
    //implementation "com.jakewharton.rxbinding2:rxbinding-appcompat-v7:2.1.1"
    implementation ("com.jakewharton.rxbinding2:rxbinding:2.1.1")
    implementation ("androidx.wear:wear:1.0.0")

    // rxjava
    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.3.0")
    implementation ("io.reactivex.rxjava2:rxjava:2.1.9")
    implementation ("io.reactivex.rxjava2:rxandroid:2.0.2")


    /* room */
    implementation("androidx.room:room-runtime:2.4.3")
    implementation("androidx.room:room-ktx:2.4.3")
    kapt("androidx.room:room-compiler:2.4.3")
    androidTestImplementation("androidx.room:room-testing:2.4.3")
    /* ends room */

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}