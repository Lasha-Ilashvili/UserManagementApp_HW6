package com.example.usermanagementapp_hw6

class UserDatabase {
    var activeUsers: Int = 0
    var deletedUsers: Int = 0
    private val users = mutableMapOf<String, User>()

    /*
     * Adds a new user to the database.
     * Returns a message indicating whether the operation was successful or not.
     */
    fun addUser(user: User): String {
        return if (userExists(user.email)) {
            "User already exists!"
        } else {
            users[user.email] = user
            activeUsers++
            "User added successfully!"
        }
    }

    /*
     * Removes a user from the database.
     * Returns a message indicating whether the operation was successful or not.
     */
    fun removeUser(email: String): String {
        return if (!userExists(email)) {
            "User does not exist!"
        } else {
            users.remove(email)
            activeUsers--
            deletedUsers++
            "User deleted successfully!"
        }
    }

    /*
     * Updates user information in the database.
     * Returns a message indicating whether the operation was successful or not.
     */
    fun updateUser(updatedUser: User): String {
        return if (!userExists(updatedUser.email)) {
            "User does not exist or incorrect email address!"
        } else {
            users[updatedUser.email] = updatedUser
            "User updated successfully"
        }
    }

    /* Checks if a user with the given email exists in the database */
    private fun userExists(email: String) = users.containsKey(email)
}