package com.smilegateblog.smliegateblog.domain.usecase.login

import android.util.Log
import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.domain.model.User
import com.smilegateblog.smliegateblog.domain.repository.LoginRepository
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository){
    suspend fun invoke(loginRequest: LoginRequest) : Flow<Resource<User>> {
        Log.d("Login", "login usecase exec")

        return loginRepository.login(loginRequest)
    }
}