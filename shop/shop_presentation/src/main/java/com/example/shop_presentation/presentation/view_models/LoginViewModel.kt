package com.example.shop_presentation.presentation.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop_domain.domain.use_cases.GetUserUseCase
import com.example.shop_presentation.presentation.views.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val getUserUseCase: GetUserUseCase) : ViewModel() {
    // store that user in a state for jetpack compose
    private val _state = mutableStateOf(value = UserState())
    val state: State<UserState>
        get() = _state

    // get user from use case
    suspend fun getUser(email: String) {
        val responseFromDB = viewModelScope.async(context = Dispatchers.IO) {
            getUserUseCase.getUserFromDatabase(email = email).collectLatest { retrievedUser ->
                _state.value = UserState(user = retrievedUser)
            }
        }
        // wait for the response (result)
        responseFromDB.await()
    }
}