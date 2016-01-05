package com.buyi.rxjavatutorial.subject;

import rx.Observer;
import rx.subjects.BehaviorSubject;
import rx.subjects.Subject;

/**
 * Created by buyi on 16/1/5.
 */
// http://www.boyunjian.com/javadoc/com.netflix.rxjava/rxjava-core/0.19.0/_/rx/subjects/BehaviorSubject.html

//a subject that emits the most recent item it has observed and all
//        subsequent observed items to each subscribed item:
public class BehaviorSubjectDemo implements Runnable {
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

        BehaviorSubject<String> subject = BehaviorSubject.create("default");

        event1(subject, observer);

//        event2(subject, observer);

//        event3(subject, observer);

//        event4(subject, observer);
    }

    // observer will receive all events.
    private void event1 (Subject subject, Observer observer) {
        subject.subscribe(observer);
        subject.onNext("one");
        subject.onNext("two");
        subject.onNext("three");
    }


    // observer will receive the "one", "two" and "three" events, but not "zero"
    private void event2 (Subject subject, Observer observer) {
        subject.onNext("zero");
        subject.onNext("one");
        subject.subscribe(observer);
        subject.onNext("two");
        subject.onNext("three");
    }

    // observer will receive only onCompleted
    private void event3 (Subject subject, Observer observer) {
        subject.onNext("zero");
        subject.onNext("one");
        subject.onCompleted();
        subject.subscribe(observer);
    }

    // observer will receive only onError
    private void event4 (Subject subject, Observer observer) {
        subject.onNext("zero");
        subject.onNext("one");
        subject.onError(new RuntimeException("error"));
        subject.subscribe(observer);
    }

}
