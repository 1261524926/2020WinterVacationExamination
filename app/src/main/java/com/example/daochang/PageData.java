package com.example.daochang;

public class PageData {

    Account_LoadAccount account_loadAccount;
    Account_MyAccount account_myAccount;
    Account_NewAccount account_newAccount;
    Community_historyPost_Fragment community_historyPost_fragment;
    Community_publicPost_Fragment community_publicPost_fragment;
    Community_publishPost_Fragment community_publishPost_fragment;
    Weather_List_Fragment weather_list_fragment;
    Weather_Seek_Fragment weather_seek_fragment;


    public static PageData pageData=new PageData();
    private PageData(){

    }


    public static PageData getPageData(){return pageData;}
    public static void initPageData(){
        pageData.account_loadAccount=new Account_LoadAccount();
        pageData.account_myAccount=new Account_MyAccount();
        pageData.account_newAccount=new Account_NewAccount();
        pageData.community_historyPost_fragment=new Community_historyPost_Fragment();
        pageData.community_publicPost_fragment=new Community_publicPost_Fragment();
        pageData.community_publishPost_fragment=new Community_publishPost_Fragment();
        pageData.weather_list_fragment=new Weather_List_Fragment();
        pageData.weather_seek_fragment=new Weather_Seek_Fragment();
    }
}
