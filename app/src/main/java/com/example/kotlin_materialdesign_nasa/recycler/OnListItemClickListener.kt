package com.example.kotlin_materialdesign_nasa.recycler

interface OnListItemClickListener {
    fun onItemClick(data: Data)
    fun onAddItemClick(position: Int)
    fun onRemoveItemClick(position: Int)

}