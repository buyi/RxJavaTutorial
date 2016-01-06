package com.buyi.rxjavatutorial.observable;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by buyi on 16/1/6.
 */
// 转类型 int转string
public class IntegerToStringObservable implements Runnable {
    @Override
    public void run() {
        //举一个具体的 Operator 的实现。下面这是一个将事件中的 Integer 对象转换成 String 的例子，仅供参考：


        Observable<Integer> observable = Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(1);
                subscriber.onCompleted();
            }
        });

        // 对于Operator而言 Integer是原始， String为目标
        // Operator的顺序 与 事件流的顺序相反。
        observable.lift(new Observable.Operator<String, Integer>() {
            // 相当于将lift之后注册subscriber 通过某种方式 转换成新subscriber 让其监听原始observable。（作为原始observable的观察者）
            @Override
            public Subscriber<? super Integer> call(final Subscriber<? super String> subscriber) {
                // 将事件序列中的 Integer 对象转换为 String 对象
                return new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        subscriber.onNext("" + integer);
                    }

                    @Override
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscriber.onError(e);
                    }
                };
            }
        }).subscribe(new Subscriber<String>() {
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
                System.out.println("onNext" + o);
            }
        });
    }
}
