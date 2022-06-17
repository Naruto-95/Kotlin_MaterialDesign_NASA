package com.example.kotlin_materialdesign_nasa.recycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_materialdesign_nasa.databinding.FragmentHeaderBinding
import com.example.kotlin_materialdesign_nasa.databinding.FragmentRecyclerEarthBinding
import com.example.kotlin_materialdesign_nasa.databinding.FragmentRecyclerMarsBinding
import com.example.kotlin_materialdesign_nasa.recycler.mViewHolders.BaseViewHolder
import com.example.kotlin_materialdesign_nasa.recycler.mViewHolders.Header
import com.example.kotlin_materialdesign_nasa.recycler.mViewHolders.Mars
import com.example.kotlin_materialdesign_nasa.utils.EARTH_R
import com.example.kotlin_materialdesign_nasa.utils.HEADER_R
import com.example.kotlin_materialdesign_nasa.utils.MARS_R
import kotlin.Pair as Pair

class RecyclerViewAdapter(
    private var onListItemClickListener:OnListItemClickListener
) :
    RecyclerView.Adapter<BaseViewHolder>(),ItemTouchHelperAdapter {

    private lateinit var listData: MutableList<Pair<Data, Boolean>>

     fun setList(newDataList:List<Pair<Data, Boolean>>){
        this.listData = newDataList.toMutableList()
    }
    fun setAddList(newDataList:List<Pair<Data, Boolean>>, position: Int){
        this.listData = newDataList.toMutableList()
        notifyItemChanged(position)
    }
   fun setRemoveList(newDataList:List<Pair<Data, Boolean>>, position: Int){
        this.listData = newDataList.toMutableList()
        notifyItemRemoved(position)
    }


    override fun getItemViewType(position: Int): Int {
        return listData[position].first.mType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            EARTH_R -> {
                val view = FragmentRecyclerEarthBinding.inflate(LayoutInflater.from(parent.context))
                Earth(view.root)
            }
            MARS_R -> {
                val view = FragmentRecyclerMarsBinding.inflate(LayoutInflater.from(parent.context))
                Mars(view.root,onListItemClickListener)
            }
            HEADER_R -> {
                val view = FragmentHeaderBinding.inflate(LayoutInflater.from(parent.context))
                Header(view.root)
            }
            else -> {
                val view = FragmentRecyclerMarsBinding.inflate(LayoutInflater.from(parent.context))
                Mars(view.root,onListItemClickListener)
            }
        }

    }
inner class Earth(view: View) :
    BaseViewHolder(view),ItemTouchHelperViewHolder {


    override fun mBind(data: Pair<Data, Boolean>) {
        (FragmentRecyclerEarthBinding.bind(itemView)).apply {
            textEarth.text = data.first.mText
            //descriptionTextViewE.text = data.first.mText
            addItemImageView.setOnClickListener {
                onListItemClickListener.onAddItemClick(layoutPosition)
            }
            removeItemImageView.setOnClickListener {
                onListItemClickListener.onRemoveItemClick(layoutPosition)
            }
            moveItemUp.setOnClickListener {
                listData.removeAt(layoutPosition).apply {
                    listData.add(layoutPosition - 1, this)
                }
                 notifyItemMoved(layoutPosition,layoutPosition-1)
            }
            moveItemDown.setOnClickListener {
                listData.removeAt(layoutPosition).apply {
                    listData.add(layoutPosition + 1, this)
                }
                notifyItemMoved(layoutPosition,layoutPosition+1)
            }
            earthImageView.setOnClickListener {
                listData[layoutPosition] =  listData[layoutPosition].let {
                    it.first to !it.second
                }
                earthDescriptionTextView.visibility = if (listData[layoutPosition].second) View.VISIBLE else View.GONE
                notifyItemChanged(layoutPosition)
            }
        }



    }

    override fun onItemSelected() {
        itemView.setBackgroundColor(Color.LTGRAY)
    }

    override fun onItemClear() {
        itemView.setBackgroundColor(0)
    }
}
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int ) {
        holder.mBind(listData[position])


    }


override fun getItemCount(): Int {
    return listData.size
}

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        listData.removeAt(fromPosition).apply {
            listData.add(toPosition, this)
        }
        notifyItemMoved(fromPosition,toPosition)
    }

    override fun onItemDismiss(position: Int) {
        listData.removeAt(position)
        notifyItemRemoved(position)
    }




}




