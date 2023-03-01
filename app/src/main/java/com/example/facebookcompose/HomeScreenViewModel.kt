package com.example.facebookcompose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class HomeScreenState {
    object Loading : HomeScreenState()
    data class Loaded(
        val avatarUrl : String
    ) : HomeScreenState()
}

class HomeScreenViewModel : ViewModel() {
    private val mutableState = MutableStateFlow<HomeScreenState>(
        HomeScreenState.Loading
    )
    val state = mutableState.asStateFlow()
}

