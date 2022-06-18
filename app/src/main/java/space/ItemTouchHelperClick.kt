package space

interface ItemTouchHelperClick {
    fun onItemMove(fromPosition:Int, toPosition:Int)
    fun onItemDismiss(position:Int)
}

interface ItemTouchNotesHelperViewHolder{
    fun onItemSelected()
    fun onItemClear()

}