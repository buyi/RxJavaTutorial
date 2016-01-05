package com.buyi.rxjavatutorial.subject;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subjects.PublishSubject;

/**
 * Created by buyi on 16/1/5.
 */

//A subject is a magic object that can be an Observable and an Observer at the same time: it acts as a
//        bridge connecting the two worlds. A subject can subscribe to an Observable, acting like an Observer,
//        and it can emit new items or even pass through the item it received, acting like an Observable.
//        Obviously, being an Observable, Observers or other subjects can subscribe to it.
public class PublishSubjectDemo2 implements Runnable {


    @Override
    public void run() {
        final PublishSubject<Boolean> subject = PublishSubject.create();
        subject.subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onNext(Boolean completed) {
                System.out.println("Observable completed!");
            }
        });

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 5; i++) {
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }).doOnCompleted(new Action0() {
            @Override
            public void call() {
                subject.onNext(true);
            }
        }).subscribe();
        //the empty subscribe()
        //call just starts the Observable, ignoring any emitted value, completed or error event.
    }
}
