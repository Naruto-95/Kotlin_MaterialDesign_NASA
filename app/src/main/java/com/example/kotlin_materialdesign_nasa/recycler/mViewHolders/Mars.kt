package com.example.kotlin_materialdesign_nasa.recycler.mViewHolders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_materialdesign_nasa.databinding.FragmentRecyclerMarsBinding
import com.example.kotlin_materialdesign_nasa.recycler.Data
import com.example.kotlin_materialdesign_nasa.recycler.OnListItemClickListener

class Mars(view: View, private var onListItemClickListener: OnListItemClickListener): BaseViewHolder(view) {
    override fun mBind(data: Pair<Data, Boolean>){
            (FragmentRecyclerMarsBinding.bind(itemView)).apply {
                textMars.text = data.first.mText
                descriptionTextView.text = data.first.mDesc
                addItemImageView.setOnClickListener {
                    onListItemClickListener.onAddItemClick(layoutPosition)
                }
                removeItemImageView.setOnClickListener {
                    onListItemClickListener.onRemoveItemClick(layoutPosition)
                }
            }

        }

}