package com.trakdesk.app.activities

import android.os.Bundle
import android.view.Gravity
import com.trakdesk.app.R
import com.trakdesk.app.fragments.ContactListFragment
import com.trakdesk.app.sheets.AddContactSheet
import com.trakdesk.app.sheets.FilterBottomSheet
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_container.*
import kotlinx.android.synthetic.main.custom_toolbar.*


class TestActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_testing)

    }
}