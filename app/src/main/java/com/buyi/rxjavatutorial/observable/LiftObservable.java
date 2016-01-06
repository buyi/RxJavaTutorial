package com.buyi.rxjavatutorial.observable;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by buyi on 16/1/6.
 */
public class LiftObservable implements Runnable {


//    public final <R> Observable<R> lift(Observable.Operator<? extends R,? super T> lift)
//    Lifts a function to the current Observable and returns a new Observable that when subscribed to will pass the values of the current Observable through the Operator function.
//    In other words, this allows chaining Observers together on an Observable for acting on the values within the Observable.
//            observable.map(...).filter(...).take(5).lift(new OperatorA()).lift(new OperatorB(...)).subscribe()
//    lift does not operate by default on a particular Scheduler.
//    参数:
//    lift - the Operator that implements the Observable-operating function to be applied to the source Observable
//    返回:
//    an Observable that is the result of applying the lifted Operator to the source Observable
    @Override
    public void run() {
        Observable.Operator operator = new Observable.Operator<String, String>(

        ) {
            @Override
            public Subscriber<? super String> call(final Subscriber<? super String> subscriber) {
                System.out.println("operator old subscriber:" + subscriber);
                Subscriber subscriber1 =  new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscriber.onError(e);
                    }

                    @Override
                    public void onNext(String s) {
                        subscriber.onNext(s);
                    }
                };
                System.out.println("operator new subscriber:" + subscriber1);
                return subscriber1;
            }
        };

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println("observable old subscriber:" + subscriber);
                subscriber.onNext("wocao!");
            }
        });
        observable.lift(operator).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:" + e);
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext:" + s);
            }
        });
    }
}
// 1.新生成的observable 调用subscribe，走新生成的observable生成的onSubscribe的逻辑。也就是lift函数中生成observable传递的onSubscribe中的逻辑。
// 2.observable传递的onSubscribe中的逻辑  第一步就是调用Observable.Operator的call方法 将新传递进来的subscriber（lift后生成oberservable注册的）转换成另一个subscriber（lift方法内部使用）
// 3.事件由最原始的observable 启动，调用的subscriber是在lift方法中创建的内部subscriber，它在通过Operator中做的某些额外处理再调用到新生成的observable注册的subscriber。
// http://www.tuicool.com/articles/3IjAbaY