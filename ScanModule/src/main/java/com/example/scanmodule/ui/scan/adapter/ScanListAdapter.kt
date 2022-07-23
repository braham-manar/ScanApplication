package com.example.scanmodule.ui.scan.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.scanmodule.R
import com.example.scanmodule.data.dataBase.model.CodeScanEntity
import com.example.scanmodule.util.ScanPhase
import com.example.scanmodule.util.ScanType


class ScanListAdapter : ListAdapter<CodeScanEntity,
        RecyclerView.ViewHolder>(ScanDiffCallback()) {

    private lateinit  var mListener : AdapterInteraction
    fun setAdapterInteractionListener (listner: ScanListAdapter.AdapterInteraction){
        mListener= listner

    }
    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onCurrentListChanged(
        previousList: MutableList<CodeScanEntity>,
        currentList: MutableList<CodeScanEntity>
    ) {
        super.onCurrentListChanged(previousList, currentList)
        Log.i("test_onCurrent", "onCurrentListChanged: ")
        mListener?.onCurrentListChanged()
    }


    interface AdapterInteraction {
        fun onItemClick(position: Int)
       // fun onDeleteButtonClicked(code: String)}
        fun onDeleteButtonClicked(i: CodeScanEntity)
         fun onCurrentListChanged ()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScannedObjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
        return ScannedObjectViewHolder(view)
    }


    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rvHolder = (holder as ScannedObjectViewHolder)

        val currentScan = currentList[position]
        rvHolder.tvScanValue.text = currentScan.code

     when(currentScan.scan_type)   {
         ScanType.CONFORM.description -> {
             rvHolder.tvScanType.setBackgroundColor(Color.parseColor( ScanType.CONFORM.color ))
             rvHolder.tvScanType.text = ScanType.CONFORM.code

         }
         ScanType.RESERVE.description -> {
             rvHolder.tvScanType.setBackgroundColor(Color.parseColor( ScanType.RESERVE.color ))
             rvHolder.tvScanType.text = ScanType.RESERVE.code


         }
         ScanType.REFUS.description ->{
             rvHolder.tvScanType.setBackgroundColor(Color.parseColor( ScanType.REFUS.color ))
             rvHolder.tvScanType.text = ScanType.REFUS.code


         } }
        when(currentScan.scan_phase)   {
         ScanPhase.RECEPTION.description -> {
             rvHolder.tvScanPhase.text = ScanPhase.RECEPTION.code
         }
            ScanPhase.CHARGEMENT.description -> {
                rvHolder.tvScanPhase.text = ScanPhase.CHARGEMENT.code
            }
            ScanPhase.DEGROUPEMENT.description -> {
            rvHolder.tvScanPhase.text = ScanPhase.DEGROUPEMENT.code
        }
            ScanPhase.MISE_EN_RAYON.description -> {
                rvHolder.tvScanPhase.text = ScanPhase.MISE_EN_RAYON.code
            }
            ScanPhase.PREPARATION.description -> {
                rvHolder.tvScanPhase.text = ScanPhase.PREPARATION.code
            }
            ScanPhase.LIVRAISON.description -> {
                rvHolder.tvScanPhase.text = ScanPhase.LIVRAISON.code
            }



     }
      /*  ChipGroupeScanType?.setOnCheckedStateChangeListener { group, checkedId ->
            when(group.checkedChipId){
                conformChip?.id -> { Log.i("test_chip", "conformeChip")
                    tvTypeHolder.tvScanType.text="AR"
                    tvTypeHolder.tvScanType.setBackgroundColor(Color.parseColor("#8E24AA"))
                }}}
*/
        holder.itemView.setOnClickListener {  }
        holder.IVdelete.setOnClickListener {
            mListener.onDeleteButtonClicked(currentScan)

        }


    }



    class ScannedObjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvScanValue = itemView.findViewById(R.id.TV_Code_Scan) as TextView
        val tvScanType = itemView.findViewById(R.id.TV_Type) as TextView
        val tvScanPhase = itemView.findViewById(R.id.TV_Phase) as TextView
        val IVdelete = itemView.findViewById(R.id.IVdelete) as ImageView


    }
    private class ScanDiffCallback : DiffUtil.ItemCallback<CodeScanEntity>() {
        override fun areItemsTheSame(oldItem: CodeScanEntity, newItem: CodeScanEntity): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(oldItem: CodeScanEntity, newItem: CodeScanEntity): Boolean {
            return(oldItem.code == newItem.code  && oldItem.scan_type == newItem.scan_type && oldItem.scan_phase == newItem.scan_phase )
        } }

        }