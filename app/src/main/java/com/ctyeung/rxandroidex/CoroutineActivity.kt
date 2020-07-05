package com.ctyeung.rxandroidex

import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ctyeung.rxandroidex.combinelatest.Credentials
import com.ctyeung.rxandroidex.databinding.ActivityCoroutineBinding
import com.ctyeung.rxandroidex.databinding.ActivityCoroutineBindingImpl
import com.ctyeung.rxandroidex.debounce.EmailEditText
import com.ctyeung.rxandroidex.maps.TransformStuff
import com.ctyeung.networkrequestex.network_retrofit.coroutine.RequestsRetrofit
import com.ctyeung.rxandroidex.timer.LinearTimeTask
import java.lang.Exception

class CoroutineActivity : AppCompatActivity() {
    lateinit var binding: ActivityCoroutineBinding
    var timer: LinearTimeTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_coroutine)
        binding.coroutine = this

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

        //var transform = TransformStuff(refresh, action)
        //transform.map()
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

        //var transform = TransformStuff(refresh, action)
        //transform.flatmap()
    }

    /*
     * N -> 1 : CombineLast
     */
    fun onClickLogin() {
        showToast("Go Authentication")
    }

    fun initCredentials() {

        fun getVisibility(isValid:Boolean):Int {
            var visibility = TextView.VISIBLE

            if (isValid)
                visibility = TextView.INVISIBLE

            return visibility
        }

        var alertUsername : (Boolean) -> Unit = {
                isValid:Boolean ->
            binding?.txtUsernameWarning.visibility = getVisibility(isValid)
        }

        var alertPassword : (Boolean) -> Unit = {
                isValid:Boolean ->
            binding?.txtPasswordWarning.visibility = getVisibility(isValid)
        }

        var enableButton : (Boolean) -> Unit = {
                enabled:Boolean ->
            binding?.btnLogin.isEnabled = enabled
        }
       // var cred = Credentials(alertUsername, alertPassword, enableButton)
       // cred.combineLast(binding?.editUsername, binding?.editPassword)
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
        task.startCoroutineDebounce(binding?.editEmail)
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
                    timer?.startCoroutineTimer()
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
        catch(ex: Exception) {
            showToast("TryRxRetrofit() $ex")
        }
    }

    fun showToast(msg:String) {
        var toast = Toast.makeText(this, msg, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.TOP or Gravity.CENTER, 0, 0)
        toast.show()
    }
}