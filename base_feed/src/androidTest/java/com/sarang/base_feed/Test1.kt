package com.sarang.base_feed

import android.view.LayoutInflater
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.sarang.base_feed.databinding.ItemTimeLine1Binding
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class Test1 {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.sarang.base_feed.test", appContext.packageName)
    }

    @Test
    fun useAppContext1() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val binding: ItemTimeLine1Binding =
            ItemTimeLine1Binding.inflate(LayoutInflater.from(appContext))

        binding.itemFeedTop.setData(
            menuClickListener = CustomClick {

            },
            clickRestaurantListener = CustomClick {

            },
            profileClickListener = CustomClick {

            }
        )
    }
}