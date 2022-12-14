package com.smilegateblog.smliegateblog.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.smilegateblog.smliegateblog.data.api.ScrapApi
import com.smilegateblog.smliegateblog.data.dto.scrap.GetScrapPostItem
import com.smilegateblog.smliegateblog.data.dto.scrap.PostScrapResponse
import com.smilegateblog.smliegateblog.data.pagingsource.ScrapPostPagingSource
import com.smilegateblog.smliegateblog.domain.repository.ScrapRepository
import com.smilegateblog.smliegateblog.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ScrapRepositoryImpl @Inject constructor(private val scrapApi: ScrapApi) : ScrapRepository {


    override suspend fun postScrap(userId: Int, postid: Int): Flow<Resource<PostScrapResponse>> {
        return flow {
            try{
                val response = scrapApi.postScrap(userId = userId, postid = postid)
                Log.d("PostScrap", "repo exec")
                if(response.isSuccessful){
                    val scrapId = response.body()!!
                    emit(Resource.Success(scrapId))
                }else{
                    emit(Resource.Error(response.errorBody().toString()))
                }
            } catch (e: HttpException) {
                Log.d("PostScrap", "HttpException")
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
            } catch(e: IOException) {
                Log.d("PostScrap", e.stackTraceToString())
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        }
    }

    override suspend fun delScrap(userId: Int, postid: Int): Flow<Resource<Void>> {
        return flow {
            try{
                val response = scrapApi.delScrap(userId = userId, postid = postid)
                Log.d("DelScrap", "repo exec")
                if(response.isSuccessful){
                    val v = response.body()!!
                    emit(Resource.Success(v))
                }else{
                    emit(Resource.Error(response.errorBody().toString()))
                }
            } catch (e: HttpException) {
                Log.d("PostScrap", "HttpException")
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
            } catch(e: IOException) {
                Log.d("PostScrap", e.stackTraceToString())
                emit(Resource.Error("Couldn't reach server. Check your internet connection."))
            }
        }
    }

    override suspend fun getScrapPost(userid: Int): Flow<PagingData<GetScrapPostItem>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = { ScrapPostPagingSource(scrapApi = scrapApi, userid = userid) }
        ).flow
    }

}