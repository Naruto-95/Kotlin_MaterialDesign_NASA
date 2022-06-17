package com.example.kotlin_materialdesign_nasa.recycler

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition:Int, toPosition:Int)
    fun onItemDismiss(position:Int)
}

interface ItemTouchHelperViewHolder{
    fun onItemSelected()
    fun onItemClear()

}