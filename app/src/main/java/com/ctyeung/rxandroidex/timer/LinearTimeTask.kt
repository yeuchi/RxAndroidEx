package com.ctyeung.rxandroidex.timer

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

/*
 * Reference:
 * https://medium.com/@gabrieldemattosleon/fundamentals-of-rxjava-with-kotlin-for-absolute-beginners-3d811350b701
 */
class LinearTimeTask {
    val DELAY_MILLI_SECONDS:Long = 1000
    val REPEAT_COUNT:Long = 1000
    var refresh: ((String) -> Unit)? = null
    var disposable:Disposable?=null
    constructor(refresh: ((String) -> Unit)? = null) {
        this.refresh = refresh
    }

    fun startCoroutineTimer() {

        var scope = CoroutineScope(Dispatchers.Main).launch {

            var i:Long = 0
            delay(DELAY_MILLI_SECONDS)
            if (REPEAT_COUNT > 0) {
                while (true) {
                    onHandleResponse(i++)
                    delay(DELAY_MILLI_SECONDS)
                }
            } else {
                onHandleResponse(i)
            }
        }
    }

    fun startInterval() {
        disposable = Observable.interval(DELAY_MILLI_SECONDS, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onHandleResponse)
    }

    fun onHandleResponse(time:Long) {
        val timeString = time.toString()
        this.refresh?.invoke(timeString)
    }

    fun destroy() {
        if(disposable?.isDisposed == false)
            disposable?.dispose()
    }
}