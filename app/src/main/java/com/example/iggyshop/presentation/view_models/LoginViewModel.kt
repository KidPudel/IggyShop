package com.example.iggyshop.presentation.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iggyshop.domain.use_cases.GetUserUseCase
import com.example.iggyshop.presentation.views.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val getUserUseCase: GetUserUseCase) : ViewModel() {
    // store that user in a state for jetpack compose
    private val _state = mutableStateOf(value = UserState())
    val state: State<UserState>
        get() = _state

    // get user from use case
    fun getUser(email: String) {
        viewModelScope.launch(context = Dispatchers.IO) {
            getUserUseCase.getUserFromDatabase(email = email).collectLatest { retrievedUser ->
                _state.value = UserState(user = retrievedUser)
            }
        }
    }
}