package com.example.scanmodule

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.scanmodule.dataBase.CodeScanEntity
import com.example.scanmodule.ui.scan.ScanViewModel

class NewAdapter:RecyclerView.Adapter<NewAdapter.CodeScanViewHolder>() {
    private var _dataList: ArrayList<CodeScanEntity> = arrayListOf()

    fun setDataToAdapter(data: ArrayList<CodeScanEntity>) {
        _dataList.addAll(data)
        Log.i("test-manar", "dataAdd ")
    }

    inner class CodeScanViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var TV_Code_Scan:TextView=itemView.findViewById(R.id.TV_Code_Scan)
    }
    /*private val differCallback = object : DiffUtil.ItemCallback<CodeScanEntity>(){
        override fun areItemsTheSame(oldItem: CodeScanEntity, newItem: CodeScanEntity): Boolean {
            return oldItem.code == newItem.code
        }*/

        /*@SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: CodeScanEntity, newItem: CodeScanEntity): Boolean {
            return oldItem == newItem
        }}
    val differ =AsyncListDiffer(this,differCallback)*/



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CodeScanViewHolder {
        return CodeScanViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_recycler_view,
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: CodeScanViewHolder, position: Int) {
        val codeScanList= _dataList[position]
        holder.TV_Code_Scan.text = codeScanList.code
        Log.i("test_new", "scan_new ")

        codeScanList.code?.forEach { code ->
            holder.TV_Code_Scan.text = holder.TV_Code_Scan.text
        }
    }
    override fun getItemCount(): Int {
        return _dataList.size

    }



}