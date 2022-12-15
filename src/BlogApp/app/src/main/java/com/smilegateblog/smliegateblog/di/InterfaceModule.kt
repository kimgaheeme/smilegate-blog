package com.smilegateblog.smliegateblog.di

import com.smilegateblog.smliegateblog.data.api.CommentApi
import com.smilegateblog.smliegateblog.data.api.LoginApi
import com.smilegateblog.smliegateblog.data.api.PostApi
import com.smilegateblog.smliegateblog.data.api.ScrapApi
import com.smilegateblog.smliegateblog.data.pref.PrefDataSource
import com.smilegateblog.smliegateblog.data.pref.model.UserPref
import com.smilegateblog.smliegateblog.data.repository.CommentRepositoryImpl
import com.smilegateblog.smliegateblog.data.repository.LoginRepositoryImpl
import com.smilegateblog.smliegateblog.data.repository.PostRepositoryImpl
import com.smilegateblog.smliegateblog.data.repository.ScrapRepositoryImpl
import com.smilegateblog.smliegateblog.domain.repository.CommentRepository
import com.smilegateblog.smliegateblog.domain.repository.LoginRepository
import com.smilegateblog.smliegateblog.domain.repository.PostRepository
import com.smilegateblog.smliegateblog.domain.repository.ScrapRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [AppModule::class])
@InstallIn(SingletonComponent::class)
class InterfaceModule {

    @Singleton
    @Provides
    fun provideLoginRepository(loginApi: LoginApi, pref: PrefDataSource) : LoginRepository {
        return LoginRepositoryImpl(loginApi, pref)
    }

    @Singleton
    @Provides
    fun providePostRepository(postApi: PostApi, pref: PrefDataSource) : PostRepository {
        return PostRepositoryImpl(postApi, pref)
    }

    @Singleton
    @Provides
    fun provideScrapRepository(scrapApi: ScrapApi, pref: PrefDataSource) : ScrapRepository {
        return ScrapRepositoryImpl(scrapApi, pref)
    }

    @Singleton
    @Provides
    fun provideCommentRepository(commentApi: CommentApi, pref: PrefDataSource) : CommentRepository {
        return CommentRepositoryImpl(commentApi, pref)
    }
}