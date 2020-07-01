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
    var toast:((String)->Unit)? = null
    var emailAddress: EditText? = null

    companion object {
        val PERIOD:Long = 400
    }

    constructor(refresh:((String)->Unit)?=null) {
        this.toast = refresh
    }

    fun debounce(emailAddress: EditText?) {
        this.emailAddress = emailAddress

        if(emailAddress!=null) {

            RxTextView.afterTextChangeEvents(emailAddress)
                .skipInitialValue()
                .debounce(PERIOD, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    toast?.invoke("debounced 400")
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