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
class RxCredentials : BaseCredentials {

    constructor(alertUsername:((Boolean)->Unit)?=null,
                alertPassword:((Boolean)->Unit)?=null,
                enableButton:((Boolean)->Unit)?=null) {
        this.enableButton = enableButton
        this.alertUsername = alertUsername
        this.alertPassword = alertPassword
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
}