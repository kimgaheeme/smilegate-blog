package com.smilegateblog.smliegateblog.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.gson.Gson
import com.smilegateblog.smliegateblog.data.api.LoginApi
import com.smilegateblog.smliegateblog.data.api.PostApi
import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.data.dto.login.MyInfoResponse
import com.smilegateblog.smliegateblog.data.dto.post.*
import com.smilegateblog.smliegateblog.data.pagingsource.MyPostPagingSource
import com.smilegateblog.smliegateblog.data.pagingsource.RecentPostPagingSource
import com.smilegateblog.smliegateblog.data.pref.PrefDataSource
import com.smilegateblog.smliegateblog.domain.model.Post
import com.smilegateblog.smliegateblog.domain.model.User
import com.smilegateblog.smliegateblog.domain.model.toDomain
import com.smilegateblog.smliegateblog.domain.repository.LoginRepository
import com.smilegateblog.smliegateblog.domain.repository.PostRepository
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.File
import java.io.IOException
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postApi: PostApi,
    private val pref: PrefDataSource
) : PostRepository {

    override suspend fun postPost(
        postPostRequest: PostPostRequest,
        file: File
    ): Flow<Resource<PostPostResponse>> {
        val multipartBody = MultipartBody.Part.createFormData(
            name = "postImg",
            filename = file.name,
            body = file.asRequestBody("image/*".toMediaType())
        )
        return flow {
            try{
                pref.getUserId().collect(){ userId ->
                    val response = postApi.postPost(
                        postPostRequest = Gson().toJson(postPostRequest)
                            .toRequestBody("application/json".toMediaTypeOrNull()),
                        image = multipartBody,
                        userId = userId)
                    Log.d("PostPost", "repo exec")
                    if(response.isSuccessful){
                        val postId = response.body()!!
                        emit(Resource.Success(postId))
                    }else{
                        emit(Resource.Error(response.errorBody().toString()))
                    }
                }
            } catch (e: HttpException) {
                Log.d("PostPost", "HttpException")
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
            } catch(e: IOException) {
                Log.d("PostPost", e.stackTraceToString())
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        }
    }

    override suspend fun getPost(postid: Int): Flow<Resource<GetPostResponse>> {
        return flow {
            try{
                pref.getUserId().collect(){ userId ->
                    val response = postApi.getPost(postid = postid, userid = userId)
                    Log.d("GetPost", "repo exec")
                    if(response.isSuccessful){
                        val post = response.body()!!
                        emit(Resource.Success(post))
                    }else{
                        emit(Resource.Error(response.errorBody().toString()))
                    }
                }
            } catch (e: HttpException) {
                Log.d("GetPost", "HttpException")
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
            } catch(e: IOException) {
                Log.d("GetPost", e.stackTraceToString())
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        }
    }

    override suspend fun putPost(
        postPostRequest: PutPostRequest,
        postid: Int
    ): Flow<Resource<PutPostResponse>> {
        return flow {
            try{
                val response = postApi.putPost(postPostRequest = postPostRequest, postid = postid)
                Log.d("PutPost", "repo exec")
                if(response.isSuccessful){
                    val postId = response.body()!!
                    emit(Resource.Success(postId))
                }else{
                    emit(Resource.Error(response.errorBody().toString()))
                }
            } catch (e: HttpException) {
                Log.d("PutPost", "HttpException")
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
            } catch(e: IOException) {
                Log.d("PutPost", e.stackTraceToString())
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        }
    }

    override suspend fun delPost(postid: Int): Flow<Resource<Boolean>> {
        return flow {
            try{
                val response = postApi.delPost(postid = postid)
                Log.d("DelPost", "repo exec")
                if(response.isSuccessful){
                    emit(Resource.Success(true))
                }else{
                    emit(Resource.Error(response.errorBody().toString()))
                }
            } catch (e: HttpException) {
                Log.d("DelPost", "HttpException")
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
            } catch(e: IOException) {
                Log.d("DelPost", e.stackTraceToString())
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        }
    }

    override fun getRecentPost(): Flow<PagingData<Post>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { RecentPostPagingSource(postApi = postApi) }
        ).flow
    }

    override suspend fun getMostViewedPost(): Flow<Resource<List<Post>>> {
        return flow {
            try{
                val response = postApi.getMostViewedPost()
                Log.d("MostViewedPost", "repo exec")
                if(response.isSuccessful){
                    val posts = response.body()!!.map { it.toDomain() }
                    emit(Resource.Success(posts))
                }else{
                    emit(Resource.Error(response.errorBody().toString()))
                }
            } catch (e: HttpException) {
                Log.d("MostViewedPost", "HttpException")
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
            } catch(e: IOException) {
                Log.d("MostViewedPost", e.stackTraceToString())
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        }
    }

    override suspend fun getMyPost(): Flow<PagingData<Post>> {
        val userId = pref.getUserId().first()
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { MyPostPagingSource(postApi = postApi, userId = userId) }
        ).flow
    }

}