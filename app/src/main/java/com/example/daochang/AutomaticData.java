package com.example.daochang;

public class AutomaticData {
    String avatar="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1581760655411&di=421c9b8eaadbc2815714166b66a3f64a&imgtype=0&src=http%3A%2F%2Fwww.17qq.com%2Fimg_qqtouxiang%2F18800168.jpeg";

    public static AutomaticData automaticData=new AutomaticData();
    private AutomaticData(){

    }

    public static void setAvatar(String url){
        automaticData.avatar=url;
    }

    public static String getAvatar(){
        return automaticData.avatar;
    }
}
