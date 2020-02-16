package com.example.daochang;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import java.util.Timer;
import java.util.TimerTask;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化数据
        PageData.initPageData();
        CurrentData.setFlag(-9999);
        initUserData();
        while(true){
            if(CurrentData.getFlag()==9998){
                initPostData();
                break;
            }
        }

        //跳转页面
        Timer timer=new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                CurrentData.setFlag(9996);
                Intent intent=new Intent(MainActivity.this, MainWindows.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        };
        timer.schedule(task,2000);

    }


    //设置沉浸式样式
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus && Build.VERSION.SDK_INT>=19){
            View decorView=getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE|View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    }


    //初始化用户数据
    public void initUserData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient=new OkHttpClient();
                    RequestBody requestBody_load=new FormBody.Builder().add("username","visitor").add("password","123456").build();
                    Request request_load=new Request.Builder().url("http://bihu.jay86.com/login.php").post(requestBody_load).build();
                    Response response_load=okHttpClient.newCall(request_load).execute();
                    String data_load=response_load.body().string();
                    CurrentData.setBufferString1(data_load);

                    CurrentData.setStatus(1);
                    CurrentData.setID(ParseJSONObject.parseIIi(CurrentData.getBufferString1(),"data","id"));
                    CurrentData.setUserName(ParseJSONObject.parseIIs(CurrentData.getBufferString1(),"data","username"));
                    CurrentData.setAvatar(ParseJSONObject.parseIIs(CurrentData.getBufferString1(),"data","avatar"));
                    CurrentData.setToken(ParseJSONObject.parseIIs(CurrentData.getBufferString1(),"data","token"));
                    CurrentData.setFlag(9998);
                }catch (Exception e){
                    System.out.println("JSONError1");
                    CurrentData.setFlag(9998);
                }
            }
        }).start();
    }


    //初始化社区数据
    public void initPostData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient=new OkHttpClient();
                    RequestBody requestBody_getQuestionList=new FormBody.Builder().add("page","1").add("count","20").add("token",CurrentData.getToken()).build();
                    Request request_getQuestionList=new Request.Builder().url("http://bihu.jay86.com/getQuestionList.php").post(requestBody_getQuestionList).build();
                    Response response_getQuestionList=okHttpClient.newCall(request_getQuestionList).execute();
                    String data_geQuestionList=response_getQuestionList.body().string();
                    PostData.setInformation(data_geQuestionList);

                    PostData.setReturnStatus(ParseJSONObject.parseIi(PostData.getPostData().information,"status"));
                    PostData.setTotalCount(ParseJSONObject.parseIIi(PostData.getPostData().information,"data","totalCount"));
                    PostData.setTotalPage(ParseJSONObject.parseIIi(PostData.getPostData().information,"data","totalPage"));
                    PostData.setQuestions(ParseJSONObject.parseIIs(PostData.getPostData().information,"data","questions"));
                    PostData.setCurrentLoadPage(1);
                    CurrentData.setFlag(-9999);
                }catch (Exception e){
                    System.out.println("JSONError5");
                    CurrentData.setFlag(-9999);

                }
            }
        }).start();
    }
}
