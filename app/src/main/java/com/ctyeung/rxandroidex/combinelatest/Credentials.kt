package com.ctyeung.rxandroidex.combinelatest

import android.widget.EditText
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

/*
 * https://medium.com/mindorks/android-form-validation-with-rxjava-3a8673886101
 */
class Credentials {
    var refresh:((String)->Unit)? = null
    var observable: Observable<Boolean>? = null
    var username: EditText? = null
    var password: EditText? = null

    constructor(refresh:((String)->Unit)?=null) {
        this.refresh = refresh
    }

    fun map(username: EditText?, password: EditText?) {
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
                .subscribe { item: String? ->
                    println(
                        item
                    )
                }
        }
    }

    private fun doTranform(str: CharSequence): String? {
        return null
    }


    fun destroy() {

    }
}