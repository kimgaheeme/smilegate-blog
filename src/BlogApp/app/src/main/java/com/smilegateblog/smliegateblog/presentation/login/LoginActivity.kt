package com.smilegateblog.smliegateblog.presentation.login

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.smilegateblog.smliegateblog.R
import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.databinding.ActivityLoginBinding
import com.smilegateblog.smliegateblog.databinding.ActivityMainBinding
import com.smilegateblog.smliegateblog.domain.model.User
import com.smilegateblog.smliegateblog.presentation.common.isEmail
import com.smilegateblog.smliegateblog.presentation.common.showGenericAlertDialog
import com.smilegateblog.smliegateblog.presentation.common.showToast
import com.smilegateblog.smliegateblog.presentation.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private val viewModel : LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        login()
        //goToMainActivity()
    }

    private fun login(){
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val nickname = binding.nicknameEditText.text.toString().trim()
            if (validate(email, nickname)){
                viewModel.login(LoginRequest(nickname, email))
            }
        }
    }

    private fun observe() {
        viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleState(state) }
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: LoginActivityState){
        when(state){
            is LoginActivityState.ShowToast -> showToast(state.message)
            is LoginActivityState.Loading -> handleLoading(state.isLoading)
            is LoginActivityState.Init -> Unit
            is LoginActivityState.ErrorLogin -> handleErrorLogin(state.rawResponse)
            is LoginActivityState.SuccessLogin -> handleSuccessLogin(state.user)
        }
    }

    private fun handleLoading(isLoading: Boolean){
        binding.loginButton.isEnabled = !isLoading
        //로그인 프로그래스바 수정필요
    }

    private fun handleSuccessLogin(user: User){
        //datastore에 저장할 것 수정필요
    }

    private fun handleErrorLogin(rawResponse: String){
        showGenericAlertDialog(rawResponse)
    }
    private fun goToMainActivity(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


    private fun setEmailError(e: String?){
        binding.emailInput.error = e
    }

    private fun setNicknameError(e: String?){
        binding.nicknameInput.error = e
    }

    private fun resetAllError(){
        setEmailError(null)
        setNicknameError(null)
    }

    private fun validate(email: String, nickname: String) : Boolean{
        resetAllError()

        if(!email.isEmail()){
            setEmailError(getString(R.string.error_email_not_valid))
            return false
        }

        return true
    }
}