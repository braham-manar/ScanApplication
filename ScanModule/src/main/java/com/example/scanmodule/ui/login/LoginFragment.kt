package com.example.scanmodule.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.scanmodule.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_login.*

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLoginResponse()
        loginButtonSetOnClickListener()
    }

    private fun loginButtonSetOnClickListener() {
        btn_login.setOnClickListener {
            viewModel.sendLoginRequest(email.text.toString(),password.text.toString())
            login_progress.visibility = View.VISIBLE
        }
    }

    private fun observeLoginResponse() {
        viewModel.loginLiveData.observe(viewLifecycleOwner) { loginResponse ->
            login_progress.visibility = View.GONE
            Toast.makeText(requireContext(), loginResponse.message, Toast.LENGTH_SHORT).show()
            Log.i("message_test", "there is " +loginResponse.message)
            if (loginResponse.message=="Success"){
                    findNavController().navigate(R.id.scanFragment)
                  }
        }
    }


}





