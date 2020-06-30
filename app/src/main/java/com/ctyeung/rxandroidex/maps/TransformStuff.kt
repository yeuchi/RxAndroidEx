package com.ctyeung.rxandroidex.maps

import io.reactivex.Flowable.just
import io.reactivex.Observable
import io.reactivex.Observable.just
import io.reactivex.ObservableSource
import io.reactivex.Single.just
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function

/*
 * source
 * https://stackoverflow.com/questions/22847105/when-do-you-use-map-vs-flatmap-in-rxjava
 */
class TransformStuff {

    var action:((String)->Unit)?=null
    var refresh:((String?)->Unit)?=null
    var i=0

    constructor(refresh:((String?)->Unit)?=null, action:((String)->Unit)?=null) {
        this.refresh = refresh
        this.action = action
    }

    fun map() {
        Observable.just("item${i++}").map({ str ->
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

    fun flatmap() {
        Observable.just<String>("item${i++}")
            .flatMap(
                Function<String, ObservableSource<out String>> { str: String ->
                    doTransform(str)
                    Observable.just<String>("$str+", "$str++", "$str+++")
                }
            ).subscribe(Consumer<String> { s: String? ->
                refresh?.invoke(s)
            })
//            ).subscribe(Consumer<String> { s: String? ->
//                println(s)
//            })
    }
}