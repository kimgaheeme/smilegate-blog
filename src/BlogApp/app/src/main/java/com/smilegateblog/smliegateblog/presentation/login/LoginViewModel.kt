package com.smilegateblog.smliegateblog.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smilegateblog.smliegateblog.data.dto.login.LoginRequest
import com.smilegateblog.smliegateblog.domain.model.User
import com.smilegateblog.smliegateblog.domain.usecase.LoginUseCase
import com.smilegateblog.smliegateblog.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _state = MutableStateFlow<LoginActivityState>(LoginActivityState.Init)
    val state : StateFlow<LoginActivityState> get() = _state

    init {
        isLogged()
    }

    private fun isLogged(){
        viewModelScope.launch {
            loginUseCase.checkLoginUseCase().collect{ result ->
                if(result.data == true) {
                    _state.value = LoginActivityState.SuccessLogin(User())
                }
            }
        }

    }

    private fun setLoading(){
        _state.value = LoginActivityState.Loading(true)
    }

    private fun hideLoading(){
        _state.value = LoginActivityState.Loading(false)
    }

    private fun showToast(message: String) {
        _state.value = LoginActivityState.ShowToast(message)
    }

    private fun successLogin(user: User?) {
        _state.value = LoginActivityState.SuccessLogin(user = user)
    }

    private fun errorLogin(message: String) {
        _state.value = LoginActivityState.ErrorLogin(message)
    }

    fun login(loginRequest: LoginRequest) {
        Log.d("Login", "login function exec")
        viewModelScope.launch {
            loginUseCase.loginUseCase.invoke(loginRequest)
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    hideLoading()
                    showToast(exception.stackTraceToString())

                }
                .collect { result ->
                    hideLoading()
                    when(result) {
                        is Resource.Success -> successLogin(result.data?: User())
                        is Resource.Error -> errorLogin(result.message?: "error in login function of loginViewModel")
                        else -> setLoading()
                    }

                }
        }

    }
}

sealed class LoginActivityState {
    object Init : LoginActivityState()
    data class Loading(val isLoading: Boolean) : LoginActivityState()
    data class ShowToast(val message: String) : LoginActivityState()
    data class SuccessLogin(val user: User?) : LoginActivityState()
    data class ErrorLogin(val rawResponse: String) : LoginActivityState()
}