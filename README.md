<h1 align="center"> Valorant Info </h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=26"><img alt="API" src="https://img.shields.io/badge/API-26%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://kotlinlang.org"><img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-1.8.xx-blue"/></a>
  <img alt="MVVM" src="https://img.shields.io/badge/MVVM-Architecture-orange"/>
  <a href="https://developer.android.com/kotlin/coroutines"><img alt="Coroutines" src="https://img.shields.io/badge/Coroutines-Asynchronous-red"/></a>
</p>

<p align="center">
  <img src="https://github.com/marlonsantini/Valorant/blob/master/screenshots/agentScreen.png" width="300"><br>
</p>

<p align="center">
A sample android app consuming Valorant API to display valorant agents, maps and weapons, it has been built with Architecture Components and MVVM pattern.
</p>

## Tech stack & Open-source libraries
- Minimum SDK level 26
- [Kotlin](https://kotlinlang.org/)
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- [Koin](https://insert-koin.io) for dependency injection.
- [Firebase](https://firebase.google.com/) for Crashlytics and Analytics
- [Admob](https://admob.google.com/)
- [CircleIndicator](https://github.com/ongakuer/CircleIndicator) A lightweight indicator lists
- [JetPack](https://developer.android.com/jetpack)
  - LiveData - Notify domain layer data to views.
  - Lifecycle - Dispose of observing data when lifecycle state changes.
  - Fragment-ktx - A set of Kotlin extensions that helps with fragment lifecycle.
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Allows you to more easily write code that interacts with views
  - ViewModel - UI related data holder, lifecycle aware.
  - Navigator - Navigation refers to the interactions that allow users to navigate across, into, and back out from the different pieces of content within your app.
- Architecture
  - Multi-module design for the app.
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository pattern (NetworkBoundResource)
  - Clean Architecture approach.
- [Gradle KotlinDsl](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - Construct the REST APIs.
- [Moshi](https://github.com/square/moshi) - A Modern JSON library for Android and Java.
- [Coil](https://coil-kt.github.io/coil/) - For Loading images from Urls.

## Architecture
Valorant Agents is Multi-modular application with a meaningful separation for layers and features with the necessary grouping.
With MVVM architecture with an additional Domain layer for each module by itself.

Modules Design:
- App
  - Api
  - Di
  - Extensions
  - Network
  - Features
      - Agents
        - Data
        - Di
        - Ui
          - Adapter
          - ViewState
      - Maps
        - Data
        - Di
        - Ui
          - Adapter
          - ViewState
      - Weapons
        - Data
        - Di
        - Ui
          - Adapter
          - ViewState
    
## Open API
Valorant Agents uses the [Valorant-api](https://dash.valorant-api.com/) for required data.
Valorant-api provides an extensive API containing data of most in-game items, assets and more!

## In progress 🚧.
- [ ] Write tests
- [ ] Code cleanup

# License
```xml
Designed and developed by .fingerfire - 2023 

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
