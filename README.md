# Groom It

> This project is a part of a 2-day Hackathon conducted by Masai School.
> The idea was to build an app for small businesses where they can host their grooming shops.
> We appended features live location integration, advance booking, sending
notification to the user, grooming tips videos & shopping. 


## Built With

- Android Studio
- Kotlin
- Material UI
- Springboot
- Firebase
- Room Database
- Java
- Jetpack Navigation
- XML

##Dependencies:
dependencies {
    implementation 'com.razorpay:checkout:1.6.6'

    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    implementation 'androidx.core:core-ktx:1.5.0'
    implementation 'androidx.appcompat:appcompat:1.3.0'
    implementation 'com.google.android.material:material:1.3.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    implementation 'com.google.firebase:firebase-database-ktx:20.0.0'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    implementation 'androidx.navigation:navigation-fragment-ktx:2.3.5'
    implementation 'androidx.navigation:navigation-ui-ktx:2.3.5'
    implementation 'com.google.android.gms:play-services-maps:17.0.1'
    implementation 'com.google.android.gms:play-services-location:18.0.0'
    implementation 'com.google.firebase:firebase-storage-ktx:20.0.0'
    testImplementation 'junit:junit:4.+'

    androidTestImplementation 'androidx.test.ext:junit:1.1.2'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'
    implementation 'com.google.firebase:firebase-auth:21.0.1'
    implementation 'com.google.firebase:firebase-database:20.0.0'
    implementation 'com.google.firebase:firebase-firestore:23.0.1'
    implementation 'com.google.firebase:firebase-perf:20.0.1'
    implementation 'com.google.firebase:firebase-messaging:22.0.0'
    def hilt_version = 2.35
    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    implementation 'androidx.core:core-ktx:1.5.0'
    implementation 'androidx.appcompat:appcompat:1.3.0'
    implementation 'com.google.android.material:material:1.3.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    testImplementation 'junit:junit:4.+'


    //dagger hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")

    //firebase
    implementation platform('com.google.firebase:firebase-bom:28.1.0')
    implementation 'com.google.firebase:firebase-analytics'

    //googleOAuth
    implementation 'com.google.android.gms:play-services-drive:17.0.0'
    implementation 'com.google.android.gms:play-services-auth:19.0.0'

    //custom signin with google button
    implementation 'com.shobhitpuri.custombuttons:google-signin:1.1.0'

    //lottie animations
    implementation "com.airbnb.android:lottie:3.7.0"

    //Glide Dependency for IMAGE PROCESSING
    implementation 'com.github.bumptech.glide:glide:4.12.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'
    implementation 'com.google.android.gms:play-services-maps:17.0.1'
    implementation 'com.google.android.libraries.places:places:2.4.0'

    //CircleImageView
    implementation 'de.hdodenhof:circleimageview:3.1.0'
    implementation 'com.google.code.gson:gson:2.8.6'
    implementation 'com.github.bumptech.glide:glide:4.12.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'
    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    implementation 'androidx.core:core-ktx:1.5.0'
    implementation 'androidx.appcompat:appcompat:1.3.0'
    implementation 'com.google.android.material:material:1.3.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    implementation 'com.google.firebase:firebase-database-ktx:20.0.0'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    implementation 'androidx.navigation:navigation-fragment-ktx:2.3.5'
    implementation 'androidx.navigation:navigation-ui-ktx:2.3.5'
    implementation 'com.google.android.gms:play-services-maps:17.0.1'
    implementation 'com.google.android.gms:play-services-location:18.0.0'
    androidTestImplementation 'androidx.test.ext:junit:1.1.2'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'
    implementation 'com.google.firebase:firebase-auth:21.0.1'
    implementation 'com.google.firebase:firebase-database:20.0.0'

    implementation 'com.razorpay:checkout:1.6.6'
    // Retrofit & OkHttp
    implementation 'com.squareup.retrofit2:retrofit:2.5.0'
    // This library is used by retrofit internally to convert json-pojo and pojo-json
    implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
    //This library is used to observe the API logs, Http status code and the API response
    implementation 'com.squareup.okhttp3:logging-interceptor:3.8.0'

### Install

After making a clone of this repo open your cmd/terminal and use


### Starting app

Once everything has been installed, login/signup and allow location persmission.
Start using the app with searching nearby shops.

## Show your support

Give a ⭐️ if you like this project!


