package com.example.jetpackdemo.day2022.month09.day2022_0906.ui.login

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)