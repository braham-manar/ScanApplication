package com.example.myscanapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.example.scanmodule.ui.MainActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_scan.setOnClickListener {
            goToScanActivity()
        }




    }

    fun goToScanActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}