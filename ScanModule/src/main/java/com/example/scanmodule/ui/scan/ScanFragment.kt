package com.example.scanmodule.ui.scan


import android.Manifest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.zxing.ResultPoint
import com.google.zxing.client.android.BeepManager
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_scan.*


import android.content.pm.PackageManager

import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scanmodule.Constant.CAMERA_PERMISSION_REQUEST_CODE

import com.example.scanmodule.data.dataBase.model.CodeScanEntity
import com.example.scanmodule.ui.scan.adapter.ScanListAdapter
import com.example.scanmodule.util.ScanPhase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import com.example.scanmodule.util.ScanType
import android.content.Context
import android.content.DialogInterface
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.scanmodule.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar


@AndroidEntryPoint

class ScanFragment : Fragment() ,ScanListAdapter.AdapterInteraction{
    lateinit var my_Adapter: ScanListAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var mContext : Context
    private lateinit var beepManager: BeepManager
    private var isScanOnPause : Boolean = false
    private var scanType : String= ScanType.CONFORM.description
    private var scanPhase : String= ScanPhase.RECEPTION.description
    private val viewModel: ScanViewModel by viewModels()
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context

    }

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
        Log.i("checkpermi", "checkpermission ")
        initScan()
        initrecycle()



        viewModel.scanDataLiveData.observe(viewLifecycleOwner, { scanList->
            Log.i("test_live_data_scan", "scan data: " + scanList)

         //   my_Adapter.setDataToAdapter(scanList )
            my_Adapter.submitList(scanList)
            scanNumb.text =  scanList.size.toString()

            /* Description.text =""
                       scanList?.forEach{
                           Description.append(it.code.toString()+"\n")
                       }*/

        })
        my_Adapter?.setAdapterInteractionListener(this)

        chipGroupScanTypeSetOnCheckedStateChangeListener()





        recycle_view?.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                Log.i("test_scroll_rv", "dx: " +dx)
                Log.i("test_scroll_rv", "dy: " + dy)
                Log.i("test_scroll_rv", "-------------------------------------------------")
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

        close_scan.setOnClickListener {

                MaterialAlertDialogBuilder(mContext!!)
                    .setTitle("Close")
                    .setMessage("Do you want to close this ")
                    .setNeutralButton("No", object : DialogInterface.OnClickListener{
                        override fun onClick(Dialog: DialogInterface?, which: Int) {
                            showSnackBar("Later")

                        } })
                    .setPositiveButton("OK", object : DialogInterface.OnClickListener{
                        override fun onClick(Dialog: DialogInterface?, which: Int) {
                            //val bundle : Bundle = Bundle()
                          //  bundle.putParcelable(BUNDLE_HIT_KEY, hit)
                          requireActivity().finish()
                         }
                    })
                    .show() }




        //
    }
     fun initrecycle(){
        linearLayoutManager= LinearLayoutManager(activity)
        recycle_view.layoutManager= linearLayoutManager
        recycle_view?.setHasFixedSize(true)
         my_Adapter= ScanListAdapter()
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
                    val codeScanEntity= CodeScanEntity(code =resultScan.text.toString(),
                        scan_type = scanType , scan_date = System.currentTimeMillis(),
                    scan_phase=scanPhase)
                    viewModel.insertRecordCodeScan(codeScanEntity  )

                if( scanType == ScanType.REFUS.description || scanType == ScanType.RESERVE.description)

                    findNavController().navigate(R.id.photosFragment)
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
    private fun  chipGroupScanTypeSetOnCheckedStateChangeListener() {
        ChipGroupeScanType.setOnCheckedStateChangeListener { group, checkedId ->

           when(group.checkedChipId){
               conformeChip.id -> {
                   scanType = ScanType.CONFORM.description
                   Log.i("test_chip", "conformeChip")
                   //TV_Type.setBackgroundColor(Color.parseColor("#8E24AA"))
               }
               reserveChip.id -> {
                   scanType = ScanType.RESERVE.description

                   Log.i("test_chip", "reserveChip")}
               // TV_Type.setBackgroundColor(Color.RED)}

               refusChip.id -> {
                   scanType = ScanType.REFUS.description
                   Log.i("test_chip", "refusChip")}
                  // TV_Type.setBackgroundColor(Color.parseColor("#EC9009"))}
           }
           }

        Chip_group_function.setOnCheckedStateChangeListener { group, checkedIds ->
            when(group.checkedChipId){
                receptionChip.id -> {
                    scanPhase=ScanPhase.RECEPTION.description
                    Log.i("test_function_chip", "receptionChip")}
                chargementChip.id -> {
                    scanPhase=ScanPhase.CHARGEMENT.description
                    Log.i("test_function_chip", "chargementChip")}
                degroupementChip.id -> {
                    scanPhase=ScanPhase.DEGROUPEMENT.description
                    Log.i("test_function_chip", "degroupementChip")}
                miseEnRayonChip.id -> {
                    scanPhase=ScanPhase.MISE_EN_RAYON.description

                    Log.i("test_function_chip", "miseEnRayonChip")}
                preparationChip.id -> {
                    scanPhase=ScanPhase.PREPARATION.description
                    Log.i("test_function_chip", "preparationChip")}
                livraisonChip.id -> {
                    scanPhase=ScanPhase.LIVRAISON.description
                    Log.i("test_function_chip", "livraisonChip")}

            }

        }
    }

    override fun onItemClick(position: Int) {
        Log.i("test_adapter_inte", "onItemClick: $position")
    }

    override fun onDeleteButtonClicked(scanId: CodeScanEntity) {

            viewModel.delete(scanId)

    }

    override fun onCurrentListChanged() {
        Log.i("test_scroll", "onCurrentListChanged: scan fragment ")
        recycle_view?.smoothScrollToPosition(0)


    }
    // private fun choice function_or_Type(){
    fun showSnackBar(msg:String){

        view?.let { view->
            Snackbar.make(view,msg, Snackbar.LENGTH_SHORT).show()
        }
    }

           }










