package com.example.scanmodule.other

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.scanmodule.R
import com.example.scanmodule.data.dataBase.model.ResponsabilityEntity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_photos.*
import pl.aprilapps.easyphotopicker.DefaultCallback
import pl.aprilapps.easyphotopicker.EasyImage
import pl.aprilapps.easyphotopicker.EasyImage.ImageSource
import java.io.File


@AndroidEntryPoint
class photosFragment : Fragment() {
    val REQUEST_CODE_CAMERA = 10
    val REQUEST_CODE_GALLERY = 11
    private val viewModelPhoto: PhotosViewModel by viewModels()
    private val items = arrayOf("Camera", "Gallery")



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



        photoIV_1.setOnClickListener(View.OnClickListener { openImage()

        })
        photoIV_2.setOnClickListener(View.OnClickListener { openImage()

        })
        photoIV_3.setOnClickListener(View.OnClickListener { openImage()

        })
        btn_close_litigation.setOnClickListener {
            Log.i("test_click", "setOnClickListener: ")
            findNavController().popBackStack()

         /*   MaterialAlertDialogBuilder(requireContext())
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
                       // requireActivity().finish()
                        findNavController().navigateUp()
                    }
                })
                .show()*/ }


    }
    fun showSnackBar(msg:String){

        view?.let { view->
            Snackbar.make(view,msg, Snackbar.LENGTH_SHORT).show()
        }
    }
   /* Log.i("check", "checkCameraPermission")
    val easyImage: EasyImage = EasyImage.Builder(requireContext())
        //  easyImage.setMemento(memento) // Setting to true will cause taken pictures to show up in the device gallery, DEFAULT false
        .setCopyImagesToPublicGalleryFolder(false) // Sets the name for images stored if setCopyImagesToPublicGalleryFolder = true
        .setFolderName("EasyImage sample") // Allow multiple picking
        .allowMultiple(true)
        .build()
    easyImage.openCameraForImage(photosFragment)*/

    private fun openImage() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Options")
        builder.setItems(items, DialogInterface.OnClickListener { dialogInterface, i ->
            if (items.get(i).equals("Camera")) {
                EasyImage.openCamera(requireActivity(),REQUEST_CODE_CAMERA)
            } else if (items.get(i).equals("Gallery")) {
                EasyImage.openGallery(requireActivity(), REQUEST_CODE_GALLERY)
            }
        })
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

private fun initSpinner(listResponsabilité :  MutableList<ResponsabilityEntity>){
    //  val newList : List<ResponsabilityEntity> =  listResponsabilité.add(ResponsabilityEntity(1,"","selectionnez") )

        val adapter: ArrayAdapter<ResponsabilityEntity> = ArrayAdapter<ResponsabilityEntity>(
            requireContext(),
            android.R.layout.simple_spinner_item, listResponsabilité
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

    responsabilité_spinner.adapter = adapter
}
     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        EasyImage.handleActivityResult(
            requestCode,
            resultCode,
            data,
            requireActivity(),
            object : DefaultCallback() {
                override fun onImagePicked(imageFile: File, source: ImageSource?, type: Int) {
                    Log.i("showimage", "showimage type ")
                    when (type) {

                                REQUEST_CODE_CAMERA -> {
                            Glide.with(this@photosFragment)
                                .load(imageFile.absolutePath)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(test_photo)
                            Log.i("showimage", "showimage ")

                        }
                        REQUEST_CODE_GALLERY -> {
                            Glide.with(this@photosFragment)
                                .load(imageFile.absolutePath)
                                .centerCrop()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(photoIV_1)

                        }
                    }
                }
            })
    }
    }
