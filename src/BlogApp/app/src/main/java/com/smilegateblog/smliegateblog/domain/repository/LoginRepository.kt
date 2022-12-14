package com.smilegateblog.smliegateblog.domain.repository

import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.data.dto.login.LoginResponse
import com.smilegateblog.smliegateblog.data.dto.login.MyInfoResponse
import com.smilegateblog.smliegateblog.domain.model.User
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface LoginRepository {
    suspend fun login(loginRequest: LoginRequest) : Flow<Resource<User>>
    suspend fun getMyInfo(userId: Int) : Flow<Resource<User>>
}