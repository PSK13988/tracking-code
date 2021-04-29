package com.trakdesk.app.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.view.ActionMode
import com.google.gson.Gson
import com.trakdesk.Config.ResponseCallback
import com.trakdesk.Trakdesk
import com.trakdesk.app.R
import com.trakdesk.app.activities.BaseActivity
import com.trakdesk.app.activities.isConnected
import com.trakdesk.app.adapters.MultiContactsAdapter
import com.trakdesk.app.customviews.ContactViewFastScroll
import com.trakdesk.app.models.contacts.ContactResponse
import com.trakdesk.app.models.contacts.ContactsData
import com.trakdesk.app.models.contacts.CustomFields
import com.trakdesk.app.utils.AppContacts.aToz
import com.trakdesk.app.utils.ProgressBarHelper
import java.util.*

class DeletedContactListFragment : BaseFragment(), View.OnClickListener,
    MultiContactsAdapter.OnItemClickListener {
    private lateinit var contactsAdapter: MultiContactsAdapter
    lateinit var mProgressBarHelper: ProgressBarHelper
    private var actionModeCallback: ActionModeCallback = ActionModeCallback()
    private var actionMode: ActionMode? = null
    var recyclerView: ContactViewFastScroll? = null

    companion object {
        val TAG = DeletedContactListFragment::class.java.name

        fun newInstance(): DeletedContactListFragment {
            return DeletedContactListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_deleted_contact_list, container, false)
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
            val trakdesk = Trakdesk("anand@trakdesk.com:Welcome01", "api-dev-01")

            var options: HashMap<String?, String?>? = null

            options = HashMap()
            options["per_page"] = ""
            trakdesk.contacts.list(options, ResponseCallback {
                Log.d("MainActivity", it.toString())
                val gson = Gson()
                val contactResponse = gson.fromJson(it.toString(), ContactResponse::class.java)

                if (contactResponse.data != null) {
                    contactsAdapter = context?.let { it1 ->

                        val contactList = mutableListOf<ContactsData>()

                        for (item in aToz) {
                            val result = contactResponse.data!!.filter { contactItem ->
                                println("Deleted Contacts : ${contactItem.name} = ${contactItem.verified}")
                                contactItem.deleted and (contactItem.name.startsWith(item) or contactItem.name.startsWith(
                                    item.toLowerCase(Locale.getDefault())
                                )
                                        )
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


                        MultiContactsAdapter(
                            it1, contactList,
                            this
                        )
                    }!!
                    recyclerView!!.setData(contactsAdapter)
                    /* rvContacts.addItemDecoration(
                         DividerItemDecoration(
                             context,
                             LinearLayoutManager.VERTICAL
                         )
                     )*/
                    //setupItemTouch()
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


    private fun itemClicked(contact: ContactsData, position: Int) {
        Toast.makeText(context, "You have clicked ${contact.name}", Toast.LENGTH_SHORT).show()
    }

    private fun itemClicked(contact: String) {
        Toast.makeText(context, contact, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(view: View, contactData: ContactsData, position: Int) {
        Toast.makeText(context, contactData.name, Toast.LENGTH_SHORT).show()
    }

    override fun onItemLongClick(view: View, contactData: ContactsData, position: Int) {
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
}