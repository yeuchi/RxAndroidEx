package com.ctyeung.rxandroidex.maps

import io.reactivex.Flowable.just
import io.reactivex.Observable
import io.reactivex.Observable.just
import io.reactivex.Single.just

/*
 * source
 * https://stackoverflow.com/questions/22847105/when-do-you-use-map-vs-flatmap-in-rxjava
 */
class TransformStuff {

    var action:((String)->Unit)?=null
    var refresh:((String)->Unit)?=null
    constructor(refresh:((String)->Unit)?=null, action:((String)->Unit)?=null) {
        this.refresh = refresh
        this.action = action
    }

    fun register() {

    }

    fun map() {
        Observable.just("item1").map({ str ->
            doTransform(str)
            str
        }).subscribe(this::handleResult)
    }

    fun doTransform(str:String) {
        action?.invoke(str)
    }

    fun handleResult(str:String) {
        refresh?.invoke(str)
    }
}