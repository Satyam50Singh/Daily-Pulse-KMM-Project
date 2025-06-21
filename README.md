# 📱 DailyPulse

DailyPulse is a Kotlin Multiplatform (KMP) news application that delivers curated business content for both **Android** and **iOS** users from a shared codebase. The project demonstrates full-stack mobile development using modern tools, reactive programming paradigms, and scalable architecture.

---

## 🚀 Features

- 🔥 Build Native **Android** and **iOS** apps from a single codebase
- 🔁 Shared **business logic, presentation logic, and infrastructure** using Kotlin Multiplatform
- 🌐 **Networking** powered by [Ktor](https://ktor.io)
- 🧠 **Clean Architecture** and **MVI** pattern for scalable and testable code
- 🔄 **StateFlow** & reactive state management
- 💾 Offline support using **SQLDelight** for local database in KMP
- 🧩 **Dependency Injection** with [Koin](https://insert-koin.io)
- ⚙️ Async programming using Kotlin **Coroutines**
- 🎨 Native Android UI with **Jetpack Compose**
- 🍎 Native iOS UI with **SwiftUI**

---

## 🧱 Tech Stack

| Layer              | Android             | iOS                | Shared (KMP)                     |
|--------------------|---------------------|---------------------|----------------------------------|
| UI                 | Jetpack Compose      | SwiftUI             | —                                |
| Presentation       | ViewModel + MVI     | ViewModel + MVI     | Shared ViewModels                |
| Domain             | Use Cases, Models   | Use Cases, Models   | Shared                           |
| Data               | Ktor, SQLDelight    | Ktor, SQLDelight    | Shared Repositories, API, DB     |
| DI                 | Koin                | Koin                | Shared Modules                   |
| Concurrency        | Coroutines          | Coroutines          | Shared Logic                     |

---
