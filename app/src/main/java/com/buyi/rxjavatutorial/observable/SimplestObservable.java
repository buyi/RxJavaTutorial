package com.buyi.rxjavatutorial.observable;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by buyi on 16/1/5.
 */


//        The create() method gives the developer the ability to create an Observable from scratch. It takes
//        an OnSubscribe object, which extends Action1, as a parameter and executes the call() function
//        when an Observer subscribes to our Observable:
public class SimplestObservable implements  Runnable {


    @Override
    public void run() {
        Observable<Integer> observableString = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> observer) {
                for (int i = 0; i < 5; i++) {
                    observer.onNext(i);
                }
                observer.onCompleted();
            }
        });
        Subscription subscriptionPrint = observableString.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Observable completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("Oh no! Something wrong happened!");
            }

            @Override
            public void onNext(Integer item) {
                System.out.println("Item is " + item);
            }
        });
    }
}
