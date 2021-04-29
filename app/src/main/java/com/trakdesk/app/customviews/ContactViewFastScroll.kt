package com.trakdesk.app.customviews

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trakdesk.app.R
import com.trakdesk.app.activities.showToast
import com.trakdesk.app.adapters.MultiContactsAdapter
import com.trakdesk.app.adapters.ContactIndexAdapter
import com.trakdesk.app.helpers.DividerDecoration
import com.trakdesk.app.helpers.RecyclerViewSwipeDecorator
import com.trakdesk.app.utils.AppContacts.aToz
import kotlinx.android.synthetic.main.layout_contact_view_fast_scroll.view.*
import java.util.*


class ContactViewFastScroll : LinearLayout {
    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    )

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes)

    var onContactTouchEventListener: OnContactTouchEventListener? = null
    lateinit var adapterRecycler: MultiContactsAdapter

    companion object {
        val TAG = ContactViewFastScroll::class.java.simpleName
    }

    interface OnContactTouchEventListener {
        fun onContactSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int)

        fun onItemClicked()
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_contact_view_fast_scroll, this, true)
        orientation = VERTICAL
        setupView()
    }

    fun setData(multiContactsAdapter: MultiContactsAdapter) {
        recyclerViewItems!!.adapter = multiContactsAdapter
        adapterRecycler = recyclerViewItems!!.adapter as MultiContactsAdapter
        recyclerViewItems!!.addItemDecoration(
            DividerDecoration(context)
        )
    }

    fun setOnContactTouchEventListener(listener: Any?) {
        onContactTouchEventListener = listener as OnContactTouchEventListener
    }

    private fun setupView() {
        recyclerViewIndexes.layoutManager = LinearLayoutManager(context)
        recyclerViewItems.layoutManager = LinearLayoutManager(context)
        val testadapter = context?.let { it1 ->
            ContactIndexAdapter(
                it1,
                aToz,
                clickListener = { itemView: View, s: String, i: Int ->
                    val indexAdapter = recyclerViewIndexes.adapter as ContactIndexAdapter
                    indexAdapter.clearSelections()

                    val itemList = (recyclerViewItems.adapter as MultiContactsAdapter).getList()
                    val found =
                        itemList.find { contactsData ->
                            (contactsData.name.length != 1) and (contactsData.name.first()
                                .toString() == s) or
                                    (contactsData.name.first()
                                        .toString() == s.toLowerCase(Locale.getDefault()))
                        }
                    println("Item Found: $found")
                    val scrollIndex = itemList.indexOf(found)
                    println("Item Found ScrollIndex : $scrollIndex")
                    if (scrollIndex != -1)
                        recyclerViewItems.smoothScrollToPosition(scrollIndex)
                    else
                        context?.showToast("No contacts with this index")
                    Log.d(TAG, "$s item at position $i")

                    indexAdapter.toggleSelection(i)
                })
        }!!
        recyclerViewIndexes.adapter = testadapter
        testadapter.toggleSelection(0)
        setupItemTouch()
    }

    private fun setupItemTouch() {
        val itemTouchHelperCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                Log.d("TAG", "onSwiped: $direction")
                try {
                    onContactTouchEventListener!!.onContactSwiped(viewHolder, direction)

                    /*val position = viewHolder.adapterPosition
                    //val item: String? = contactsAdapter.removeItem(position)
                    val snackbar: Snackbar = Snackbar.make(
                        viewHolder.itemView,
                        "Item " + (if (direction == ItemTouchHelper.RIGHT) "deleted" else "archived") + ".",
                        Snackbar.LENGTH_LONG
                    )
                    snackbar.setAction(
                        android.R.string.cancel,
                        View.OnClickListener {
                            try {
                                //item?.let { it1 -> contactsAdapter.addItem(it1, position) }
                            } catch (e: Exception) {
                                Log.e("MainActivity", e.message)
                            }
                        })
                    snackbar.show()*/
                } catch (e: Exception) {
                    Log.e("MainActivity", "${e.printStackTrace()}")
                }
            }

            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                if (viewHolder is MultiContactsAdapter.HeaderViewHolder) {
                    val dragFlags = 0
                    val swipeFlags = 0
                    return makeMovementFlags(dragFlags, swipeFlags)
                }
                return super.getMovementFlags(recyclerView, viewHolder)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                RecyclerViewSwipeDecorator.Builder(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
                    .addSwipeLeftBackgroundColor(
                        ContextCompat.getColor(
                            context!!,
                            R.color.recycler_view_item_swipe_left_background
                        )
                    )
                    .addSwipeLeftActionIcon(R.drawable.ic_refresh_black_24dp)
                    .addSwipeRightBackgroundColor(
                        ContextCompat.getColor(
                            context!!,
                            R.color.recycler_view_item_swipe_right_background
                        )
                    )
                    .addSwipeRightActionIcon(R.drawable.ic_delete_black_24dp)
                    .addSwipeRightLabel(context?.getString(R.string.action_delete))
                    .setSwipeRightLabelColor(Color.WHITE)
                    .addSwipeLeftLabel(context?.getString(R.string.action_restore))
                    .setSwipeLeftLabelColor(Color.WHITE)
                    .create()
                    .decorate()

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }


        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerViewItems)
    }
}