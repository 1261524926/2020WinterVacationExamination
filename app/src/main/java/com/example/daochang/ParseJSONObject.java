package com.example.daochang;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParseJSONObject {

    static public String parseIs(String string1,String string2){
        try {
            String data;
            JSONObject jsonObject1=new JSONObject(string1);
            data=jsonObject1.getString(string2);
            return data;
        }catch (Exception e){
            System.out.println("JSONError2");
            return "JSONError2";
        }
    }

    static public int parseIi(String string1,String string2){
        try {
            int data;
            JSONObject jsonObject1=new JSONObject(string1);
            data=Integer.valueOf(jsonObject1.getString(string2));
            return data;
        }catch (Exception e){
            System.out.println("JSONError2");
            return -1;
        }
    }

    static public String parseIIs(String string1,String string2,String string3){
        try {
            String data;
            JSONObject jsonObject1=new JSONObject(string1);
            JSONObject jsonObject2=jsonObject1.getJSONObject(string2);
            data=jsonObject2.getString(string3);
            return data;
        }catch (Exception e){
            System.out.println("JSONError3");
            return "JSONError3";
        }
    }

    static public int parseIIi(String string1,String string2,String string3){
        try {
            int data;
            JSONObject jsonObject1=new JSONObject(string1);
            JSONObject jsonObject2=jsonObject1.getJSONObject(string2);
            data=Integer.valueOf(jsonObject2.getString(string3));
            return data;
        }catch (Exception e){
            System.out.println("JSONError3");
            return -1;
        }
    }

    static public String parseIIIs(String string1,String string2,String string3,String string4){
        try {
            String data;
            JSONObject jsonObject1=new JSONObject(string1);
            JSONObject jsonObject2=jsonObject1.getJSONObject(string2);
            JSONObject jsonObject3=jsonObject2.getJSONObject(string3);
            data=jsonObject3.getString(string4);
            return data;
        }catch (Exception e){
            System.out.println("JSONError4");
            return "JSONError4";
        }
    }

    static public int parseIIIi(String string1,String string2,String string3,String string4){
        try {
            int data;
            JSONObject jsonObject1=new JSONObject(string1);
            JSONObject jsonObject2=jsonObject1.getJSONObject(string2);
            JSONObject jsonObject3=jsonObject2.getJSONObject(string3);
            data=Integer.valueOf(jsonObject3.getString(string4));
            return data;
        }catch (Exception e){
            System.out.println("JSONError4");
            return -1;
        }
    }

    static public String parseQuestionS(int i,String string){
        try {
            return new JSONArray(PostData.getQuestions()).getJSONObject(i).getString(string);
        }catch (Exception e){
            return "JSONError6";
        }
    }

    static public int parseQuestionI(int i,String string){
        try {
            return Integer.valueOf(new JSONArray(PostData.getQuestions()).getJSONObject(i).getString(string));
        }catch (Exception e){
            return -9999;
        }
    }

    static public Boolean parseQuestionB(int i,String string){
        try {
            return Boolean.valueOf(new JSONArray(PostData.getQuestions()).getJSONObject(i).getString(string));
        }catch (Exception e){
            return false;
        }
    }


}
