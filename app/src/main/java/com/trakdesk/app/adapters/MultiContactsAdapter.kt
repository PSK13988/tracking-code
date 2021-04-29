package com.trakdesk.app.adapters

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.util.SparseBooleanArray
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.trakdesk.app.R
import com.trakdesk.app.adapters.MultiContactsAdapter.Companion.TYPE_CONTACT
import com.trakdesk.app.models.contacts.ContactsData
import kotlinx.android.synthetic.main.list_item_contact_layout.view.*
import kotlinx.android.synthetic.main.list_item_section_header.view.*
import java.util.*

class MultiContactsAdapter(
    private val context: Context,
    private val contactsData: MutableList<ContactsData>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    var initialChar: String = ""

    companion object {
        const val TYPE_HEADER = 1
        const val TYPE_CONTACT = 2
        var TAG: String = MultiContactsAdapter::class.java.name
        var currentSelectedIndex = -1
    }

    private val selectedItems: SparseBooleanArray = SparseBooleanArray()

    inner class NameViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //        val tvSeparatorInitialChar = view.tvSeparatorInitialChar!!
        val tvInitialChar = view.tvInitialChar!!
        val child = view.child!!
        val tvEmail = view.tvEmail!!
        val divider = view.divider!!
        val tvContactVerify = view.tvContactVerify!!
    }

    inner class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sectionHeader = view.sectionHeader!!
    }


    interface OnItemClickListener {
        fun onItemClick(view: View, contactData: ContactsData, position: Int)
        fun onItemLongClick(view: View, contactData: ContactsData, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_section_header, parent, false)
                HeaderViewHolder(view)
            }
            TYPE_CONTACT -> {
                val view =
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item_contact_layout, parent, false)
                NameViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (contactsData[position].name.length == 1) {
            TYPE_HEADER;
        } else {
            TYPE_CONTACT;
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = contactsData[position]
        Log.d(TAG, "Contact: $item")
        when (holder) {
            is HeaderViewHolder -> {
                holder.sectionHeader.text = item.name.first().toString()
            }
            is NameViewHolder -> {
                holder.tvInitialChar.text = item.name.first().toUpperCase().toString()
                holder.child.text = item.name
                if (item.verified) holder.tvContactVerify.background =
                    ContextCompat.getDrawable(context, R.drawable.contact_circle_solid_shape)
                else
                    holder.tvContactVerify.background =
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.contact_circle_solid_shape_unverified
                        )

                if (item.email.isEmpty())
                    holder.tvEmail.text = "--"
                else
                    holder.tvEmail.text = item.email

                if (selectedItems!![position, false]) {
                    holder.itemView.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.colorItemSelection
                        )
                    )
                } else {
                    holder.itemView.setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            R.color.colorBackground
                        )
                    )
                }

                holder.itemView.setOnClickListener {
                    onItemClickListener!!.onItemClick(
                        it,
                        item,
                        position
                    )
                }
                holder.itemView.setOnLongClickListener {
                    onItemClickListener!!.onItemLongClick(it, item, position)
                    true
                }
            }
        }
    }

    override fun getItemCount() = contactsData.size

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun getList(): List<ContactsData> {
        return contactsData
    }

    fun getItem(position: Int): ContactsData {
        return contactsData[position]
    }

    fun removeItem(position: Int): ContactsData {
        val contact = contactsData[position]
        contactsData.removeAt(position)
        notifyItemRemoved(position)
        println("Item removing position $position and name is : ${contact.name}")
        return contact
    }

    fun removeItemAndHeader(position: Int): ContactsData {
        val contact = contactsData[position]
        // removing contact
        contactsData.removeAt(position)
        notifyItemRemoved(position)

        // removing header
        contactsData.removeAt(position - 1)
        notifyItemRemoved(position - 1)

        println("Item removing position $position and header is : ${position - 1} and item ${contact.name}")
        return contact
    }

    fun addItem(contactData: ContactsData, position: Int) {
        contactsData.add(position, contactData)
        println("Item inserting position $position and name is : ${contactData.name}")
        notifyItemInserted(position)
    }

    fun addItemAndHeader(contactData: ContactsData, position: Int) {
        contactsData.add(position, contactData)
        println("Item inserting position $position and name is : ${contactData.name}")
        notifyItemInserted(position)
    }

    fun toggleSelection(pos: Int) {
        currentSelectedIndex = pos
        if (selectedItems[pos, false]) {
            selectedItems.delete(pos)
        } else {
            selectedItems.put(pos, true)
        }
        notifyItemChanged(pos)
    }

    fun selectAll() {
        for (i in 0 until itemCount) selectedItems.put(i, true)
        notifyDataSetChanged()
    }


    fun clearSelections() {
        selectedItems.clear()
        notifyDataSetChanged()
    }

    fun getSelectedItemCount(): Int {
        return selectedItems.size()
    }

    fun getSelectedItems(): List<Int>? {
        val items: MutableList<Int> =
            ArrayList(selectedItems.size())
        for (i in 0 until selectedItems.size()) {
            items.add(selectedItems.keyAt(i))
        }
        return items
    }
}