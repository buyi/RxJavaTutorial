package com.buyi.rxjavatutorial.subject;

import rx.Observer;
import rx.Subscription;
import rx.subjects.PublishSubject;

/**
 * Created by buyi on 16/1/5.
 */

//A subject is a magic object that can be an Observable and an Observer at the same time: it acts as a
//        bridge connecting the two worlds. A subject can subscribe to an Observable, acting like an Observer,
//        and it can emit new items or even pass through the item it received, acting like an Observable.
//        Obviously, being an Observable, Observers or other subjects can subscribe to it.
public class PublishSubjectDemo implements Runnable {
    @Override
    public void run() {
        PublishSubject<String> stringPublishSubject = PublishSubject.create();
        Subscription subscriptionPrint = stringPublishSubject.subscribe(new Observer<String>() {
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
        stringPublishSubject.onNext("Hello World");
    }
}
