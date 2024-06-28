package com.example.jetpackdemo.kotlinLearn

import android.os.Build
import android.util.Log
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableSubscriber
import org.reactivestreams.Subscription

fun main() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        Flowable.create({
            it.onNext(1)
            Log.d("TAG", "send 1")
            it.onNext(2)
            Log.d("TAG", "send 1")
            it.onComplete()
        },
            BackpressureStrategy.ERROR
        ).subscribe(object : FlowableSubscriber<Int>{
            override fun onSubscribe(s: Subscription) {
                s.request(2)
            }

            override fun onError(t: Throwable?) {
                println("Error occurred")
            }

            override fun onComplete() {
                println("onComplete")
            }

            override fun onNext(t: Int?) {
                println("get the $t")
            }
        })

    }
}