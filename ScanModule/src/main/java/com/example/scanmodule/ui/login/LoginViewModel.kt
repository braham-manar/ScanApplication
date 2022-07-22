package com.example.scanmodule.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scanmodule.data.Api.model.LoginResponse
import com.example.scanmodule.data.Api.model.LoginRequest
import com.example.scanmodule.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository): ViewModel() {

    private val _loginLiveData = MutableLiveData<LoginResponse>()
    val loginLiveData: LiveData<LoginResponse>  = _loginLiveData

    fun sendLoginRequest(mail : String , password : String){
        viewModelScope.launch(Dispatchers.IO) {
            loginRepository.loginRequest(LoginRequest(mail,password)).apply {
                Log.i("test_lo", "loginRequest: "+ (this.Response?.value ?: ""))
                _loginLiveData.postValue(this)
            }
        }


    }

}