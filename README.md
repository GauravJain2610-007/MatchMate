## MatchMate Sample App

🔧 Features
✅ Jetpack Compose UI
Built entirely with Jetpack Compose because I'm familiar and comfortable with it.

✅ Paging with Room and RemoteMediator
Loads user data from https://randomuser.me/ API and stores it in a local Room database with seamless pagination using Paging 3.

✅ Gender Filter
The app filters users based on gender. Currently, the gender value is hardcoded, but the plan is to ask the user for their gender at app launch in future versions.

✅ Smart Match Logic
Match color is shown on each user card:

🟩 Green: Both age and country closely match

🟧 Orange: Only age or country matches

⚪ Gray: No match

Currently, this logic is based on:

A hardcoded user age and country

A 5–6 year age gap considered a match

In the future, I plan to collect basic user details (like age, country, interests) on app launch and use them to calculate better matches.

🚧 Roadmap / To-do
Prompt user for gender and basic details (age, country) on first launch

Improve match logic with more fields (e.g., interests, language)

Add local preferences to save user config

Dark mode & theme support

📦 Tech Stack
Kotlin

Jetpack Compose

Paging 3

Room DB

Retrofit + Coroutines

MVVM architecture