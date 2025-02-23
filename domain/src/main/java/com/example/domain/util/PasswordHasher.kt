package com.example.domain.util

import org.mindrot.jbcrypt.BCrypt

object PasswordHasher {
    // Хеширование пароля
    fun hashPassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt(4)) // 4 - уровень сложности
    }

    // Проверка пароля
    fun verifyPassword(password: String, hashedPassword: String): Boolean {
        return BCrypt.checkpw(password, hashedPassword)
    }
}