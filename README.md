
# Travel Tales

Travel Tales is an Android application that allows users to log in, create journals, and add entries to each of their journals. This README provides an overview of the application's functionality, how to set it up, and details about key components and their implementation.

## Features
- User Authentication: Users can log in to the app using their email and password. Upon successful login, they receive a token for authenticated requests.
  
- Journal Management: Users can create, edit, and view journals. Each journal contains multiple entries. All the journals are listed for one user upon once they are logged in.
  
- Entry Management: Users can view entries within a journal.

## Getting Started

### Prerequisites

- Android Studio
- Node.js
- MySQL database

### Setting Up the Backend

1. Clone the repository and navigate to the backend directory:

    ```sh
    git clone https://github.com/chachamottaki/travel-journal.git
    cd travel-journal/backend
    ```

2. Install the dependencies:

    ```sh
    npm install
    ```

3. Set up your MySQL database and update the `.env` file with your database credentials:

    ```sh
    DB_USER=yourusername
    DB_PASS=yourpassword
    DB_NAME=travel-journal
    ```

4. Run the migrations to set up the database:

    ```sh
    npx sequelize-cli db:migrate
    ```

5. Start the backend server:

    ```sh
    node server.js
    ```

### Setting Up the Frontend

1. Open the Android project in Android Studio:

    ```sh
    cd traveltales
    ```

2. Sync the project with Gradle files and ensure all dependencies are installed.

3. Run the application on an emulator or a physical device.

## Project Structure

### Backend

- `controllers/`: Contains the controllers for handling user, journal, and entry-related requests.
- `models/`: Contains the Sequelize models for User, Journal, and Entry.
- `routes/`: Contains the route definitions for the API endpoints.

### Frontend

- `com.example.traveltales/`: Contains the main application code.
- `network/`: Contains the Retrofit service interfaces and network-related data classes.
- `viewmodel/`: Contains the ViewModel for handling the business logic and the communication with the backend.


## Backend

#### User Controller

Handles user login and CRUD operations for users.


#### Journal Controller

Handles CRUD operations for journals.

#### Backend Endpoints

- `POST /login`: Authenticates a user and returns a JWT token.
- `POST /user`: Creates a new user.
- `GET /user/:user_id`: Retrieves user details.
- `PUT /user/:user_id`: Updates user details.
- `DELETE /user/:user_id`: Deletes a user.
- `POST /user/:user_id/journal`: Creates a new journal for a user.
- `GET /user/:user_id/journals`: Retrieves all journals for a user.
- `PUT /journal/:journal_id`: Updates a journal.
- `DELETE /journal/:journal_id`: Deletes a journal.
- `GET /journal/:journal_id/entries`: Retrieves all entries for a journal.
- `POST /journal/:journal_id/entry`: Creates a new entry for a journal.
- `PUT /journal/:journal_id/entry/:entry_id`: Updates an entry.
- `DELETE /journal/:journal_id/entry/:entry_id`: Deletes an entry.


## Frontend: Key Components

### ViewModel

Handles the business logic and API calls.

### Main UI Components

- `TravelTalesApp`: Main entry point of the app that sets up navigation.
- `LoginPage`: Handles user login.
- `HomePage`: Displays a list of journals and allows creating new journals.
- `JournalEntriesScreen`: Displays entries for a selected journal.

### Helper Components

- `JournalItem`: Displays a journal with options to edit.
- `CreateJournalDialog`: Dialog for creating a new journal.
- `EditJournalDialog`: Dialog for editing a journal.


## Running the App

1. Ensure the backend server is running.
2. Open the Android project in Android Studio.
3. Sync the project with Gradle files and ensure all dependencies are installed.
4. Run the application on an emulator or a physical device.

## Future Enhancements

The following features are planned to be added to the app:

1. **Sign Up a new User**
   - Add functionality for users to create an account
     
2. **Entry Management:**
   - Add functionality to create, edit, and delete entries within a journal.
   - Allow users to add images or videos to entries.

3. **Journal Management:**
   - Implement the delete journal functionality.
   - Allow users to change the theme of a journal.

4. **Sharing:**
   - Add options to share an entry or a journal via social media platforms.

5. **Expense Management:**
   - Implement a feature to track and manage travel expenses within a journal.


Happy traveling & journaling with TravelTales!

![Smiling Dumpling](./smiling-dudu.jpg)



(img src: https://www.google.com/search?client=opera-gx&hs=39j&sca_esv=5ac3b5860f52b755&sxsrf=ADLYWILUgWJ194cUw01mURAhQHlf8AUAxA:1717790055690&q=anime+dumpling+kawaiiµ&tbm=isch&source=lnms&prmd=ivnbz&sa=X&ved=2ahUKEwins8XQosqGAxVcVKQEHUkRNGYQ0pQJegQICxAB&biw=1359&bih=650&dpr=1.38#imgrc=qtkgYQc96DtkvM)
