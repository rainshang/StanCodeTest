



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
5. After adding version checking in `MovieView`, it doesn't crash any more.
```kotlin
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                startVideo()
            }
```




### Task 2 - Unit tests

The project already has some tests for `MainActivity` and `MovieActivity`, however none for the `MainViewModel`. Please add some that you would think are appropriate and include any app changes that may be needed. Add any libraries that you would like to be able to achieve this task.

We are looking for how changes to support unit tests are made, as well as choice of things to test.

### Task 3 - New feature

When you move from `MainActivity` to `MovieActivity`, the timer stops and is restarted when you return to the activity again. Instead of that, we would like for the timer to continue running, even if you switch between screens. The only time the timer should stop is if the user taps on the pause icon in `MainActivity`.

If you would like to add any UX variations on this, feel free to get creative as you want, just ensure that you maintain the timer across navigation between activites. 

We are looking for an implementation of some kind of repository where this state will be stored and how it is plugged into the rest of the app. Also the integration with the current app to make this work will be reviewed.


## Other Notes

Please send through any other code or projects that you're proud of and would like to share with us.

Any feedback on the coding challenge once you're done is also appreciated!