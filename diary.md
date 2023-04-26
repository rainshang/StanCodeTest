



### Task 1 - Legacy support

1. First, solve the comple and lint errors. So add version checking for the places calling high api, etc.
```kotlin
    binding.pip.setOnClickListener {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    enterPictureInPictureMode(updatePictureInPictureParams(viewModel.started.value == true))
                }
            }
```
2. Alright, it can be compiled. However, will crash on api 21. Reason is
```log 
java.lang.NoClassDefFoundError: Failed resolution of: Landroid/app/PictureInPictureParams$Builder;
    at androidx.activity.Api26Impl.setPipParamsSourceRectHint(PipHintTracker.kt:107)
    at androidx.activity.PipHintTrackerKt$trackPipAnimationHintView$2.emit(PipHintTracker.kt:94)
```
It seems `MainActivity` enabled some pip setting. Checking...
Found it!!
```kotlin
    trackPipAnimationHintView(binding.stopwatchBackground)
```
So wrap it with version checking. The App is running on api 21! [571b3b247c491c9177ebcd3335b85df678cc5d27]()
3. Played a while. Found it crashes when clicking "Switch to using MediaSession". Oh, it's `MovieActivity`. Guess need to do the same version check.
4. Still crashes. OK, `MovieView`...
5. After adding version checking in `MovieView`, it doesn't crash any more. [32ece981a533099252c7c342c7655bbb3341d30a]()
```kotlin
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                startVideo()
            }
```
6. Time to add more user friendly tips. [20807a129b121c0e414d93c0076258dd344fdb58]()




### Task 2 - Unit tests

Since the project already has some tests for `MainActivity` and `MovieActivity`, I'll just create Unit Test for `MainViewModel`.

Well, met this
```log
java.lang.RuntimeException: Method uptimeMillis in android.os.SystemClock not mocked. See http://g.co/androidstudio/not-mocked for details.
```
I think DI is a peroper solution here. But I choose to manually inject it cause this's only used here.

Met a problem when running test code. Decide to skip it first. [3661aabbca132210642a78785d38b66f1ea5b3a5]()

### Task 3 - New feature

First thing came out of my head is to covert the 2 Activities to Fragments and then share the ViewModel. Then I read the repository stuff. Hmmm...Feel I can save ViewModel states to the repository and read back when recreated. Nice.

The main idea is to keep the exisitng local UI driver, but save the started moment on server.