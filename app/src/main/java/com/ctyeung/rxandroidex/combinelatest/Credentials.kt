package com.ctyeung.rxandroidex.combinelatest

import android.widget.EditText
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

/*
 * CombineLast - similar to merge but emits only when each observable has changed at least once.
 *
 * References
 * https://medium.com/mindorks/android-form-validation-with-rxjava-3a8673886101
 * https://proandroiddev.com/exploring-rxjava-in-android-operators-for-combining-observables-25734080f4be
 */
class Credentials {
    var enableButton:((String)->Unit)? = null

    var username: EditText? = null
    var password: EditText? = null

    constructor(enableButton:((String)->Unit)?=null) {
        this.enableButton = enableButton
    }

    fun combineLast(username: EditText?, password: EditText?) {
        this.username = username
        this.password = password

        if(username!=null && password!=null) {

            val nameObservable: Observable<String> =
                RxTextView.textChanges(username).skip(1)
                    .map({str -> doTranform(str)})

            val passwordObservable: Observable<String> =
                RxTextView.textChanges(password).skip(1)
                    .map({str -> doTranform(str)})

            Observable.combineLatest(nameObservable,
                passwordObservable,
                BiFunction { observable1Times: Any, observable2Times: Any -> "Refreshed Observable1: $observable1Times refreshed Observable2: $observable2Times" }
            )
                .subscribe { item: String? -> onHandleResult(item) }
        }
    }

    private fun doTranform(str: CharSequence): String? {
        // validate string here ?
        return str.toString()
    }

    private fun onHandleResult(item:String?) {
        // enable button here
        var str = ""
        if(item != null)
            str = item

        enableButton?.invoke(str)
    }

    fun destroy() {

    }
}