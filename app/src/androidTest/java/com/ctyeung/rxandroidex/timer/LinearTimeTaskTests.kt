package com.ctyeung.rxandroidex.timer

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

import com.ctyeung.rxandroidex.MainActivity
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import java.lang.Exception
import junit.framework.Assert

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
            Assert.assertEquals(num, 0)
        }

        var task = LinearTimeTask(refresh)
        task.startInterval()
    }
}