package com.example.scanmodule.ui.scan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.scanmodule.R
import com.example.scanmodule.dataBase.UserEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_scan.*


@AndroidEntryPoint

class ScanFragment : Fragment() {

    lateinit var viewModel: ScanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        saveButtonSetOnClickListener()
        observeResultFromDataBase()
    }

    private fun saveButtonSetOnClickListener(){
        save.setOnClickListener{
            val userEntity= UserEntity(name=Description.text.toString())
            viewModel.insertRecords(userEntity)
            Description.setText("")
        }
    }
    private fun observeResultFromDataBase(){
        viewModel= ViewModelProvider(this)[ScanViewModel::class.java]
        viewModel.getRecordsObserver().observe(viewLifecycleOwner,
            { resultList ->
                tv_result.text = ""
                resultList?.forEach{
                    tv_result.append(it.name+"\n")
                }
            })

    }
    }


