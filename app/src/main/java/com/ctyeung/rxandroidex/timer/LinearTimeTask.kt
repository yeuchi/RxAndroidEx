package com.ctyeung.rxandroidex.timer

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

/*
 * Reference:
 * https://medium.com/@gabrieldemattosleon/fundamentals-of-rxjava-with-kotlin-for-absolute-beginners-3d811350b701
 */
class LinearTimeTask {
    var refresh: ((String) -> Unit)? = null
    var disposable:Disposable?=null
    constructor(refresh: ((String) -> Unit)? = null) {
        this.refresh = refresh
    }

    fun startInterval() {
        disposable = Observable.interval(1000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onHandleResponse)
    }

    private fun onHandleResponse(time:Long) {
        val timeString = time.toString()
        this.refresh?.invoke(timeString)
    }

    fun destroy() {
        if(disposable?.isDisposed == false)
            disposable?.dispose()
    }
}