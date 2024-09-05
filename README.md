# Zonak_Task
#Android News App Task

# Overview
This project is a news application built using Jetpack Compose for the UI, Navigation Compose for handling navigation, and MVI (Model-View-Intent) as the architecture pattern. The application incorporates Room for local data storage, Hilt for dependency injection, and includes testing to validate its functionality. It follows Clean Architecture principles to separate concerns and improve maintainability.

# Features
# Home Screen:

Displays a horizontal list of available categories fetched from the API.
Shows a vertical list of news articles based on the selected category.
Automatically selects the first category as the initial category for news when the app opens.
Updates the news list when a new category is selected.

# Article Details Screen:

Provides detailed information about a selected article.
Allows the user to share the article link with other apps.
Contains a button to open the article in a web browser for further reading.
Icons in the Top Bar:
Share Link: An icon to share the article link with other apps.
Open in Browser: An icon to open the article in a web browser for further reading.


# Offline Handling:
When the app is reopened and no internet connection is found, it displays the last news retrieved from the API.

# Architecture
![MVI](https://github.com/user-attachments/assets/62a53f14-8bb5-4262-9673-a2814653832a)

# MVI (Model-View-Intent):

Model: Represents the application's data and business logic. Utilizes Use Cases to fetch data and perform operations.
View: Composed of Composable functions that display the UI. Observes state changes and emits user intents.
Intent: Represents user actions and events that trigger state changes.

# Clean Architecture:

Presentation Layer: Manages UI state and interacts with the domain layer through ViewModels.
Domain Layer: Contains business logic and use cases.
Data Layer: Handles data retrieval and storage. Interacts with APIs and local databases.
Core Layer: Includes shared utilities, constants, and extensions used across different layers of the application. It provides foundational components and common functionality used throughout the app.
Single Source of Truth (SST): Implemented in the Data Layer to ensure that data is consistently managed and accessed through a single repository.








