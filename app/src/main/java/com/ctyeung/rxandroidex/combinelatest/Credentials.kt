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
    var enableButton:((Boolean)->Unit)? = null

    var username: EditText? = null
    var password: EditText? = null

    constructor(enableButton:((Boolean)->Unit)?=null) {
        this.enableButton = enableButton
    }

    fun combineLast(username: EditText?, password: EditText?) {
        this.username = username
        this.password = password

        if(username!=null && password!=null) {

            val nameObservable: Observable<Boolean> =
                RxTextView.textChanges(username).skip(1)
                    .map({str -> isValidUsername(str)})

            val passwordObservable: Observable<Boolean> =
                RxTextView.textChanges(password).skip(1)
                    .map({str -> isValidPassword(str)})

            Observable.combineLatest(nameObservable,
                passwordObservable,
                BiFunction { observable1Times: Any, observable2Times: Any -> listOf(observable1Times, observable2Times) }
            )
                .subscribe { item: List<Any> -> onHandleResult(item) }
        }
    }

    private fun isValidUsername(str: CharSequence): Boolean {
        // validate string here ?
        val MIN_CHARACTERS = 7
        if(str.length >= MIN_CHARACTERS)
            return true
        else
            return false
    }

    private fun isValidPassword(str: CharSequence): Boolean {
        val MIN_CHARACTERS = 7
        val PASSWORD ="password"
        if(str.length >= MIN_CHARACTERS &&
            !str.equals(PASSWORD))
            return true
        else
            return false
    }

    private fun onHandleResult(list:List<Any>) {
        var enable = true

        for(item in list) {
            if (item == false) {
                enable = false
            }
        }
        enableButton?.invoke(enable)
    }

    fun destroy() {

    }
}