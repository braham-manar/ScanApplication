package com.example.scanmodule.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.scanmodule.R
import com.example.scanmodule.ui.scan.ScanViewModel
import com.example.scanmodule.util.getTokenFromPref
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

    //    viewModel.getResponsibilité("yU1qVziJ+jlB4+bNtteBt/82YAxokE5d6vYvntwfP/EN760B0cnfMSnnCyM91WLMJtE=")
       val token = getTokenFromPref(this)
        Log.i("test_pref", "getTokenFromPref: "+token)
        viewModel.getResponsibilité(token)


}}
