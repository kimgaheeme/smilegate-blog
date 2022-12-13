package com.smilegateblog.smliegateblog.data.repository

import com.smilegateblog.smliegateblog.data.api.LoginApi
import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.domain.model.User
import com.smilegateblog.smliegateblog.domain.model.toDomain
import com.smilegateblog.smliegateblog.domain.repository.LoginRepository
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val loginApi: LoginApi) : LoginRepository {
    override suspend fun login(loginRequest: LoginRequest): Flow<Resource<User>> {
        return flow {
            val response = loginApi.postLogin(loginRequest)

            if(response.isSuccessful){
                val user = response.body()!!.toDomain()
                emit(Resource.Success(user))
            }else{
                emit(Resource.Error(response.errorBody().toString()))
            }
        }
    }

}