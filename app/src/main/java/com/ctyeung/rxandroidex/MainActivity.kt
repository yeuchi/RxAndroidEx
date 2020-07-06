package com.ctyeung.rxandroidex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ctyeung.rxandroidex.databinding.ActivityMainBinding

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
