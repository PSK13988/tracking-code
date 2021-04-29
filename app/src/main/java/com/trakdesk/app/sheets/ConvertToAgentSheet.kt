package com.trakdesk.app.sheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trakdesk.app.R
import com.trakdesk.app.fragments.BaseFragment

class ConvertToAgentSheet : BaseBottomSheet() {

    companion object {
        val TAG = ConvertToAgentSheet::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme);
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.bottom_sheet_convert_to_agent, container, false)
}