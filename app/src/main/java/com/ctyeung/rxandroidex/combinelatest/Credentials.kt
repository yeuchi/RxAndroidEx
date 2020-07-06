package com.ctyeung.rxandroidex.combinelatest

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.ctyeung.rxandroidex.debounce.EmailEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Credentials : BaseCredentials {

    var isUsernameValid = false
    var isPasswordValid = false

    constructor(alertUsername:((Boolean)->Unit)?=null,
                alertPassword:((Boolean)->Unit)?=null,
                enableButton:((Boolean)->Unit)?=null) {
        this.enableButton = enableButton
        this.alertUsername = alertUsername
        this.alertPassword = alertPassword
    }

    /*
     * not need to use coroutine ??
     */
    fun combine(username: EditText?, password: EditText?) {
        this.username = username
        this.password = password

        username?.addTextChangedListener(usernameWatcher)
        password?.addTextChangedListener(passwordWatcher)
    }

    val usernameWatcher = object : TextWatcher {
        private var searchFor = ""

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            isUsernameValid = isValidUsername(s.toString())
            onHandleResult(listOf(isUsernameValid, isPasswordValid))
        }

        override fun afterTextChanged(s: Editable?) = Unit
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    }

    val passwordWatcher = object : TextWatcher {
        private var searchFor = ""

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            isPasswordValid = isValidPassword(s.toString())
            onHandleResult(listOf(isUsernameValid, isPasswordValid))
        }

        override fun afterTextChanged(s: Editable?) = Unit
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    }
}