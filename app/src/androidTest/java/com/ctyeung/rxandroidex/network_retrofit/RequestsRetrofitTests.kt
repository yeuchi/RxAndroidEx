package com.ctyeung.rxandroidex.network_retrofit

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import junit.framework.Assert
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*


@RunWith(AndroidJUnit4ClassRunner::class)
class RequestsRetrofitTests {
    val timer = Timer()

    @Test
    fun getUsers() {
        var request = RequestsRetrofit()
        request.getUsers()

        var i = 0
        timer.scheduleAtFixedRate(
            object : TimerTask() {

                override fun run() {
                    if(i==1)
                        Assert.assertEquals(64, request.list?.size)

                    i++
                }
            },
            0, 1000
        )
    }
}