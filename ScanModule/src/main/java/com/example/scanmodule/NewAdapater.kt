package com.example.scanmodule

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.scanmodule.data.dataBase.CodeScanEntity

class NewAdapter:RecyclerView.Adapter<NewAdapter.CodeScanViewHolder>() {
    private var oldCodeList: ArrayList<CodeScanEntity> = arrayListOf()


    fun setDataToAdapter(data: List<CodeScanEntity>) {
        val diff_Util=MyDiffUtil(oldCodeList,data)
        val diffResult=DiffUtil.calculateDiff(diff_Util)
        oldCodeList= data as ArrayList<CodeScanEntity>
        diffResult.dispatchUpdatesTo(this)
    }
        /*differ.addListListener(data)
        _dataList=differ as ArrayList<CodeScanEntity>
        Log.i("test-manar", "dataAdd ")*/


    inner class CodeScanViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var TV_Code_Scan:TextView=itemView.findViewById(R.id.TV_Code_Scan)
    }
    //DiffUtil




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CodeScanViewHolder {
        return CodeScanViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_recycler_view,
                parent,
                false))
    }

    override fun onBindViewHolder(holder: CodeScanViewHolder, position: Int) {
        val codeScanList= oldCodeList[position]
        holder.TV_Code_Scan.text = codeScanList.code
        Log.i("test_new", "scan_new ")

        codeScanList.code?.forEach { code ->
            holder.TV_Code_Scan.text = holder.TV_Code_Scan.text

        }
    }
    override fun getItemCount(): Int {
        return oldCodeList.size

    }




}