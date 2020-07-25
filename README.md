# News App

An Android App that uses the news.org api to display latest news


## How to Build

1. Clone the git repository
2. Run `./gradlew build`

## Implementation
- The app was impelemnted with the MVVM framwork along with Clean Architecture
- Asycronous code was written with RxJava
- Navigation component
- ConstraintLayout

## Improvements
- This implementation uses ConstraintLayout heavily because of the requirements. In a production app, I would avoid ConstrainLayout in RecyclerView items as it could be less perfomant than other view groups in RecyclerViews.

- Also, I would replace the RxKotlin code with Kotlin Coroutines as it allows terser code and better clarity

- kotlinx.android.synthetic was used extensively for dev speed. In production, I'll use the explicit findViewById (see https://old.reddit.com/r/androiddev/comments/ala9p2/why_kotlinx_synthetic_is_no_longer_a_recommended/efdvpkg/)

- The Sources RecyclerView has a bug where checking a CheckBox checks another one. this seems to be due to recycling of views. To work around that, view recycling was disabled. With more time, I'll fix the bug buy saving checkbox state within the adapter
