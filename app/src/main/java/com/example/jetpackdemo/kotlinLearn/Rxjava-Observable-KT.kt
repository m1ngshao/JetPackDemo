package com.example.jetpackdemo.kotlinLearn

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

fun main() {
    val firstObservable = Observable.create {
        it.onNext("Hello Observer")
        it.onComplete()
    }

    Observable.create{
        println("Subscribe:" + Thread.currentThread())
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        it.onNext("guess the Thread")
        it.onComplete()
    }.subscribeOn(Schedulers.io())
        .subscribe(object : Observer<Any> {
        var mDisposable : Disposable? = null
        override fun onSubscribe(d: Disposable) {
            println("onSubscribe:" + Thread.currentThread())
//            mDisposable = d
        }

        override fun onNext(t: Any) {
            println("onNext:$t ${Thread.currentThread()}")
//            if(t == "stop") mDisposable!!.dispose()
        }

        override fun onError(e: Throwable) {
            println(e)
        }

        override fun onComplete() {
            println("onComplete: ${Thread.currentThread()}")
        }
    })
}