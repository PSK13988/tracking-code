package com.trakdesk.app.sheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trakdesk.app.R

class ProfilePictureOptionSheet : BaseBottomSheet() {
    companion object {
        val TAG = ProfilePictureOptionSheet::class.java.simpleName
        fun newInstance(): ProfilePictureOptionSheet {
            return ProfilePictureOptionSheet()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.bottom_sheet_profile_picture_option, container, false)
}