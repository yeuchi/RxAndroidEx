package com.ctyeung.networkrequestex.network_retrofit.coroutine

import com.ctyeung.rxandroidex.network_retrofit.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception


class RequestsRetrofit {
    var refresh:((String, String)->Unit)? = null
    var startTime:Long = System.currentTimeMillis()
    var list:List<User>?=null

    constructor(refresh:((String, String)->Unit)?=null) {
        this.refresh = refresh
    }

    fun getUsers() = runBlocking {
        startTime = System.currentTimeMillis()

        var scope = CoroutineScope(Dispatchers.IO).launch {
            try {
                var apiService = RetrofitBuilder.apiService
                list = apiService.getUsers()
            } catch (e: Exception) {
                throw Exception(e.toString())
            }
        }
        scope.join()
        refresh?.invoke("size:${list?.size}", getElapsedString())
    }

    fun getElapsedString():String {
        var stopTime = System.currentTimeMillis()
        var elapsed = stopTime - startTime
        return elapsed.toString() + "ms"
    }
}

