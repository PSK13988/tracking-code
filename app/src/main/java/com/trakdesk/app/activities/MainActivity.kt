package com.trakdesk.app.activities

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.trakdesk.Config.ResponseCallback
import com.trakdesk.Trakdesk
import com.trakdesk.app.R
import com.trakdesk.app.fragments.*
import com.trakdesk.app.models.contacts.ContactResponse
import com.trakdesk.app.models.contacts.ContactsData
import com.trakdesk.app.sheets.AddContactSheet
import com.trakdesk.app.sheets.FilterBottomSheet
import com.trakdesk.app.utils.ProgressBarHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_container.*
import kotlinx.android.synthetic.main.custom_toolbar.*
import java.util.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    companion object {
        const val TAB_HOME = "tab_home"
        const val POPUP_CONSTANT = "mPopup"
        const val POPUP_FORCE_SHOW_ICON = "setForceShowIcon"
    }

    private val mStacks = HashMap<String, Stack<Fragment>>()
    private val mCurrentTab = TAB_HOME

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //bottomAppBar.setBackgroundResource(R.drawable.bottombar_background_gradient)

        if (savedInstanceState == null) {
            val contactListFragment = ContactListFragment.newInstance()
            loadFragment(contactListFragment)
            /*supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, contactListFragment, contactListFragment.tag)
                .commit()*/
        }

        ivDrawerIcon.setOnClickListener {
            drawerLayout.openDrawer(Gravity.LEFT)
        }

        fab.setOnClickListener {
            val addContactSheet = AddContactSheet.newInstance()
            supportFragmentManager.let { addContactSheet.show(it, addContactSheet.tag) }
        }

        ivFilterIcon.setOnClickListener {
            // drawerLayout.openDrawer(Gravity.LEFT)
            val filterBottomSheet = FilterBottomSheet.newInstance()
            supportFragmentManager.let { filterBottomSheet.show(it, filterBottomSheet.tag) }
        }

        llParentTicket.setOnClickListener {
//            startActivity(Intent(this, SampleActivity::class.java))
            showToast("Not functional now")
        }
        llParentContacts.setOnClickListener {
            showToast("Not functional now")
        }
        llParentCompanies.setOnClickListener {
            showToast("Not functional now")
        }
        llParentExtraMenus.setOnClickListener {
            showToast("Not functional now")
//            showSnackar("This is normal action").show()
        }

        navigationView.setNavigationItemSelectedListener(this)
    }

    fun showSnackar(message: String): Snackbar {
        val snackbar = Snackbar.make(clBottomAppBar, message, Snackbar.LENGTH_LONG)
        snackbar.anchorView = fab
        val snackBarView = snackbar.view

        val params = CoordinatorLayout.LayoutParams(
            CoordinatorLayout.LayoutParams.MATCH_PARENT,
            CoordinatorLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(50, 0, 50, 20)
        params.gravity = Gravity.BOTTOM
        params.anchorGravity = Gravity.BOTTOM

        snackBarView.layoutParams = params
        //snackbar.show()
        return snackbar
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_all_contacts -> {
                // Handle the camera action
                val contactListFragment = ContactListFragment.newInstance()
                pushFragments("contactListFragment", contactListFragment, true)
            }
            R.id.nav_verified_contacts -> {
                val verifiedContactListFragment = VerifiedContactListFragment.newInstance()
                pushFragments("verifiedContactListFragment", verifiedContactListFragment, true)
            }
            R.id.nav_unverified_contacts -> {
                val unverifiedContactFragment = UnverifiedContactListFragment.newInstance()
                pushFragments("unverifiedContactFragment", unverifiedContactFragment, true)
            }
            R.id.nav_blocked_contacts -> {
                val blockContactListFragment = BlockContactListFragment.newInstance()
                pushFragments("blockContactListFragment", blockContactListFragment, true)
            }
            R.id.nav_deleted_contacts -> {
                val deletedContactListFragment = DeletedContactListFragment.newInstance()
                pushFragments("deletedContactListFragment", deletedContactListFragment, true)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        //switching fragment
        if (fragment != null) {
            // mStacks[mCurrentTab]!!.push(fragment)
            val ft = supportFragmentManager.beginTransaction()
            ft.setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            //ft.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            ft.replace(R.id.fragment_container, fragment).commit()
            return true
        }
        return false
    }

    fun pushFragments(tag: String, fragment: Fragment, shouldAdd: Boolean) {
        //if (shouldAdd)
        // mStacks[tag]!!.push(fragment)
        val manager = supportFragmentManager
        val ft = manager.beginTransaction()
        ft.setCustomAnimations(
            android.R.anim.fade_in,
            android.R.anim.fade_out,
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
//        ft.setCustomAnimations(
//            R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_left
//        )
        ft.replace(R.id.fragment_container, fragment)
        ft.commit()
    }

    fun popFragments() {
        /*
         *    Select the second last fragment in current tab's stack..
         *    which will be shown after the fragment transaction given below
         */
        val fragment = mStacks[mCurrentTab]?.elementAt(mStacks[mCurrentTab]!!.size - 2)

        /*pop current fragment from stack.. */
        mStacks[mCurrentTab]?.pop()

        /* We have the target fragment in hand.. Just show it.. Show a standard navigation animation*/
        val manager = supportFragmentManager
        val ft = manager.beginTransaction()
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        fragment?.let { ft.replace(R.id.fragment_container, it) }
        ft.commit()
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    fun getScreenOrientation(): Int {
        return resources.configuration.orientation
    }


}


