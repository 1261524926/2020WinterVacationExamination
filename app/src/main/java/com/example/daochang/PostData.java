package com.example.daochang;

public class PostData {
    String information;
    int returnStatus;
    int totalCount;
    int totalPage;
    int currentLoadPage;
    String questions;
    int flag=-9999;
    int status;
    String avatar;



    public static PostData postData=new PostData();
    private PostData(){
    }

    public static PostData getPostData(){
        return postData;
    }
    public static void setInformation(String information){
        postData.information=information;
    }
    public static void setReturnStatus(int returnStatus){
        postData.returnStatus=returnStatus;
    }
    public static void setTotalCount(int totalCount){
        postData.totalCount=totalCount;
    }
    public static void setTotalPage(int totalPage){
        postData.totalPage=totalPage;
    }
    public static void setQuestions(String questions){
        postData.questions=questions;
    }
    public static void setCurrentLoadPage(int currentLoadPage){postData.currentLoadPage=currentLoadPage;}
    public static void setFlag(int flag){postData.flag=flag;}
    public static void setStatus(int status){postData.status=status;}
    public static void setAvatar(String avatar){postData.avatar=avatar;}


    public static String getInformation(){
        return postData.information;
    }
    public static int getReturnStatus(){
        return postData.returnStatus;
    }
    public static int getTotalCount(){
        return postData.totalCount;
    }
    public static int getTotalPage(){
        return postData.totalPage;
    }
    public static String getQuestions(){
        return postData.questions;
    }
    public static int getCurrentLoadPage(){return postData.currentLoadPage;}
    public static int getFlag(){return postData.flag;}
    public static int getStatus(){return postData.status;}
    public static String getAvatar(){return postData.avatar;}

}
