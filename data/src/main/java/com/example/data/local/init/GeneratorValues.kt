package com.example.data.local.init

import com.example.data.local.entity.user.BaseUserEntity
import com.example.data.local.init.DatabaseInitializer.Companion.COUNT_CLIENT_USERS
import com.example.data.local.init.DatabaseInitializer.Companion.COUNT_COMPANY_USERS
import com.example.data.local.init.DatabaseInitializer.Companion.COUNT_MANAGER_USERS
import com.example.data.local.init.DatabaseInitializer.Companion.COUNT_OPERATOR_USERS
import com.example.domain.models.user.TypeOfUser
import kotlin.random.Random

object GeneratorValues {
    val firstNames = listOf("Alexey", "Ivan", "Peter", "Nikolay", "Sergey", "Dmitry", "Andrey",
        "Vasily", "Gennady", "Oleg", "Mikhail", "Yegor", "Anton", "Arkady", "Vladimir",
        "Grigory", "Kirill", "Maxim", "Fedor", "Yuri")

    val lastNames = listOf("Ivanov", "Petrov", "Sidorov", "Kuznetsov", "Smirnov", "Popov",
        "Vasilyev", "Zaitsev", "Morozov", "Belyaev", "Grigoryev", "Mikhaylov", "Frolov", "Semenov",
        "Voronov", "Romanov", "Korolev", "Lebedev", "Sokolov", "Timofeyev")

    val surnames = listOf("Alexandrovich", "Viktorovich", "Sergeevich", "Dmitrievich", "Nikolaevich",
        "Petrovich", "Ivanovich", "Anatolyevich", "Maximovich", "Andreevich", "Yuryevich", "Gennadyevich",
        "Olegovich", "Mikhaylovich", "Yegorovich", "Antonovich")

    val seriesPassportChoices = listOf("AB", "BM", "НВ", "КН", "МР", "МС", "КВ")
    val domains = listOf("mail.ru", "yandex.ru", "gmail.com")


    private fun generatePassportNumber(): String {
        return Random.nextInt(1000000, 9999999).toString()
    }

    private fun generateIdentityNumber(): String {
        return "${Random.nextInt(1000000, 9999999)}" +
                "${('A'..'Z').random()}" +
                "${Random.nextInt(100, 999)}" +
                "${('A'..'Z').random()}B" +
                "${Random.nextInt(1, 9)}"
    }

    private fun generatePhone(): String {
        val operators = listOf("25", "29", "33", "44")
        return "+375${operators.random()}${Random.nextInt(1000000, 9999999)}"
    }

    private fun generateUniqueEmails(lastNames: List<String>, count: Int): List<String> {
        val emails = mutableSetOf<String>()

        while (emails.size < count) {
            val email = "${lastNames[emails.size].lowercase()}${Random.nextInt(10, 99)}@${domains.random()}"
            emails.add(email)
        }

        return emails.toList()
    }

    fun generatePasswords(count: Int): List<String> {
        return List(count.toInt()) {
            "${Random.nextInt(100000, 999999)}${('A'..'Z').random()}${('a'..'z').random()}"
        }
    }

    fun generateBaseUsers(count: Int): List<BaseUserEntity> {
        val firstNames = List(count) { firstNames.random() }
        val lastNames = List(count) { lastNames.random() }
        val surNames = List(count) { surnames.random() }
        val emails = generateUniqueEmails(lastNames = lastNames, count = count)

        return List(count.toInt()) { index ->
            BaseUserEntity(
                id = index + 1,
                firstName = firstNames[index],
                lastName = lastNames[index],
                surName = surNames[index],
                seriesPassport = seriesPassportChoices.random(),
                numberPassport = generatePassportNumber(),
                identityNumber = generateIdentityNumber(),
                phone = generatePhone(),
                email = emails[index],
                typeOfUser = when {
                    index < COUNT_CLIENT_USERS
                        -> TypeOfUser.ClientUser.toString()

                    index in COUNT_CLIENT_USERS until COUNT_CLIENT_USERS + COUNT_COMPANY_USERS
                        -> TypeOfUser.CompanyUser.toString()

                    index in COUNT_CLIENT_USERS + COUNT_COMPANY_USERS until
                            COUNT_CLIENT_USERS + COUNT_COMPANY_USERS + COUNT_MANAGER_USERS
                        -> TypeOfUser.ManagerUser.toString()

                    index in COUNT_CLIENT_USERS + COUNT_COMPANY_USERS + COUNT_MANAGER_USERS until
                            COUNT_CLIENT_USERS + COUNT_COMPANY_USERS + COUNT_MANAGER_USERS
                            + COUNT_OPERATOR_USERS
                        -> TypeOfUser.OperatorUser.toString()

                    else -> TypeOfUser.AdminUser.toString()
                }
            )
        }
    }
}