package com.trakdesk.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trakdesk.app.R

class TicketGuideFragment : BaseFragment() {

    companion object {
        val TAG = TicketGuideFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater!!.inflate(R.layout.fragment_ticket_guide, container, false)

        // Get the text view widget reference from custom layout
        // Return the fragment view/layout
        return view
    }


}