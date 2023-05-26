package com.example.tinpet

sealed class UiState {
    object Default : UiState()
    object SignedOut : UiState()
    object InProgress : UiState()
    object Error : UiState()
    object SignIn : UiState()
}