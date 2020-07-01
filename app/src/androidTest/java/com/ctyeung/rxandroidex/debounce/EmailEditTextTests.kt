package com.ctyeung.rxandroidex.debounce

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.ctyeung.rxandroidex.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Exception
import java.util.*


@RunWith(AndroidJUnit4ClassRunner::class)
class EmailEditTextTests {

    @get:Rule
    var rules: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java
    )

    @Test
    fun debounce() {

        var startTime = System.currentTimeMillis()

        var result : (String) -> Unit = { count: String ->
            val endTime = System.currentTimeMillis()
            if(startTime == 0L)
                throw Exception("startTime is 0")

            else {
                val elapsed = endTime - startTime
                if(elapsed < EmailEditText.PERIOD)
                    throw Exception("elapsed:${elapsed}")
            }
        }

        var editText = rules.activity.binding.editEmail
        var email = EmailEditText(result)
        email.debounce(editText)
    }

}