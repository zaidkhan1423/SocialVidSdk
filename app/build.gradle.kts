plugins {
    id("com.android.application")
    kotlin("kapt")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.zaid.socialvidsdk"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.zaid.socialvidsdk"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("org.chromium.net:cronet-api:119.6045.31")
    implementation("androidx.media3:media3-datasource-cronet:1.3.0")
    implementation("com.google.android.datatransport:transport-runtime:3.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //Compose navigation
    val navVersion = "2.7.7"
    implementation ("androidx.navigation:navigation-compose:$navVersion")

    //Lifecycle aware compose state
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")

    //Hilt
    val hiltVersion = "2.49"
    implementation ("com.google.dagger:hilt-android:$hiltVersion")
    kapt ("com.google.dagger:hilt-android-compiler:$hiltVersion")
    implementation ("androidx.hilt:hilt-navigation-compose:1.2.0")

    //Retrofit
    val retrofitVersion = "2.9.0"
    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-moshi:$retrofitVersion")

    //Moshi
    implementation ("com.squareup.moshi:moshi-kotlin:1.14.0")

    //Okhttp
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3")

    //Coil
    implementation ("io.coil-kt:coil-compose:2.4.0")

    //ExoPlayer
    val mediaVersion = "1.3.0"
    implementation ("androidx.media3:media3-exoplayer:$mediaVersion")
    implementation ("androidx.media3:media3-exoplayer-hls:$mediaVersion")
    implementation ("androidx.media3:media3-ui:$mediaVersion")

    // Diff Utils
    implementation ("io.github.java-diff-utils:java-diff-utils:4.12")

    //Paging
    implementation("androidx.paging:paging-runtime-ktx:3.2.1")
    implementation("androidx.paging:paging-compose:3.3.0-alpha05")

    //Accompanist- Pager layouts
    implementation ("com.google.accompanist:accompanist-pager:0.25.1")

}

kapt {
    correctErrorTypes = true
}