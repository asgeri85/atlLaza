package net.asgeri.atlproject7.ui.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.asgeri.atlproject7.source.remote.Repository
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var loading = MutableLiveData<Boolean>()
    var isSuccess = MutableLiveData<Boolean>()
    var errorMessage = MutableLiveData<String>()

    fun loginUser(email: String, password: String) {
        loading.value = true
        viewModelScope.launch {
            try {
                val response = repository.loginUser(email, password)
                if (!response.user?.email.isNullOrEmpty()) {
                    loading.value = false
                    isSuccess.value = true
                }
            } catch (e: Exception) {
                errorMessage.value = e.localizedMessage.toString()
                loading.value = false
                isSuccess.value = false
            }
        }
    }

    fun registerUser(email: String, password: String) {
        loading.value = true
        viewModelScope.launch {
            try {
                val response = repository.registerUser(email, password)
                if (!response.user?.email.isNullOrEmpty()) {
                    loading.value = false
                    isSuccess.value = true
                }
            } catch (e: Exception) {
                errorMessage.value = e.localizedMessage.toString()
                loading.value = false
                isSuccess.value = false
            }
        }
    }
}