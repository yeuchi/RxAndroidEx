package com.ctyeung.rxandroidex.debounce

import android.widget.EditText
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

/*
 * https://code.tutsplus.com/tutorials/kotlin-reactive-programming-for-an-android-sign-up-screen--cms-31585
 */
class EmailEditText {
    var refresh:((String)->Unit)? = null
    var observable: Observable<Boolean>? = null
    var emailAddress: EditText? = null

    constructor(refresh:((String)->Unit)?=null) {
        this.refresh = refresh
    }

    fun map(emailAddress: EditText?) {
        this.emailAddress = emailAddress

        if(emailAddress!=null) {

            RxTextView.afterTextChangeEvents(emailAddress)
                .skipInitialValue()
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    // display message -- toast
                }
        }
    }

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