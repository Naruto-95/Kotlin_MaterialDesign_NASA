package com.example.kotlin_materialdesign_nasa.recycler

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback(private val itemTouchHelperAdapter: ItemTouchHelperAdapter):ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags,swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        itemTouchHelperAdapter.onItemMove(viewHolder.adapterPosition,target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        itemTouchHelperAdapter.onItemDismiss(viewHolder.adapterPosition)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
       if (actionState != ItemTouchHelper.ACTION_STATE_IDLE){
           val mViewHolder = viewHolder as ItemTouchHelperViewHolder
           mViewHolder.onItemSelected()
       }
        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        val mViewHolder = viewHolder as ItemTouchHelperViewHolder
        mViewHolder.onItemClear()
        super.clearView(recyclerView, viewHolder)
    }

}