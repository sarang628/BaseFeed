package com.sarang.base_feed

import android.content.Context
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.example.torang_core.navigation.MenuBottomSheetNavigation
import com.example.torang_core.navigation.MyMenuBottomSheetNavigation
import com.example.torang_core.navigation.NotLoggedInMenuBottomSheetNavigation
import com.example.torang_core.navigation.ReportNavigation
import com.example.torang_core.util.EventObserver
import com.example.torang_core.util.Logger

class ReportProcessor constructor(
    val baseFeedViewModel: BaseFeedViewModel,
    private val menuBottomSheetNavigation: MenuBottomSheetNavigation,
    private val myMenuBottomSheetNavigation: MyMenuBottomSheetNavigation,
    private val notLoggedInMenuBottomSheetNavigation: NotLoggedInMenuBottomSheetNavigation,
    private val reportNavigation: ReportNavigation,
    val context : Context

) : DefaultLifecycleObserver {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        Logger.d("신고 프로세스 onCreate")
        subScribeUI(owner)
    }

    private fun subScribeUI(owner: LifecycleOwner) {
        Logger.d("신고 프로세스 subScribeUI")

    }

}