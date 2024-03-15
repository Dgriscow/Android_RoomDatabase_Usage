plugins {

    id("com.android.application")

    id("org.jetbrains.kotlin.android")

    //Implementing Kotlin Kapt
    id("kotlin-kapt")


    //Implement Kotlin Parcelize [Used to parcelize data and easier usage]
    id("kotlin-parcelize")

    //add safe navigation arguments for apps gradle
    id("androidx.navigation.safeargs.kotlin")

}

//Default Config Remains Untouched
android {
    //NEVER CHANGE APPLICATIONID/DEFAULT,
    //Changing this causes complete project(S) break, always leave as IDE/project default.
    namespace = "com.example.android_roomdatabase_usage"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.example.android_roomdatabase_usage"
        minSdk = 34 //Working Value with 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    //*NEW for view binding
    buildFeatures {
        viewBinding = true //Allows for views to use Bindings
    }

    //BuildTypes is Untouched
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
        //App Combiles with Java LTS Version 1.8
        jvmTarget = "1.8"
    }


}




dependencies {

    //Core dependencies made by THE PROJECT,
    //***DO NOT TOUCH, Will explode prject essentials if touched
    //Generally Made to just include only parts used for a bare minimum project, does not need to change.
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //ADDED DEPENDENCIES for Fragment parts
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    /*===============================================*/


    //Navigation Components Would Go here

    //The Room Database version to use, as well as the version for room database dependent values
    val room_version = "2.6.0"

    implementation("androidx.room:room-runtime:$room_version")

    annotationProcessor("androidx.room:room-compiler:$room_version")

    //Kotlin Annotation Complier (for @'s)
    kapt("androidx.room:room-compiler:$room_version")

    //Co-routine support
    implementation("androidx.room:room-ktx:$room_version")

    //Additinal Background thread task support
    implementation("androidx.room:room-rxjava2:$room_version")
    //Not included in video, good to use for support

    //support for modern google Guava
    implementation("androidx.room:room-guava:$room_version")
    //Not included in video, good to include for support


    //Android Testing implementations
    testImplementation("androidx.room:room-testing:$room_version")


    //    implementation("androidx.room:room-paging:$room_version")
    //This is implementation for Adding Pagnation if needed in a large database



    // Lifecycle components
    val lifecycle_version = "2.6.2"


    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")


    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")


    // Lifecycles only (without ViewModel or LiveData)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")


    // Saved state module for ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version")

    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycle_version")


    //Kotlin Components + Suproutines
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.72")
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")
    api ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.5")














}