object Versions {
    const val AGP = "7.3.1"
    const val kotlin = "1.7.20"
    const val daggerHilt = "2.44"
    const val core = "1.7.0"
    const val material = "1.9.0"
    const val appcompat = "1.6.1"
    const val constraint = "2.1.4"
    const val junit = "4.13.2"
    const val extJunit = "1.1.5"
    const val espresso = "3.5.1"
    const val room = "2.5.0"
    const val coroutines = "1.6.4"
    const val lifecycle = "2.6.1"
    const val navigationComponent = "2.5.3"
    const val binding = "1.5.9"
}

object Deps {

    object UI {
        const val core = "androidx.core:core-ktx:${Versions.core}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
        const val junit = "junit:junit:${Versions.junit}"
        const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }

    object DaggerHilt {
        const val hilt = "com.google.dagger:hilt-android:${Versions.daggerHilt}"
        const val compiler = "com.google.dagger:hilt-compiler:${Versions.daggerHilt}"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
    }

    object Coroutines {
        const val android =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object Lifecycle {
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    }

    object NavigationComponent {
        const val fragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.navigationComponent}"
        const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigationComponent}"
    }

    object ViewBindingDelegate {
        const val binding =
            "com.github.kirich1409:viewbindingpropertydelegate-noreflection:${Versions.binding}"
    }
}

object Plugins {

    object AGP {
        const val application = "com.android.application"
        const val library = "com.android.library"
    }

    object Kotlin {
        const val android = "org.jetbrains.kotlin.android"
        const val kapt = "kotlin-kapt"
    }

    object DaggerHilt {
        const val hilt = "com.google.dagger.hilt.android"
    }
}