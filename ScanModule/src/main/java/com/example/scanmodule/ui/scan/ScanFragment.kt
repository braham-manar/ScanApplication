package com.example.scanmodule.ui.scan


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
import com.example.scanmodule.Constant


@AndroidEntryPoint

class ScanFragment : Fragment() {
   // lateinit var mContext: Context
    //override fun onAttach(context: Context) {
        //super.onAttach(context)
       // mContext = context

   // }


    private val permissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { granted ->
            granted.entries.forEach {
                when (it.value) {
                    true -> {
                        // Call whatever you want to do when someone allow the permission.
                    }
                    false -> {
                        showPermissionSettingsAlert(requireContext())
                    }
                }
            }
        }

    private fun showPermissionSettingsAlert(requireContext: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Grant Permission")
        builder.setMessage("You have rejected the Storage permission for the application. As it is absolutely necessary for the app to perform you need to enable it in the settings of your device. Please select \"Go to settings\" to go to application settings in your device.")
        builder.setPositiveButton("Allow") { dialog, which ->
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            val uri = Uri.fromParts("package", context?.packageName, null)
            intent.data = uri
            context?.startActivity(intent)
        }
        builder.setNeutralButton("Deny") { dialog, which ->

            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
    private lateinit var beepManager: BeepManager

    lateinit var viewModel: ScanViewModel
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
                Log.i("test_scan", "new scan: " + resultScan.text)
            }
        }

        override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {}
    }

    private fun checkCameraPermission() {

        if (allPermissionGranted()) {
            Toast.makeText(
                requireContext(),
                "we have all permission",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            ActivityCompat.requestPermissions(
                requireContext() as Activity,
                Constant.REQUIRED_PERMISSION,
                Constant.REQUEST_CODE_PERMISSION
            )

        }

    }


    override fun onResume() {
        super.onResume()
        scan_barcode?.resume()
    }


   private fun allPermissionGranted() =
        Constant.REQUIRED_PERMISSION.all {
            ContextCompat.checkSelfPermission(
                requireContext(), it
            ) == PackageManager.PERMISSION_GRANTED
        }
}








