package com.trakdesk.app.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.appcompat.view.ActionMode
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.trakdesk.Config.ResponseCallback
import com.trakdesk.Trakdesk
import com.trakdesk.app.R
import com.trakdesk.app.activities.*
import com.trakdesk.app.adapters.MultiContactsAdapter
import com.trakdesk.app.customviews.ContactViewFastScroll
import com.trakdesk.app.models.contacts.ContactResponse
import com.trakdesk.app.models.contacts.ContactsData
import com.trakdesk.app.models.contacts.CustomFields
import com.trakdesk.app.utils.AppContacts.aToz
import com.trakdesk.app.utils.ProgressBarHelper
import kotlinx.android.synthetic.main.list_item_contact_layout.view.*
import java.util.*

class ContactListFragment : BaseFragment(), View.OnClickListener,
    MultiContactsAdapter.OnItemClickListener,
    ContactViewFastScroll.OnContactTouchEventListener {
    private lateinit var trakdesk: Trakdesk
    private lateinit var contactsAdapter: MultiContactsAdapter
    lateinit var mProgressBarHelper: ProgressBarHelper
    private var actionModeCallback: ActionModeCallback = ActionModeCallback()
    private var actionMode: ActionMode? = null
    var recyclerView: ContactViewFastScroll? = null

    companion object {
        val TAG = ContactListFragment::class.java.name

        fun newInstance(): ContactListFragment {
            return ContactListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    fun initView(view: View) {
//        tvSearchText
        mProgressBarHelper = ProgressBarHelper(context, activity as Activity)
        recyclerView = view.findViewById<ContactViewFastScroll>(R.id.recycler_view)
        //ivDrawerIcon.setOnClickListener(this)
        // ivFilterIcon.setOnClickListener(this)

        if ((activity as BaseActivity).isConnected) {
            mProgressBarHelper.showProgressBar()
            trakdesk = Trakdesk("anand@trakdesk.com:Welcome01", "api-dev-01")

            var options: HashMap<String?, String?>? = null

            options = HashMap()
            options["per_page"] = ""
            trakdesk.contacts.list(options, ResponseCallback {
                Log.d("MainActivity", it.toString())
                val gson = Gson()
                val contactResponse = gson.fromJson(it.toString(), ContactResponse::class.java)

                if (contactResponse.data != null) {
                    val contactList = mutableListOf<ContactsData>()

                    for (item in aToz) {
                        val result = contactResponse.data!!.filter { contactItem ->
                            println("All Contacts : ${contactItem.name} = ${contactItem.blocked}")
                            (contactItem.name.startsWith(item) or contactItem.name.startsWith(
                                item.toLowerCase(Locale.getDefault())
                            ))
                        }
                        if (result.isNotEmpty()) {
                            contactList.add(
                                ContactsData(
                                    -1,
                                    item,
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    0,
                                    listOf(),
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    "",
                                    false,
                                    false,
                                    false,
                                    "",
                                    "",
                                    CustomFields("", "", "", "", "", "", "", "", ""),
                                    ""
                                )
                            )
                            contactList.addAll(result)
                        }
                    }
                    print("New List 1: for $contactList")


                    contactsAdapter = MultiContactsAdapter(
                        context!!, contactList,
                        this
                    )

                    recyclerView!!.setData(contactsAdapter)
                    recyclerView!!.setOnContactTouchEventListener(this)
                    /*recyclerView!!.addItemDecoration(
                        DividerItemDecoration(
                            context,
                            LinearLayoutManager.VERTICAL
                        )
                    )*/
                } else {
                    Toast.makeText(context, "Contacts not available", Toast.LENGTH_LONG).show()
                }
                mProgressBarHelper.hideProgressBar()
            })
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            /* R.id.ivFilterIcon -> {
                 val filterBottomSheet = AddContactSheet.newInstance()
                 fragmentManager?.let { filterBottomSheet.show(it, filterBottomSheet.tag) }
             }*/
        }
    }

    override fun onItemClick(view: View, contactData: ContactsData, position: Int) {
        startActivity(Intent(context, ContactDetailActivity::class.java))
    }

    override fun onItemLongClick(view: View, contactData: ContactsData, position: Int) {
        val oa1: ObjectAnimator = ObjectAnimator.ofFloat(view.tvInitialChar, "scaleX", 1f, 0f)
        val oa2: ObjectAnimator = ObjectAnimator.ofFloat(view.tvInitialChar, "scaleX", 0f, 1f)
        oa1.interpolator = DecelerateInterpolator()
        oa2.interpolator = AccelerateDecelerateInterpolator()
        //oa1.duration = 2000;
        //oa2.duration = 2000;
        oa1.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
//                view.tvInitialChar.text = ""
                oa2.start()
            }
        })
        oa1.start()
        enableActionMode(position)
    }

    private fun enableActionMode(position: Int) {
        if (actionMode == null) {
            actionMode =
                (activity as BaseActivity?)!!.startSupportActionMode(actionModeCallback!!)
        }
        toggleSelection(position)
    }

    private fun toggleSelection(position: Int) {
        contactsAdapter.toggleSelection(position)
        val count: Int = contactsAdapter.getSelectedItemCount()
        if (count == 0) {
            actionMode!!.finish()
            actionMode = null
        } else {
            actionMode!!.title = count.toString()
            actionMode!!.invalidate()
        }
    }

    private fun selectAll() {
        contactsAdapter.selectAll()
        val count: Int = contactsAdapter.getSelectedItemCount()
        if (count == 0) {
            actionMode!!.finish()
        } else {
            actionMode!!.title = count.toString()
            actionMode!!.invalidate()
        }
        actionMode = null
    }


    private inner class ActionModeCallback : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            mode.menuInflater.inflate(R.menu.contact_selection_menu, menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean {
            return false
        }

        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
            Log.d(TAG, item.title.toString())
            return when (item.itemId) {
                R.id.action_delete -> {
                    mode.finish()
                    true
                }
                else -> false
            }
        }

        override fun onDestroyActionMode(mode: ActionMode) {
            contactsAdapter.clearSelections()
            actionMode = null
        }
    }

    override fun onContactSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (direction == ItemTouchHelper.RIGHT) {
            context?.showToast("Right")
        } else {
            context?.showToast("Left")
        }


        val position = viewHolder.adapterPosition
        val swipedContact = contactsAdapter.getItem(position)

        val contactsList = contactsAdapter.getList();

        val result = contactsList.filter { contactItem ->
            contactItem.name.length != 1 && (contactItem.name.startsWith(swipedContact.name.first()) ||
                    contactItem.name.startsWith(
                        swipedContact.name.first().toUpperCase()
                    ))
        }

        Log.d(
            TAG,
            "The filtered result for letter ${swipedContact.name.first()} is ${result.size}"
        )

        if (result.isNotEmpty()) {
            if (result.size == 1) {
                Log.d(TAG, "Filtered contact equal to 1")
                val item: ContactsData = contactsAdapter.removeItem(position)
                val headerItem = contactsAdapter.removeItem(position - 1)

                val parentActivity = activity as MainActivity
                val snackbar =
                    parentActivity.showSnackar(
                        context?.getString(R.string.str_contact) + (if (direction == ItemTouchHelper.RIGHT)
                            context?.getString(R.string.str_deleted)
                        else context?.getString(R.string.str_restore)) + "."
                    )

                snackbar.setAction(
                    android.R.string.cancel,
                    View.OnClickListener {
                        try {
                            contactsAdapter.addItem(headerItem, position - 1)
                            contactsAdapter.addItem(item, position)
                        } catch (e: Exception) {
                            Log.e("MainActivity", "${e.printStackTrace()}")
                        }
                    })
                snackbar.show()
            } else if (result.size > 1) {
                val item: ContactsData = contactsAdapter.removeItem(position)
                val parentActivity = activity as MainActivity
                val snackbar =
                    parentActivity.showSnackar(
                        context?.getString(R.string.str_contact) + (if (direction == ItemTouchHelper.RIGHT)
                            context?.getString(R.string.str_deleted)
                        else context?.getString(R.string.str_restore)) + "."
                    )

                snackbar.setAction(
                    android.R.string.cancel,
                    View.OnClickListener {
                        try {
                            contactsAdapter.addItem(item, position)
                        } catch (e: Exception) {
                            Log.e(TAG, "${e.printStackTrace()}")
                        }
                    })
                snackbar.show()
            }
        } else {
            Log.d(TAG, "Filtered contact empty")
        }
    }

    override fun onItemClicked() {

    }
}