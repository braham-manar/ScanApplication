package com.example.scanmodule

import androidx.recyclerview.widget.DiffUtil
import com.example.scanmodule.data.dataBase.CodeScanEntity

class MyDiffUtil (private val oldList:List<CodeScanEntity>, private val newList:List<CodeScanEntity>)
    :DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].code==newList[newItemPosition].code
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            oldList[oldItemPosition].code!=newList[newItemPosition].code ->{
                false
            }else ->
                true


    }
}
}