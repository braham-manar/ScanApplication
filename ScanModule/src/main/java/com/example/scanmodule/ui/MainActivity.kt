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
       /* save.setOnClickListener{
            val userEntity=UserEntity(name=Description.text.toString())
            viewModel.insertRecords(userEntity)
            Description.setText("")
        }
        observeResultFromDataBase()*/
    }
/*    fun observeResultFromDataBase(){
        viewModel= ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getRecordsObserver().observe(this,object:Observer<List<UserEntity>>{
            override fun onChanged(t: List<UserEntity>?) {
                result.setText("")
                t?.forEach{
                    result.append(it.name
                    +"\n")
                }
            }
        })

    }*/

}
