package com.buyi.rxjavatutorial.schedule;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by buyi on 16/1/6.
 */
//因此如果有多次切换线程的需求，只要在每个想要切换线程的位置调用一次 observeOn() 即可
//subscribeOn() 的线程切换发生在 OnSubscribe 中，即在它通知上一级 OnSubscribe 时，这时事件还没有开始发送，因此 subscribeOn() 的线程控制可以从事件发出的开端就造成影响；而 observeOn() 的线程切换则发生在它内建的 Subscriber 中，即发生在它即将给下一级 Subscriber 发送事件时，因此 observeOn() 控制的是它后面的线程。
public class ScheduleDemo implements  Runnable {
    Func1<Integer, String> mapOperator = new Func1<Integer, String>() {
        @Override
        public String call(Integer s) {
            System.out.println("mapOperator" + Thread.currentThread());
            return s + " go around mapOperator" ;
        }
    };

    Func1<String, String> mapOperator2 = new Func1<String, String>() {
        @Override
        public String call(String s) {
            System.out.println("mapOperator2" + Thread.currentThread());
            return s + " go around mapOperator2" ;
        }
    };

    Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            System.out.println("onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("onError" + e);
        }

        @Override
        public void onNext(String o) {
            System.out.println("onNext" + Thread.currentThread());
            System.out.println("onNext" + o);
        }
    };


    @Override
    public void run() {
        Observable.just(1, 2, 3, 4) // IO 线程，由 subscribeOn() 指定
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .map(mapOperator) // 新线程，由 observeOn() 指定
                .observeOn(Schedulers.io())
                .map(mapOperator2) // IO 线程，由 observeOn() 指定
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);  // Android 主线程，由 observeOn() 指定
    }
}
