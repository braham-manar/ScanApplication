package com.example.scanmodule.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.scanmodule.R
import com.example.scanmodule.ui.MainActivity
import com.example.scanmodule.util.SharedPreferencesUtil
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.android.synthetic.main.activity_login.*
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }


    override fun onResume() {
        super.onResume()
        observeLoginResponse()
        loginButtonSetOnClickListener()

    }

    private fun loginButtonSetOnClickListener() {
        btn_login.setOnClickListener {
            viewModel.sendLoginRequest(email.text.toString(),password.text.toString())
            login_progress.visibility=View.VISIBLE
        }

        }

        private fun observeLoginResponse() {
            viewModel.loginLiveData.observe(this) { loginResponse ->
                login_progress.visibility = View.GONE
                Toast.makeText(this, loginResponse.message, Toast.LENGTH_SHORT).show()
                Log.i("message_test", "there is " +loginResponse.message)
                Log.i("message_test", "there is " + (loginResponse.Response?.value ?: ""))
                //-------------------------
                SharedPreferencesUtil.saveTokenToPreference (applicationContext , loginResponse.Response?.value)
                //-------------------------

                if (loginResponse.message=="Success"){
                    goToScanActivity()
                }
            }
        }
        fun goToScanActivity() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }





