package com.trakdesk.app.sheets

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.trakdesk.app.R
import kotlinx.android.synthetic.main.bottom_sheet_add_contact.*

class AddContactSheet : BaseBottomSheet() {

    companion object {
        val TAG = AddContactSheet::class.java.simpleName
        fun newInstance(): AddContactSheet {
            return AddContactSheet()
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        dialog?.also {
            val bottomSheet = dialog!!.findViewById<View>(R.id.design_bottom_sheet)
            bottomSheet.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            val behavior = BottomSheetBehavior.from<View>(bottomSheet)
            behavior.peekHeight =
                resources.displayMetrics.heightPixels - 150//replace to whatever you want
            view?.requestLayout()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_add_contact, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ivProfileIcon.setOnClickListener {
            val profilePictureOptionSheet = ProfilePictureOptionSheet.newInstance()
            fragmentManager?.let {
                profilePictureOptionSheet.show(
                    it,
                    profilePictureOptionSheet.tag
                )
            }
        }
    }
}
