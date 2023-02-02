
# JalalView

[![](https://jitpack.io/v/JalalKhanM/customTextView.svg)](https://jitpack.io/#JalalKhanM/customTextView)

    This is an android library that is use to show image behind the given text.

### Dependency:

add this line to your project level build.gradle file

````
allprojects {
repositories {
    maven { url 'https://jitpack.io' }
    }
}
````

add this line to your module level build.gradle file

**implementation 'com.github.JalalKhanM:customTextView:1.0.1'**


### Use 1:
````
 <com.text.stylish.StylishTextView
        android:id="@+id/yourId"
        android:layout_width="270dp"
        android:layout_height="70dp"
        app:mobiPixel_Drawable="@drawable/yourDrawable"
        app:mobiPixel_String="Example Text"
        app:mobiPixel_fontSize="40sp"
        app:mobiPixel_isAnimateDrawable="true"
        app:mobiPixel_AnimSpeed="10"
   
        />

````

### Use 2:
````
 <com.text.stylish.StylishTextView
        android:id="@+id/yourId"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:mobiPixel_Drawable="@drawable/yourDrawable"
        app:mobiPixel_String="Example Text"
        app:mobiPixel_fontSize="40sp"
        app:mobiPixel_isAnimateDrawable="true"
        app:mobiPixel_isVertical="true"
        app:mobiPixel_verticalSpeed="20"
 
        />

````

| Attribute | Description |
| --- | --- |
| mobiPixel_String | Text value for displaying. |
| mobiPixel_fontSize | Set Size of Text |
| mobiPixel_Drawable | drawable to show behind Text |
| mobiPixel_isAnimateDrawable | tells whether image should animate behind the text or not, gets boolean value |
| mobiPixel_AnimSpeed | sets the speed of animation behind the text |
| mobiPixel_isVertical | tells whether image should animate vertically behind the text or not, gets boolean value |
| mobiPixel_verticalSpeed | sets the speed of animation behind the text |

| Method | Description |
| --- | --- |
| setText | to set text programetically |


### ScreenShot:

![screenShot](https://user-images.githubusercontent.com/111576812/212462204-93ea5edc-d6a3-4837-9bc9-3c14bddf3ce0.PNG)


![screenShot2](https://user-images.githubusercontent.com/111576812/212470029-3996dee8-6d53-436a-bdde-67cd7c076419.png)


