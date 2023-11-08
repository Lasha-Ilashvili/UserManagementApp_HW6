package com.example.usermanagementapp_hw6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.usermanagementapp_hw6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val userDatabase = UserDatabase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addUserButton.setOnClickListener {
            add(
                binding.firstNameInput.text.toString(),
                binding.lastNameInput.text.toString(),
                binding.emailInput.text.toString(),
                binding.ageInput.text.toString().toIntOrNull() ?: 0
            )
        }

        binding.removeUserButton.setOnClickListener {
            delete(binding.emailInput.text.toString())
        }

        binding.updateUserButton.setOnClickListener {
            update(
                binding.firstNameInput.text.toString(),
                binding.lastNameInput.text.toString(),
                binding.emailInput.text.toString(),
                binding.ageInput.text.toString().toIntOrNull() ?: 0
            )
        }
    }

    private fun add(firstName: String, lastName: String, email: String, age: Int) {
        val user = User(email, firstName, lastName, age)
        val result = user.validateUser()

        if (result.isEmpty()) {
            val dataBaseRes = userDatabase.addUser(user)
            if (dataBaseRes == "com.example.usermanagementapp_hw6.User added successfully!") {
                colorText(dataBaseRes, R.color.green)
                binding.activeUserCount.text = "Active Users: "+ userDatabase.activeUsers.toString()
            } else {
                colorText(dataBaseRes, R.color.red)
            }
        } else {
            colorText(result, R.color.red)
        }
    }

    private fun delete(email: String) {
        val result = userDatabase.removeUser(email)
        if (result == "com.example.usermanagementapp_hw6.User deleted successfully!") {
            colorText(result, R.color.green)
            binding.activeUserCount.text = "Active Users: " + userDatabase.activeUsers.toString()
            binding.deletedUserCount.text = "Deleted Users: "+ userDatabase.deletedUsers.toString()
        } else {
            colorText(result, R.color.red)
        }
    }

    private fun update(firstName: String, lastName: String, email: String, age: Int) {
        val user = User(email, firstName, lastName, age)
        val result = user.validateUser()

        if (result.isEmpty()) {
            val dataBaseRes = userDatabase.updateUser(user)
            if (dataBaseRes == "com.example.usermanagementapp_hw6.User updated successfully") {
                colorText(dataBaseRes, R.color.green)
            } else {
                colorText(dataBaseRes, R.color.red)
            }
        } else {
            colorText(result, R.color.red)
        }
    }

    private fun colorText(text: String, color: Int) {
        binding.operationText.text = text
        binding.operationText.setTextColor(ContextCompat.getColor(this, color))
    }
}