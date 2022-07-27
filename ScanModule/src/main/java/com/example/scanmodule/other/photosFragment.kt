package com.example.scanmodule.other

import android.hardware.Camera
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.scanmodule.R
import com.example.scanmodule.data.Api.model.Responsibility
import com.example.scanmodule.data.dataBase.model.ResponsabilityEntity
import com.example.scanmodule.ui.scan.ScanViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_photos.*
import java.util.ArrayList


@AndroidEntryPoint
class photosFragment : Fragment() {
    private val viewModelPhoto: PhotosViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("test_photo", " onViewCreated")
        viewModelPhoto.responsibilityDataLiveData.observe(viewLifecycleOwner, { observeList->
            Log.i("test_live_data_responsibility", "resp " + observeList)
            initSpinner(observeList as MutableList<ResponsabilityEntity>)
       })


        photoIV_1.setOnClickListener{
            Log.i("test_photo", " photoIV_1")

            Log.i("check", "checkCameraPermission")
          //  getCameraInstance()
        }
    }
      /* private fun getCameraInstance(): Camera? {
            return try {
                Camera.open() // attempt to get a Camera instance
            } catch (e: Exception) {
                // Camera is not available (in use or does not exist)
                null // returns null if camera is unavailable
            }
        }
*/

private fun initSpinner(listResponsabilité :  MutableList<ResponsabilityEntity>){
    //  val newList : List<ResponsabilityEntity> =  listResponsabilité.add(ResponsabilityEntity(1,"","selectionnez") )

        val adapter: ArrayAdapter<ResponsabilityEntity> = ArrayAdapter<ResponsabilityEntity>(
            requireContext(),
            android.R.layout.simple_spinner_item, listResponsabilité
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

    responsabilité_spinner.adapter = adapter
}
    }
