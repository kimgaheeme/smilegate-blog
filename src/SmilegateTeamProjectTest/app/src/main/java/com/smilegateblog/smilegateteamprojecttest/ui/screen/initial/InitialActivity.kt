package com.smilegateblog.smilegateteamprojecttest.ui.screen.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.smilegateblog.smilegateteamprojecttest.ui.screen.initial.LoginRoot
import com.smilegateblog.smilegateteamprojecttest.ui.screen.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InitialActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginRoot {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}
