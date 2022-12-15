package com.smilegateblog.smliegateblog.domain.usecase.login

import android.util.Log
import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.domain.model.User
import com.smilegateblog.smliegateblog.domain.repository.LoginRepository
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyInfoUseCase @Inject constructor(private val loginRepository: LoginRepository){
    suspend fun invoke(userId: Int) : Flow<Resource<User>> {
        Log.d("GetMyInfo", "usecase exec")

        return loginRepository.getMyInfo()
    }
}