package com.buyi.rxjavatutorial.observable;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

/**
 * Created by buyi on 16/1/5.
 */
//an Observable emitting nothing and never terminating
public class NeverObservable implements  Runnable {
    @Override
    public void run() {
        Observable<String> observableString = Observable.never();
        Subscription subscriptionPrint = observableString.subscribe(new Observer<String>
                () {
            @Override
            public void onCompleted() {
                System.out.println("Observable completed");
            }
            @Override
            public void onError(Throwable e) {
                System.out.println("Oh no! Something wrong happened!");
            }
            @Override
            public void onNext(String message) {
                System.out.println(message);
            }
        });
    }

    private String helloWorld() {
        return "Hello World";
    }
}
