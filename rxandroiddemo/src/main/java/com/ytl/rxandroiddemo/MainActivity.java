package com.ytl.rxandroiddemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ytl.rxandroiddemo.fragment.BlankFragment;
import com.ytl.rxandroiddemo.rxbus.RxBus;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener
{
    CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       /* Subscriber<Object> mySubscriber = new Subscriber<Object>() {
            @Override
            public void onNext(Object s)
            {
                Toast.makeText(MainActivity.this, s.toString(), Toast.LENGTH_SHORT).show();
                ((TextView)findViewById(R.id.tv_info)).setText(s.toString());
            }

            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) { }
        };*/

        Subscription subscription2 = RxBus.getInstance()
                .doSubscribe(Integer.class, new Action1<Integer>() {
                    @Override
                    public void call(Integer s) {
                        Toast.makeText(MainActivity.this, s.toString(), Toast.LENGTH_SHORT).show();
                        ((TextView)findViewById(R.id.tv_info)).setText(s.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        RxBus.getInstance().addSubscription(this, subscription2);
    }

    public void btnOpenFrag(View v)
    {
        FragmentManager mange = getSupportFragmentManager();
        FragmentTransaction tran = mange.beginTransaction();
        tran.replace(R.id.ll_fragment, BlankFragment.newInstance("hell","hi"));
        tran.commit();
    }

    public void btnRxSend(View v)
    {
        /*RxBus.getInstance().toObserverable().create(
                new Observable.OnSubscribe<String>(){
                    @Override
                    public void call(Subscriber<? super String> subscriber)
                    {
                        subscriber.onNext("hello, erveryBoday! i am ob from main ");
                    }
                }
        );*/

        RxBus.getInstance().post(1024);
    }

    public void btnRemove(View v)
    {
        RxBus.getInstance().unSubscribe(this);
    }
    @Override
    public void onFragmentInteraction(Uri uri)
    {

    }
}
