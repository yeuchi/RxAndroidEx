package com.ctyeung.rxandroidex

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ctyeung.rxandroidex.databinding.ActivityCoroutineBindingImpl

class CoroutineActivity : AppCompatActivity() {
    lateinit var binding: ActivityCoroutineBindingImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_coroutine)
        binding.coroutine = this
    }
}