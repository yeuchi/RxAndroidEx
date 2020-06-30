package com.ctyeung.rxandroidex.combinelatest

import android.widget.EditText
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

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

            RxTextView.afterTextChangeEvents(username)
                .skipInitialValue()
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    // display message -- toast
                }
//            val nameObservable: Observable<String> =
//                RxTextView.textChanges(username).skip(1)
//                    .map(object : Function<CharSequence?, String?>() {
//                        @Throws(Exception::class)
//                        fun apply(charSequence: CharSequence): String? {
//                            return charSequence.toString()
//                        }
//                    })

//            val passwordObservable: Observable<String> =
//                RxTextView.textChanges(password).skip(1)
//                    .map(object : Function<CharSequence?, String?>() {
//                        @Throws(java.lang.Exception::class)
//                        fun apply(charSequence: CharSequence): String? {
//                            return charSequence.toString()
//                        }
//                    })

//            observable = Observable.combineLatest(
//                nameObservable,
//                passwordObservable,
//                object : BiFunction<String?, String?, Boolean?>() {
//                    @Throws(java.lang.Exception::class)
//                    fun apply(s: String?, s2: String?): Boolean? {
//                        return isValidForm(s, s2)
//                    }
//                })
//            observable.subscribe(object : DisposableObserver<Boolean?>() {
//                override fun onNext(aBoolean: Boolean) {
//                    updateButton(aBoolean)
//                }
//
//                override fun onError(e: Throwable) {}
//                override fun onComplete() {}
//            })
        }
    }

//    fun updateButton(valid: Boolean) {
//        if (valid) btn_login.setEnabled(true)
//    }
//
//    fun isValidForm(name: String, password: String): Boolean {
//        val validName = !name.isEmpty()
//        if (!validName) {
//            this.username?.setError("Please enter valid name")
//        }
//        val validPass = !password.isEmpty() && password == AppPrefs.getPassword()
//        if (!validPass) {
//            this.password?.setError("Incorrect password")
//        }
//        return validName && validPass
//    }

    fun destroy() {

    }
}