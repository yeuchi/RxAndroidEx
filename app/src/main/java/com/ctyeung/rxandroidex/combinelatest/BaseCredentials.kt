package com.ctyeung.rxandroidex.combinelatest

import android.widget.EditText

open class BaseCredentials {
    var enableButton:((Boolean)->Unit)? = null
    var alertUsername:((Boolean)->Unit)? = null
    var alertPassword:((Boolean)->Unit)? = null

    var username: EditText? = null
    var password: EditText? = null

    fun isValidUsername(str: CharSequence): Boolean {
        // validate string here ?
        val MIN_CHARACTERS = 7
        var isValid = false

        if(str.length >= MIN_CHARACTERS) {
            isValid = true
        }

        this.alertUsername?.invoke(isValid)
        return isValid
    }

    fun isValidPassword(str: CharSequence): Boolean {
        val MIN_CHARACTERS = 7
        val PASSWORD ="password"
        var isValid = false

        if(str.length >= MIN_CHARACTERS &&
            !str.equals(PASSWORD)) {
            isValid = true
        }

        this.alertPassword?.invoke(isValid)
        return isValid
    }

    fun onHandleResult(list:List<Any>) {
        var enable = true

        for(item in list) {
            if (item == false) {
                enable = false
            }
        }
        enableButton?.invoke(enable)
    }
}