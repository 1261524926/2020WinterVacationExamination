package com.example.daochang;

//单例设计模式，头文件性质
public class CurrentData {
    int status=0;//status即状态值，用于判断当前登录数据信息的状态，0值表示未登录，1值表示登录成功，-1值表示登录数据读入错误，默认状态为0

    //用户数据
    int id=-9999;
    String userName=null;
    String avatar=null;
    String token=null;

    //临时缓存数据，文本型和数值型各4个通道
    int flag=-9999;
    String bufferString1=null;
    String bufferString2=null;
    String bufferString3=null;
    String bufferString4=null;
    int bufferInt1=-9999;
    int bufferInt2=-9999;
    int bufferInt3=-9999;
    int bufferInt4=-9999;


    //存在唯一currentData
    public static CurrentData currentData=new CurrentData();
    private CurrentData(){
    }


    public static CurrentData getCurrentData(){
        return currentData;
    }
    //设置用户数据方法
    public static void setStatus(int status){
        currentData.status=status;
    }
    public static void setUserName(String userName){
        currentData.userName=userName;
    }
    public static void setToken(String token){
        currentData.token=token;
    }
    public static void setID(int id){currentData.id=id;}
    public static void setAvatar(String avatar){currentData.avatar=avatar;}
    //获取用户数据方法
    public static int getStatus(){return currentData.status;}
    public static String getUserName(){return currentData.userName;}
    public static String getToken(){return currentData.token;}
    public static int getID(){return currentData.id;}
    public static String getAvatar(){return currentData.avatar;}


    //设置临时缓存数据方法
    public static void setBufferString1(String bufferString1){currentData.bufferString1=bufferString1;}
    public static void setBufferInt1(int bufferInt1){currentData.bufferInt1=bufferInt1;}
    public static void setBufferString2(String bufferString2){currentData.bufferString2=bufferString2;}
    public static void setBufferInt2(int bufferInt2){currentData.bufferInt2=bufferInt2;}
    public static void setBufferString3(String bufferString3){currentData.bufferString3=bufferString3;}
    public static void setBufferInt3(int bufferInt3){currentData.bufferInt1=bufferInt3;}
    public static void setBufferString4(String bufferString4){currentData.bufferString4=bufferString4;}
    public static void setBufferInt4(int bufferInt4){currentData.bufferInt4=bufferInt4;}
    public static void setFlag(int flag){currentData.flag=flag;}
    //获取临时缓存数据方法
    public static String getBufferString1(){return currentData.bufferString1;}
    public static int getBufferInt1(){return currentData.bufferInt1;}
    public static String getBufferString2(){return currentData.bufferString2;}
    public static int getBufferInt2(){return currentData.bufferInt2;}
    public static String getBufferString3(){return currentData.bufferString3;}
    public static int getBufferInt3(){return currentData.bufferInt3;}
    public static String getBufferString4(){return currentData.bufferString4;}
    public static int getBufferInt4(){return currentData.bufferInt4;}
    public static int getFlag(){return  currentData.flag;}

    //清空所有缓存方法
    public static void clearAllBuffer(){
        currentData.bufferString1=null;
        currentData.bufferString2=null;
        currentData.bufferString3=null;
        currentData.bufferString4=null;
        currentData.bufferInt1=-9999;
        currentData.bufferInt2=-9999;
        currentData.bufferInt3=-9999;
        currentData.bufferInt4=-9999;
        currentData.flag=-9999;
    }

}
