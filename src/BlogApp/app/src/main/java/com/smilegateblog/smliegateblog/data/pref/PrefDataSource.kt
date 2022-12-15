package com.smilegateblog.smliegateblog.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.smilegateblog.smliegateblog.data.pref.model.UserPref
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("settings")

class PrefDataSource @Inject constructor(@ApplicationContext val context: Context){

    companion object {
        val LOGIN_USER_NICKNAME = stringPreferencesKey("login_user_nickname")
        val LOGIN_USER_ID = intPreferencesKey("login_user_id")
        val LOGIN_USER_EMAIL = stringPreferencesKey("login_user_email")
    }

    suspend fun logoutUser() {
        context.dataStore.edit { settings ->
            settings[LOGIN_USER_ID] = 0
            settings[LOGIN_USER_EMAIL] = ""
            settings[LOGIN_USER_NICKNAME] = ""
        }
    }


    suspend fun setUser(user: UserPref) {
        context.dataStore.edit { settings ->
            settings[LOGIN_USER_ID] = user.userId
            settings[LOGIN_USER_EMAIL] = user.email
            settings[LOGIN_USER_NICKNAME] = user.nickname
        }
    }

    fun getUserId(): Flow<Int> {
        return context.dataStore.data.map {
            it[LOGIN_USER_ID] ?: 0
        }
    }

    fun getUser(): Flow<UserPref> {
        return context.dataStore.data.map { preference ->
            val userId = preference[LOGIN_USER_ID] ?: 0
            val email = preference[LOGIN_USER_EMAIL] ?: ""
            val nickname = preference[LOGIN_USER_NICKNAME] ?: ""

            UserPref(userId = userId, email = email, nickname = nickname)
        }
    }
}