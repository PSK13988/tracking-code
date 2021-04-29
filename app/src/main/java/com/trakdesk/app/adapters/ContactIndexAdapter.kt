package com.trakdesk.app.adapters

import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.trakdesk.app.R
import kotlinx.android.synthetic.main.list_item_contact_index_adapter.view.*

class ContactIndexAdapter(
    private val context: Context,
    private val indexes: List<String>,
    private val clickListener: (View, String, Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        var TAG: String = ContactIndexAdapter::class.java.name
        var initialChar: String = ""
        var currentSelectedIndex = -1
    }

    private var selectedItems: SparseBooleanArray? = null

    init {
        selectedItems = SparseBooleanArray()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        // LayoutInflater: takes ID from layout defined in XML.
        // Instantiates the layout XML into corresponding View objects.
        // Use context from main app -> also supplies theme layout values!
        val inflater = LayoutInflater.from(parent.context)
        // Inflate XML. Last parameter: don't immediately attach new view to the parent view group
        val view = inflater.inflate(R.layout.list_item_contact_index_adapter, parent, false)
        return IndexViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Populate ViewHolder with data that corresponds to the position in the list
        // which we are told to load
        val holderView = holder as IndexViewHolder
        holderView.scrollIndexText.text = indexes[position]
        if (selectedItems!![position, false]) {
            holder.scrollIndexText.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorIndexHighlighted
                )
            )
        } else {
            holder.scrollIndexText.setTextColor(ContextCompat.getColor(context, R.color.colorIndex))
        }
        holderView.itemView.setOnClickListener { clickListener(it, indexes[position], position) }
    }

    override fun getItemCount() = indexes.size
    fun removeItem(position: Int): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getList(): List<String> {
        return indexes
    }

    fun addItem(contactData: String, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class IndexViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val scrollIndexText = itemView.scrollIndexText!!

        /*fun bind(item: String, clickListener: (View, String, Int) -> Unit, context: Context, position: Int) {
            Log.d(TAG, "Contact: $item")

            itemView.scrollIndexText.text = item

        }*/
    }

    fun toggleSelection(pos: Int) {
        currentSelectedIndex = pos
        if (selectedItems!![pos, false]) {
            selectedItems!!.delete(pos)
        } else {
            selectedItems!!.put(pos, true)
        }
        notifyItemChanged(pos)
    }

    fun selectAll() {
        for (i in 0 until itemCount) selectedItems!!.put(i, true)
        notifyDataSetChanged()
    }


    fun clearSelections() {
        selectedItems!!.clear()
        notifyDataSetChanged()
    }

    fun getSelectedItemCount(): Int {
        return selectedItems!!.size()
    }

    fun getSelectedItems(): List<Int>? {
        val items: MutableList<Int> =
            ArrayList(selectedItems!!.size())
        for (i in 0 until selectedItems!!.size()) {
            items.add(selectedItems!!.keyAt(i))
        }
        return items
    }
}