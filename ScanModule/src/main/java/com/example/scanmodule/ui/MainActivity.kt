package com.example.scanmodule.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.scanmodule.R
import com.example.scanmodule.ui.scan.ScanViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ScanViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)


}}
