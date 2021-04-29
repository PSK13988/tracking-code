package com.trakdesk.app.utils

import android.R
import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout

class ProgressBarHelper(
    context: Context?,
    mActivity: Activity
) {
    private val mRelativeLayout: RelativeLayout?
    fun showProgressBar() {
        if (mRelativeLayout != null) mRelativeLayout.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        if (mRelativeLayout != null) mRelativeLayout.visibility = View.GONE
    }

    init {
        val layout =
            mActivity.findViewById<View>(R.id.content).rootView as ViewGroup
        val mProgressBar =
            ProgressBar(context, null, R.attr.progressBarStyleLarge)
        mProgressBar.isIndeterminate = true
        val params =
            RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
            )
        mRelativeLayout = RelativeLayout(context)
        mRelativeLayout.gravity = Gravity.CENTER
        mRelativeLayout.addView(mProgressBar)
        mRelativeLayout.isClickable = true
        mRelativeLayout.isFocusable = true
        layout.addView(mRelativeLayout, params)
        hideProgressBar()
    }
}