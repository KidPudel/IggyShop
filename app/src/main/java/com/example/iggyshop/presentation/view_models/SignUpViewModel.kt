package com.example.iggyshop.presentation.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iggyshop.domain.models.User
import com.example.iggyshop.domain.use_cases.AddUserUseCase
import com.example.iggyshop.domain.use_cases.GetUserUseCase
import com.example.iggyshop.presentation.views.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val addUserUseCase: AddUserUseCase
) : ViewModel() {
    private val _state = mutableStateOf(value = UserState())
    val state: State<UserState>
        get() = _state

    fun getUser(email: String) {
        viewModelScope.launch(context = Dispatchers.IO) {
            getUserUseCase.getUserFromDatabase(email = email).collectLatest { retrievedUser ->
                _state.value = UserState(user = retrievedUser)
            }
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch(context = Dispatchers.IO) {
            addUserUseCase.addUserToDatabase(user = user)
        }
    }
}