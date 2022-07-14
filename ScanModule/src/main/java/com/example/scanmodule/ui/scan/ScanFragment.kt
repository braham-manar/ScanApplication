package com.example.scanmodule.ui.scan


import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Camera
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.scanmodule.dataBase.UserEntity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.zxing.ResultPoint
import com.google.zxing.client.android.BeepManager
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_scan.*
import java.nio.channels.DatagramChannel.open
import java.nio.channels.Pipe.open
import java.nio.channels.Selector.open
import java.nio.channels.ServerSocketChannel.open
import java.nio.channels.SocketChannel.open
import androidx.core.app.ActivityCompat.startActivityForResult


import android.app.Activity
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings

import android.widget.RadioButton

import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scanmodule.Constant
import com.example.scanmodule.Constant.CAMERA_PERMISSION_REQUEST_CODE
import com.example.scanmodule.NewAdapter
import com.example.scanmodule.dataBase.CodeScanEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint

class ScanFragment : Fragment() {
    lateinit var my_Adapter:NewAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var beepManager: BeepManager
    private var isScanOnPause : Boolean = false

    private val viewModel: ScanViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.example.scanmodule.R.layout.fragment_scan, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkCameraPermission()
        initScan()


        viewModel.scanDataLiveData.observe(viewLifecycleOwner, { scanList->
            Log.i("test_live_data", "scan data: " + scanList.size)
            initrecycle()

            my_Adapter.setDataToAdapter(scanList as ArrayList<CodeScanEntity>)

            /* Description.text =""
                       scanList?.forEach{
                           Description.append(it.code.toString()+"\n")
                       }*/

        })
    }
     fun initrecycle(){
        linearLayoutManager= LinearLayoutManager(activity)
        recycle_view.layoutManager= linearLayoutManager
        recycle_view?.setHasFixedSize(true)
         my_Adapter= NewAdapter()
         recycle_view.adapter=my_Adapter
       // my_Adapter.setAdapterListener(this)

    }

    private fun initScan() {
        run {
            val integrator = IntentIntegrator.forSupportFragment(this)
            integrator.setOrientationLocked(false)
            beepManager = BeepManager(requireActivity())
            scan_barcode?.initializeFromIntent(requireActivity().intent)
            scan_barcode?.decodeContinuous(barcodeCallback)
        }
    }


    private val barcodeCallback = object : BarcodeCallback {
        override fun barcodeResult(result: BarcodeResult?) {
            result?.let { resultScan ->
                if (!isScanOnPause){
                    beepManager.playBeepSound()
                    pauseScan()
                    Log.i("test_scan", "new scan: " + resultScan.text)
                    val codeScanEntity= CodeScanEntity(code=resultScan.text.toString())
                    viewModel.insertRecordCodeScan(codeScanEntity  )

                }

            }
        }


        override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {}
    }

    private fun pauseScan(){
        isScanOnPause = true

        lifecycleScope.launch(Dispatchers.IO){
            delay(1000)
            isScanOnPause = false
        }




    }

    private fun checkCameraPermission(){
        if (ContextCompat.checkSelfPermission(requireActivity(),Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_REQUEST_CODE
            );
        }
    }


    override fun onResume() {
        super.onResume()
        scan_barcode?.resume()
    }

}








