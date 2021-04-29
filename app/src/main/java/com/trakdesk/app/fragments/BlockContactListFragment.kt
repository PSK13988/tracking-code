package com.trakdesk.app.fragments

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
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
import java.util.*

class BlockContactListFragment : BaseFragment(), View.OnClickListener,
    ContactViewFastScroll.OnContactTouchEventListener,
    MultiContactsAdapter.OnItemClickListener {
    var recyclerView: ContactViewFastScroll? = null
    lateinit var mProgressBarHelper: ProgressBarHelper

    companion object {
        val TAG = BlockContactListFragment::class.java.name

        fun newInstance(): BlockContactListFragment {
            return BlockContactListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blocked_contact_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    fun initView(view: View) {
        //initialize RecyclerView

        mProgressBarHelper = ProgressBarHelper(context, activity as Activity)
        recyclerView = view.findViewById<ContactViewFastScroll>(R.id.recycler_view)

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            recyclerView!!.setOnRecyclerViewFastScrollEventListener(this)
        }*/

        val trakdesk = Trakdesk("anand@trakdesk.com:Welcome01", "api-dev-01")
        var options: HashMap<String?, String?>? = null

        options = HashMap()
        options["per_page"] = ""
        if ((activity as BaseActivity).isConnected) {
            mProgressBarHelper.showProgressBar()
            trakdesk.contacts.list(options, ResponseCallback { it ->
                Log.d("MainActivity", it.toString())
                val gson = Gson()
                val contactResponse = gson.fromJson(it.toString(), ContactResponse::class.java)

                if (contactResponse.data != null) {
                    val contactList = mutableListOf<ContactsData>()

                    for (item in aToz) {
                        val result = contactResponse.data!!.filter { contactItem ->
                            println("Blocked Contacts : ${contactItem.name} = ${contactItem.blocked}")
                            contactItem.blocked and (contactItem.name.startsWith(item) or contactItem.name.startsWith(
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

                    if (contactList.isEmpty()) {
                        context?.showToast("Blocked contacts not available");
                    } else {
                        val myAdapter = MultiContactsAdapter(
                            context!!, contactList,
                            this
                        )

                        recyclerView!!.setData(myAdapter)
                    }
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

    override fun onContactSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        if (direction == ItemTouchHelper.RIGHT)
            context?.showToast("Right") else context?.showToast("Left")

    }

    /* override fun onRightSwiped(position: Int) {
         context?.showToast("Right")
     }

     override fun onLeftSwiped(position: Int) {
         context?.showToast("Left")
     }
 */
    override fun onItemClicked() {
        context?.showToast("clicked")
    }

    override fun onItemClick(view: View, contactData: ContactsData, position: Int) {
        context!!.showToast(contactData.name)
    }

    override fun onItemLongClick(view: View, contactData: ContactsData, position: Int) {
        context!!.showToast(contactData.name)
    }

}