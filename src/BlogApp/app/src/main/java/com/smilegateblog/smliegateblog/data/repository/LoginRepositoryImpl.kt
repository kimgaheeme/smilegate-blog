package com.smilegateblog.smliegateblog.data.repository

import android.util.Log
import com.smilegateblog.smliegateblog.data.api.LoginApi
import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.data.dto.login.MyInfoResponse
import com.smilegateblog.smliegateblog.data.pref.PrefDataSource
import com.smilegateblog.smliegateblog.data.pref.model.UserPref
import com.smilegateblog.smliegateblog.data.pref.model.toPref
import com.smilegateblog.smliegateblog.domain.model.User
import com.smilegateblog.smliegateblog.domain.model.toDomain
import com.smilegateblog.smliegateblog.domain.repository.LoginRepository
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginApi: LoginApi,
    private val pref: PrefDataSource
) : LoginRepository {

    override suspend fun login(loginRequest: LoginRequest): Flow<Resource<User>> {
        return flow {
            try{
                val response = loginApi.postLogin(loginRequest)
                Log.d("Login", "login repo exec")
                if(response.isSuccessful){
                    val user = response.body()!!.toDomain()
                    pref.setUser(response.body()!!.toPref())
                    emit(Resource.Success(user))
                }else{
                    emit(Resource.Error(response.errorBody().toString()))
                }
            } catch (e: HttpException) {
                Log.d("Login", "HttpException")
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
            } catch(e: IOException) {
                Log.d("Login", e.stackTraceToString())
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        }
    }

    override suspend fun getMyInfo(): Flow<Resource<User>> {
        return flow {
            try{
                pref.getUserId().collect(){ userId ->
                    val response = loginApi.getMyInfo(userId)
                    Log.d("GetMyInfo", "repo exec")
                    if(response.isSuccessful){
                        val user = response.body()!!.toDomain()
                        emit(Resource.Success(user))
                    }else{
                        emit(Resource.Error(response.errorBody().toString()))
                    }
                }
            } catch (e: HttpException) {
                Log.d("GetMyInfo", "HttpException")
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
            } catch(e: IOException) {
                Log.d("GetMyInfo", e.stackTraceToString())
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        }
    }

    override suspend fun logoutUser() {
        pref.logoutUser()
    }

}