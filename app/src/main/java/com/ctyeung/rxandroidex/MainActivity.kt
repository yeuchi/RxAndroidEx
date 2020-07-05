package com.ctyeung.rxandroidex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.ctyeung.rxandroidex.combinelatest.Credentials
import com.ctyeung.rxandroidex.databinding.ActivityMainBinding
import com.ctyeung.rxandroidex.debounce.EmailEditText
import com.ctyeung.rxandroidex.maps.TransformStuff
import com.ctyeung.rxandroidex.network_retrofit.RequestsRetrofit
import com.ctyeung.rxandroidex.timer.LinearTimeTask
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.main = this
    }

    fun onClickRxKotlin() {
        navigate2Activity(RxKotlinActivity::class.java)
    }

    fun onClickCoroutine() {
        navigate2Activity(CoroutineActivity::class.java)
    }

    fun navigate2Activity(classType:Class<*>)
    {
        val intent = Intent(this.applicationContext, classType)
        startActivity(intent)
    }
}
