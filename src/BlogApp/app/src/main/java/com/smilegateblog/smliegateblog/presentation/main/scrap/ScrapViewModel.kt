package com.smilegateblog.smliegateblog.presentation.main.scrap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.smilegateblog.smliegateblog.domain.usecase.ScrapUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ScrapViewModel @Inject constructor(private val scrapUseCase: ScrapUseCase) : ViewModel() {

    private val _state = MutableStateFlow<ScrapFragmentState>(ScrapFragmentState.Init)
    val state: StateFlow<ScrapFragmentState> get() = _state

    val scrapPost = scrapUseCase.getScrapPostUseCase().cachedIn(viewModelScope)

    private fun setLoading(){
        _state.value = ScrapFragmentState.IsLoading(true)
    }

    private fun hideLoading(){
        _state.value = ScrapFragmentState.IsLoading(false)
    }

    private fun showToast(message: String) {
        _state.value = ScrapFragmentState.ShowToast(message)
    }
}

sealed class ScrapFragmentState {
    object Init : ScrapFragmentState()
    data class IsLoading(val isLoading: Boolean) : ScrapFragmentState()
    data class ShowToast(val message: String) : ScrapFragmentState()
}