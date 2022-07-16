package com.example.scanmodule.ui.scan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.scanmodule.R
import com.example.scanmodule.dataBase.CodeScanEntity


class ScanListAdapter : ListAdapter<CodeScanEntity, RecyclerView.ViewHolder>(ScanDiffCallback()) {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScannedObjectViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
        return ScannedObjectViewHolder(view)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rvHolder = (holder as ScannedObjectViewHolder)
        val currentScan = currentList[position]

        rvHolder.tvScanValue.text = currentScan.code

        holder.itemView.setOnClickListener {  }
    }



    class ScannedObjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvScanValue = itemView.findViewById(R.id.TV_Code_Scan) as TextView

    }



    private class ScanDiffCallback : DiffUtil.ItemCallback<CodeScanEntity>() {
        override fun areItemsTheSame(oldItem: CodeScanEntity, newItem: CodeScanEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CodeScanEntity, newItem: CodeScanEntity): Boolean {
            return oldItem.id == newItem.id
        }

    }


}