package com.trakdesk.app.fragments

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    fun openChildFragment(fragment: Fragment, shouldAdd: Boolean) {
        //(activity as MainActivity).pushFragments(MainActivity.TAB_HOME, fragment, shouldAdd)
    }

    fun getScreenOrientation(): Int {
        return resources.configuration.orientation
    }
}