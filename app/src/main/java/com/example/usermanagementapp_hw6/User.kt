package com.example.usermanagementapp_hw6

import android.util.Patterns

data class User(
    val email: String, // Unique identifier
    var firstName: String,
    var lastName: String,
    var age: Int
) {
    /* Validate the user's input for correctness */
    fun validateUser() = when {
        !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> // Checks if the email format is valid
            generateErrorMessage("Invalid email address!")

        email.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || age == 0 -> // Checks for empty fields
            generateErrorMessage("All fields must be filled!")

        else -> ""
    }

    /* Generate an error message with the specified text */
    private fun generateErrorMessage(message: String) = "Error! $message"
}
