package com.example.scanmodule.other

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.scanmodule.R
import com.example.scanmodule.data.dataBase.model.ResponsabilityEntity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_photos.*
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import pl.aprilapps.easyphotopicker.MediaFile
import pl.aprilapps.easyphotopicker.MediaSource
import java.io.File
import kotlin.math.log


@AndroidEntryPoint
class photosFragment : Fragment() {
    val REQUEST_CODE_CAMERA = 10
    val REQUEST_CODE_GALLERY = 11
    private val viewModelPhoto: PhotosViewModel by viewModels()
    private val items = arrayOf("Camera", "Gallery")

    private var easyImage : EasyImage? = null
    private var imageToShow : ImageView? = null



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

        initEasyImageLibrary()
        Log.i("test_photo", " onViewCreated")
        viewModelPhoto.responsibilityDataLiveData.observe(viewLifecycleOwner, { observeList->
            Log.i("test_live_data_responsibility", "resp " + observeList)
            initSpinner(observeList as MutableList<ResponsabilityEntity>)
       })

        btn_close_litigation.setOnClickListener {
            Log.i("test_click", "setOnClickListener: ")

            findNavController().navigateUp()
            Log.i("navigateUp", "navigateUp: ")
                }


        photoIV_1.setOnClickListener(View.OnClickListener { openImage()
            imageToShow = photoIV_1
        })
        photoIV_2.setOnClickListener(View.OnClickListener { openImage()
            imageToShow = photoIV_2

        })
        photoIV_3.setOnClickListener { openImage()
            imageToShow = photoIV_3

        }
  /*photoIV_3.setOnLongClickListener {
      imageToShow?.setPadding(20,20,20,20)

      true
  }*/

    }


    private fun openImage() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Options")
        builder.setItems(items, DialogInterface.OnClickListener { dialogInterface, i ->
            if (items.get(i).equals("Camera")) {
                easyImage?.openCameraForImage(this)
            } else if (items.get(i).equals("Gallery")) {
                easyImage?.openGallery(this)
            }
        })
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    private fun initSpinner(listResponsabilité :  MutableList<ResponsabilityEntity>){


        val adapter: ArrayAdapter<ResponsabilityEntity> = ArrayAdapter<ResponsabilityEntity>(
            requireContext(),
            android.R.layout.simple_spinner_item, listResponsabilité
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

    responsabilité_spinner.adapter = adapter
}
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        easyImage?.handleActivityResult(
            requestCode,
            resultCode,
            data,
            requireActivity(),
            object : DefaultCallback() {
                override fun onMediaFilesPicked(
                    imageFiles: Array<MediaFile>,
                    source: MediaSource
                ) {
                    updateDesign()
                    showImage(imageFiles)

                    Log.i("padding_test", "padding_test: ")
                }

                override fun onImagePickerError(error: Throwable, source: MediaSource) {
                    error.printStackTrace()
                }

                override fun onCanceled(source: MediaSource) {}
            })
    }

        private fun initEasyImageLibrary() {
        easyImage = EasyImage.Builder(requireContext())
            .setCopyImagesToPublicGalleryFolder(true)
            .setFolderName("scan library")
            .allowMultiple(true)
            .build()
    }


private fun updateDesign(){
    imageToShow?.setPadding(0,0,0,0)


}
private fun showImage(imageFiles : Array<MediaFile>){
    imageToShow?.let {
        Glide.with(this@photosFragment)
        .load(imageFiles[0].file)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(it)
    }
}





}
