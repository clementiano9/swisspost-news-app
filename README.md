## Post Finance News App
An Android app built for my assessment at Post Finance. The app is built using Jetpack Compose and MVVM architecture using a modular approach to showcase the required requirements.


### Running the app
To run the app, place the secrets.properties file in the root directory of the project. The file should contain the api key for the news api. This has been extracted to keep with security best practices.

### Libraries used
- Retrofit
- Dagger Hilt
- Coroutines
- Accompanist
- Coil

### Screenshots
<img src="screenshots/screenshot_loading.jpeg" width="250">
<img src="screenshots/screenshot_headlines.jpeg" width="250">
<img src="screenshots/screenshot_article.jpeg" width="250">

### Modules

Modules are collection of source files and build settings that allow you to divide a project into discrete units of functionality. The following modules are used in this project:

- `:app` depends on `:core-ui` and `:feature-headlines`.
- `:features-headlines` modules depends on `:core-ui`, `:data`, and `:domain`.
- `:core-ui` doesn't have any dependency.
- `:data` depends on `:domain`.
- `:domain` doesn't have any dependency.