package com.ytl.myeditext;

/**
 * explanation :
 * Created by ytl on 2016/8/23.
 */
public class Father
{
    TextChangeListener listener;
    Father(){

    }
     public Father(Object ob)
    {
        if (ob instanceof Father)
        {
            listener = (TextChangeListener)ob;
            listener.onChange();
        }
    }


}
