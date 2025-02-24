package com.example.domain.useCase.allUserCases.UserUseCases.insert

import com.example.domain.models.company.Company
import com.example.domain.models.user.BaseUser
import com.example.domain.models.user.CertificateUser
import com.example.domain.models.user.TypeOfUser
import com.example.domain.models.user.companyUser.CompanyUser
import com.example.domain.repository.UserRepository
import com.example.domain.util.PasswordHasher
import kotlinx.coroutines.flow.firstOrNull

class InsertCompanyUserUseCase(
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
        identityNumber: String,
        typeOfUser: TypeOfUser,
        company: Company
    ){
        var baseUser = BaseUser(
            id = 0,
            firstName = firstName,
            lastName = lastName,
            surName = surName,
            phone = phone,
            email = email,
            seriesPassport = seriesPassport,
            numberPassport = numberPassport,
            identityNumber = identityNumber,
            typeOfUser = typeOfUser
        )

        userRepository.insertBaseUser(baseUser)
        baseUser = userRepository.getBaseUserByEmail(email = email).firstOrNull()!!

        val certificateUser = CertificateUser(
            baseUser = baseUser,
            id = 0,
            hashedPassword = PasswordHasher.hashPassword(password)
        )

        val companyUser = CompanyUser(
            baseUser = baseUser,
            companyUserId = 0,
            company = company
        )

        userRepository.insertCertificateUser(certificateUser)
        userRepository.insertCompanyUser(companyUser)
    }
}