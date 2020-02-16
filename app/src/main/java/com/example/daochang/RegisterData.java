package com.example.daochang;

public class RegisterData {


    String information;
    int status;
    int id;
    String username;
    String password;
    String avatar;
    String token;
    int flag=-9999;


    public static RegisterData registerData=new RegisterData();
    private RegisterData(){

    }



    public static RegisterData getRegisterData(){
        return registerData;
    }
    public static void setInformation(String information){
        registerData.information=information;
    }
    public static void setStatus(int status){
        registerData.status=status;
    }
    public static void setId(int id){
        registerData.id=id;
    }
    public static void setUsername(String username){
        registerData.username=username;
    }
    public static void setPassword(String password){
        registerData.password=password;
    }
    public static void setAvatar(String avatar){
        registerData.avatar=avatar;
    }
    public static void setToken(String token){
        registerData.token=token;
    }
    public static void setFlag(int flag){registerData.flag=flag;}



    public static String getInformation(){
        return registerData.information;
    }
    public static int getStatus(){
        return registerData.status;
    }
    public static int getId(){
        return registerData.id;
    }
    public static String getUsername(){
        return registerData.username;
    }
    public static String getPassword(){
        return registerData.password;
    }
    public static String getAvatar(){
        return registerData.avatar;
    }
    public static String getToken(){
        return registerData.token;
    }
    public static int getFlag(){return registerData.flag;}

}
