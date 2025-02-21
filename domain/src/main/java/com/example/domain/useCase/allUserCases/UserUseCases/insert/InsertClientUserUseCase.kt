package com.example.domain.useCase.allUserCases.UserUseCases.insert

import com.example.domain.models.user.BaseUser
import com.example.domain.models.user.CertificateUser
import com.example.domain.models.user.clientUser.ClientUser
import com.example.domain.repository.UserRepository
import com.example.domain.util.PasswordHasher

class InsertClientUserUseCase(
    private val userRepository: UserRepository
)
{
    suspend operator fun invoke(
        firstName: String,
        lastName: String,
        surName: String,
        phone: String,
        email: String,
        password: String,
        seriesPassport: String,
        numberPassport: String,
        identityNumber: String
    ){
        val baseUser = BaseUser(
            id = 0,
            firstName = firstName,
            lastName = lastName,
            surName = surName,
            phone = phone,
            email = email,
            seriesPassport = seriesPassport,
            numberPassport = numberPassport,
            identityNumber = identityNumber
        )

        val certificateUser = CertificateUser(
            baseUser = baseUser,
            id = 0,
            hashedPassword = PasswordHasher.hashPassword(password)
        )

        val clientUser = ClientUser(
            baseUser = baseUser,
            clientUserId = 0
        )

        userRepository.insertBaseUser(baseUser)
        userRepository.insertCertificateUser(certificateUser)
        userRepository.insertClientUser(clientUser)
    }
}