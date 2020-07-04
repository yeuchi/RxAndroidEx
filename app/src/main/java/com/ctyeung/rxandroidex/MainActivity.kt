package com.ctyeung.rxandroidex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
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
    lateinit var binding:ActivityMainBinding
    var timer:LinearTimeTask?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.layout = this

        initCredentials()
    }

    /*
     * 1 -> 1 transform
     */
    fun onClickMap() {

        var action : (String) -> Unit = {
                msg: String ->
            binding?.txtMapAction.text = "action: ${msg}"
        }

        var refresh : (String?) -> Unit = {
                msg: String? ->
            binding?.txtMapResult.text = "result: ${msg}"
        }

        var transform = TransformStuff(refresh, action)
        transform.map()
    }

    /*
     * 1 -> N async transform
     */
    fun onClickFlatmap() {

        var action : (String) -> Unit = {
                msg: String ->
            binding?.txtFlatmapAction.text = "action: ${msg}"
        }

        var refresh : (String?) -> Unit = {
                msg: String? ->
            binding?.txtFlatmapResult.text = "result: ${msg}"
        }

        var transform = TransformStuff(refresh, action)
        transform.flatmap()
    }

    /*
     * N -> 1 : CombineLast
     */
    fun onClickLogin() {

    }

    fun initCredentials() {
        var enableButton : (Boolean) -> Unit = {
            enabled:Boolean ->
            binding?.btnLogin.isEnabled = enabled
        }
        var cred = Credentials(enableButton)
        cred.combineLast(binding?.editUsername, binding?.editPassword)
    }

    /*
     * wait N seconds and execute last (not priors)
     */
    fun onClickDebounce() {

        var refresh : (String) -> Unit = {
                msg: String ->
            showToast(msg)
        }

        var task = EmailEditText(refresh)
        task.debounce(binding?.editEmail)
    }

    /*
     * replace timer with Rx operator
     */
    fun onClickTimer() {
        try {
            when {
                timer != null -> {
                    timer?.destroy()
                    timer = null
                }

                else -> {
                    var refresh : (String) -> Unit = {
                            count: String ->
                        binding?.txtTimerCount.text = count
                    }

                    timer = LinearTimeTask(refresh)
                    timer?.startInterval()
                }
            }
        }
        catch(ex: Exception) {
            showToast("tryTimer() $ex")
        }
    }

    fun onClickRetrofit () {
        try {
            var refreshUI : (String, String) -> Unit = {
                    count: String, elapsed: String ->
                binding.txtRxRetrofitElapsed.text = elapsed
                binding.txtRxRetrofitUserCount.text = count
            }

            var request = RequestsRetrofit(refreshUI)
            request.getUsers()
        }
        catch(ex:Exception) {
            showToast("TryRxRetrofit() $ex")
        }
    }

    fun showToast(msg:String) {
        var toast = Toast.makeText(this, msg, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.TOP or Gravity.CENTER, 0, 0)
        toast.show()
    }
}
