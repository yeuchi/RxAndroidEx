package com.ctyeung.rxandroidex.network_retrofit

import com.ctyeung.networkrequestex.network_rxjava.RetrofitBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RequestsRetrofit {
    var list: List<User>? = null
    var myCompositeDisposable = CompositeDisposable()
    var refreshUI:((String)->Unit)?=null

    fun getUsers(refresh: ((String) -> Unit)? = null) {
        this.refreshUI = refresh
        val requestInterface = RetrofitBuilder.apiService

//Add all RxJava disposables to a CompositeDisposable//
        myCompositeDisposable?.add(
            requestInterface.getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::onHandleResponse))
    }

    private fun onHandleResponse(users:List<User>) {
        this.list = users
        this.refreshUI?.invoke("size:${list?.size}")
    }

    fun destroy() {
        myCompositeDisposable?.clear()
    }
}