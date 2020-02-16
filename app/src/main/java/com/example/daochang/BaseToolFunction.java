package com.example.daochang;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseToolFunction {

    //循环延迟
    public static void delay(int number){
        for(int i=0;i<number;i++);
    }

    //获取系统当前时间
    public static String getTime(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd    HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    //取最大值
    public static int Max(int a,int b){
        if(a>=b){
            return a;
        }else{
            return b;
        }
    }

    //取最小值
    public static int Min(int a,int b){
        if(a<=b){
            return a;
        }else{
            return b;
        }
    }

}
