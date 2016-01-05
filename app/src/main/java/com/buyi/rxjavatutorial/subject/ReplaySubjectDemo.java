package com.buyi.rxjavatutorial.subject;

import rx.Observer;
import rx.subjects.ReplaySubject;

/**
 * Created by buyi on 16/1/5.
 */
//Subject that buffers all items it observes and replays them to any Observer that subscribes.
public class ReplaySubjectDemo implements  Runnable {
    @Override
    public void run() {
//        ReplaySubject<String> replaySubject = ReplaySubject.create();

        ReplaySubject<Object> subject = ReplaySubject.create();
        subject.onNext("one");
        subject.onNext("two");
        subject.onNext("three");
        subject.onCompleted();

        Observer observer1 = new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Observable completed");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                System.out.println("Oh no! Something wrong happened!");
            }

            @Override
            public void onNext(String message) {
                System.out.println(message);
            }
        };

        Observer observer2 = new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("Observable completed");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                System.out.println("Oh no! Something wrong happened!");
            }

            @Override
            public void onNext(String message) {
                System.out.println(message);
            }
        };


        // both of the following will get the onNext/onCompleted calls from above
        subject.subscribe(observer1);
        subject.subscribe(observer2);
    }
}
