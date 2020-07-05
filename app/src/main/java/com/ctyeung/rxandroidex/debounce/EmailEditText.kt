package com.ctyeung.rxandroidex.debounce

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

/*
 * https://code.tutsplus.com/tutorials/kotlin-reactive-programming-for-an-android-sign-up-screen--cms-31585
 */
class EmailEditText {
    var toast:((String)->Unit)? = null
    var emailAddress: EditText? = null

    companion object {
        val PERIOD:Long = 1000
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
                    this.toast?.invoke("debounced 400")
                }
        }
    }

    fun startCoroutineDebounce(emailAddress: EditText?) {
        emailAddress?.addTextChangedListener(watcher)
    }

    val watcher = object :TextWatcher{
        private var searchFor = ""

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val searchText = s.toString().trim()
            if (searchText == searchFor)
                return

            var scope = CoroutineScope(Dispatchers.Main).launch {
                delay(PERIOD)  //debounce timeOut

                // do our magic here
                onHandleResponse(PERIOD)
            }
        }

        override fun afterTextChanged(s: Editable?) = Unit
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    }

    fun onHandleResponse(time:Long) {
        val timeString = time.toString()
        this.toast?.invoke(timeString)
    }

    fun destroy() {

    }
}