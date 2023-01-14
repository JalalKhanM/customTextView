
JalalView

[![](https://jitpack.io/v/JalalKhanM/customTextView.svg)](https://jitpack.io/#JalalKhanM/customTextView)

This is an android libray that is used to show bitmap behind the given text.

Depenedency:

add this to your project level build.gradle file
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

add this line to your module build.gradle file.
implementation 'com.github.JalalKhanM:customTextView:1.0.1'


<com.custom.view.textview.JalalView
            android:id="@+id/textId"
            android:layout_width="270dp"
            android:layout_height="70dp"
            android:background="@color/transparent"
            app:mobiPixel_Drawable="@drawable/yourImage"
            app:mobiPixel_String="your Text"
            app:mobiPixel_fontSize="40sp"
            />


