# Stan Android Coding Challenge


## Overview 

Instead of starting a project from scratch, we'll be using a sample project already available from Google and making some changes to it. The tasks are a mixture of technical tasks as well as product features, similar to how you would be working at Stan.

In doing the tasks, we ask that you keep a diary where you document issues you faced, how you overcame them and approaches to solutions. We are looking for how you are able to describe the problems you face and provide rationale for decisions made. Observations along the way are also encouraged - imagine you were explaining your process during a pair programming session.

Please send us your code when you're done. We prefer if it is publicly available on some service like Github or Bitbucket.

This challenge is expected to take 2-4 hours, however you can spend as much time as you'd like.


## The Challenge 

1. Start with the Google sample for [PictureInPictureKotlin](https://github.com/android/media-samples/tree/main/PictureInPictureKotlin). You may need to clone the entire repo with all the samples, however please only use the `PictureInPictureKotlin` directory 
2. Start a new git repo inside of the directory so that your work (inlcuding commits) can later on be sent to us.
2. Create a file `diary.md` where you can journal each task.
3. Choose at least 2 of the tasks that you'd like to complete. Feel free to do them all.


### Task 1 - Legacy support

Change the `minVersion` to support API 21. Make change so that the app can gracefully support the newer features when possible with their API levels. You are welcome to make any changes to the UI as you see fit. 

We are looking for some thought into UX and why you think some decisions you took might be better for the user. We aren’t that interested in pixel perfect design, Material components are fine, just some thought to UX is best.


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