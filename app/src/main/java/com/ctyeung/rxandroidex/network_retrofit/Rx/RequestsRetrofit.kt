package com.ctyeung.rxandroidex.network_retrofit.Rx

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import com.ctyeung.rxandroidex.network_retrofit.User
import com.ctyeung.rxandroidex.network_retrofit.Rx.RetrofitBuilder

class RequestsRetrofit {
    var startTime:Long = System.currentTimeMillis()
    var refresh:((String, String)->Unit)? = null
    var list:List<User>?=null
    var myCompositeDisposable = CompositeDisposable()

    constructor(refresh:((String, String)->Unit)?=null) {
        this.refresh = refresh
    }

    fun startTimer() {
        startTime = System.currentTimeMillis()
    }

    fun getUsers() {
        startTimer()

        val requestInterface = RetrofitBuilder.apiService

        myCompositeDisposable?.add(
            requestInterface.getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::onHandleResponse))
    }

    private fun onHandleResponse(users:List<User>) {
        this.list = users
        this.refresh?.invoke("size:${list?.size}", getElapsedString())
    }

    fun getElapsedString():String {
        var stopTime = System.currentTimeMillis()
        var elapsed = stopTime - startTime
        return elapsed.toString() + "ms"
    }

    fun destroy() {
        myCompositeDisposable?.clear()
    }
}