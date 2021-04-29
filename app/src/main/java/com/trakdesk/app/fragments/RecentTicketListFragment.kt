package com.trakdesk.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trakdesk.app.R

class RecentTicketListFragment : BaseFragment() {
    companion object {
        val TAG = RecentTicketListFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_recent_ticket_list, container, false)

}