package com.buyi.rxjavatutorial.subject;

import rx.Observer;
import rx.subjects.AsyncSubject;

/**
 * Created by buyi on 16/1/5.
 */
// Subject that publishes only the last item observed to each Observer that has subscribed, when the source Observable completes.
public class AsyncSubjectDemo implements  Runnable {
    @Override
    public void run() {

        Observer observer = new Observer<String>() {
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

        // observer will receive no onNext events because the subject.onCompleted() isn't called.
        AsyncSubject<Object> subject = AsyncSubject.create();
        subject.subscribe(observer);
        subject.onNext("one");
        subject.onNext("two");
        subject.onNext("three");

//        // observer will receive "three" as the only onNext event.
//        AsyncSubject<Object> subject = AsyncSubject.create();
//        subject.subscribe(observer);
//        subject.onNext("one");
//        subject.onNext("two");
//        subject.onNext("three");
//        subject.onCompleted();

    }
}
