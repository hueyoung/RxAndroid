package com.ytl.myeditext;

/**
 * explanation :
 * Created by ytl on 2016/8/23.
 */
public class Child extends Father implements TextChangeListener
{
//    Child(Object ob)
//    {
//        super(ob);
//    }
    @Override
    public void onChange()
    {
        System.out.print("onchange......");
    }
}
