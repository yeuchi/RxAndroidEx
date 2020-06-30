package com.ctyeung.rxandroidex.timer

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import junit.framework.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

import com.ctyeung.rxandroidex.MainActivity
import junit.framework.Assert.assertEquals
import androidx.test.rule.ActivityTestRule
import junit.framework.Assert.assertNotSame
import org.junit.Rule
import java.lang.Exception


@RunWith(AndroidJUnit4ClassRunner::class)
class LinearTimeTaskTests {
    val timer = Timer()

    @get:Rule
    var rules: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java
    )

    @Test
    fun startInterval() {

        var refresh : (String) -> Unit = {
                count: String ->

            val num = count.toInt()
            if(num !=0)
                throw Exception("Not increasing")
        }

        var task = LinearTimeTask(refresh)
        task.startInterval()
    }
}