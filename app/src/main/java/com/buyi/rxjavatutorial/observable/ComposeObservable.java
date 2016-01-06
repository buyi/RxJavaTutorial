package com.buyi.rxjavatutorial.observable;

/**
 * Created by buyi on 16/1/6.
 */

//lift() 是针对事件项和事件序列的，而 compose() 是针对 Observable 自身进行变换
    // 同时针对多个obserable同时操作
public class ComposeObservable implements Runnable {

//    public static class LiftAllTransformer implements Observable.Transformer<Integer, String> {
//        @Override
//        public Observable<String> call(Observable<Integer> observable) {
//            return observable;
//        }
//    }
    @Override
    public void run() {


//        Observable.Transformer liftAll = new LiftAllTransformer();
//        .compose(liftAll).subscribe(new Observer<Integer>() {
//            @Override
//            public void onCompleted() {
//                System.out.println("Observable completed");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("Oh no! Something wrong happened!");
//            }
//
//            @Override
//            public void onNext(Integer item) {
//                System.out.println("Item is " + item);
//            }
//        });
//        observable2.compose(liftAll).subscribe(subscriber2);
//        observable3.compose(liftAll).subscribe(subscriber3);
//        observable4.compose(liftAll).subscribe(subscriber4);
    }
}
