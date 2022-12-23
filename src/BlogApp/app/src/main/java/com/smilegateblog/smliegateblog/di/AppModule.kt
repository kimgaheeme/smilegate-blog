package com.smilegateblog.smliegateblog.di

import com.smilegateblog.smliegateblog.BuildConfig
import com.smilegateblog.smliegateblog.data.api.CommentApi
import com.smilegateblog.smliegateblog.data.api.LoginApi
import com.smilegateblog.smliegateblog.data.api.PostApi
import com.smilegateblog.smliegateblog.data.api.ScrapApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder().apply {
            addConverterFactory(GsonConverterFactory.create())
            baseUrl(BuildConfig.API_BASE_URL)
        }.build()
    }

    @Singleton
    @Provides
    fun provideLoginApi(retrofit: Retrofit) : LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    @Singleton
    @Provides
    fun providePostApi(retrofit: Retrofit) : PostApi {
        return retrofit.create(PostApi::class.java)
    }

    @Singleton
    @Provides
    fun provideScrapApi(retrofit: Retrofit) : ScrapApi {
        return retrofit.create(ScrapApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCommentApi(retrofit: Retrofit) : CommentApi {
        return retrofit.create(CommentApi::class.java)
    }
}