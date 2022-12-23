package com.smilegateblog.smliegateblog.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.smilegateblog.smliegateblog.data.api.CommentApi
import com.smilegateblog.smliegateblog.data.api.LoginApi
import com.smilegateblog.smliegateblog.data.dto.comment.*
import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.data.dto.login.MyInfoResponse
import com.smilegateblog.smliegateblog.data.dto.post.GetRecentPostResponseItem
import com.smilegateblog.smliegateblog.data.pagingsource.CommentPagingSource
import com.smilegateblog.smliegateblog.data.pagingsource.RecentPostPagingSource
import com.smilegateblog.smliegateblog.data.pref.PrefDataSource
import com.smilegateblog.smliegateblog.domain.model.User
import com.smilegateblog.smliegateblog.domain.model.toDomain
import com.smilegateblog.smliegateblog.domain.repository.CommentRepository
import com.smilegateblog.smliegateblog.domain.repository.LoginRepository
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val commentApi: CommentApi,
    private val pref: PrefDataSource
) : CommentRepository {


    override fun getComments(postid: Int): Flow<PagingData<GetCommentsResponseItem>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { CommentPagingSource(commentApi = commentApi, postid = postid) }
        ).flow
    }

    override suspend fun postComment(
        postid: Int,
        postCommentRequest: PostCommentRequest
    ): Flow<Resource<PostCommentResponse>> {
        return flow {
            try{
                val userId = pref.getUserId().first()
                val response = commentApi.postComment(userId = userId, postid = postid, postCommentRequest = postCommentRequest)
                Log.d("PostComment", "repo exec")
                if(response.isSuccessful){
                    val comment = response.body()!!
                    emit(Resource.Success(comment))
                }else{
                    emit(Resource.Error(response.errorBody().toString()))
                }
            } catch (e: HttpException) {
                Log.d("PostComment", "HttpException")
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
            } catch(e: IOException) {
                Log.d("PostComment", e.stackTraceToString())
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        }
    }

    override suspend fun putComment(
        commentid: Int,
        putCommentRequest: PutCommentRequest
    ): Flow<Resource<PutCommentResponse>> {
        return flow {
            try{
                val response = commentApi.putComment(commentid = commentid, putCommentRequest = putCommentRequest)
                Log.d("PutComment", "repo exec")
                if(response.isSuccessful){
                    val comment = response.body()!!
                    emit(Resource.Success(comment))
                }else{
                    emit(Resource.Error(response.errorBody().toString()))
                }
            } catch (e: HttpException) {
                Log.d("PutComment", "HttpException")
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
            } catch(e: IOException) {
                Log.d("PutComment", e.stackTraceToString())
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        }
    }


    override suspend fun delComment(commentid: Int): Flow<Resource<Boolean>> {
        return flow {
            try{
                val response = commentApi.delComment(commentid = commentid)
                Log.d("DelComment", "repo exec")
                if(response.isSuccessful){
                    emit(Resource.Success(true))
                }else{
                    emit(Resource.Error(response.errorBody().toString()))
                }
            } catch (e: HttpException) {
                Log.d("DelComment", "HttpException")
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
            } catch(e: IOException) {
                Log.d("DelComment", e.stackTraceToString())
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        }
    }

}