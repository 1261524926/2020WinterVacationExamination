package com.example.daochang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import java.util.Random;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class Account_LoadAccount extends Fragment {

    TextView username_textView;
    TextView password_textView;
    TextView verification_input_textView;
    TextView verification_show_textView;
    TextView tips1_textView;
    Button load_button;
    Button getVerificationCode_button;
    Random random;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        View view=layoutInflater.inflate(R.layout.account_loadaccount,container,false);

        username_textView=(TextView)view.findViewById(R.id.loadAccount_username_textView);
        password_textView=(TextView)view.findViewById(R.id.loadAccount_password_textView);
        verification_input_textView=(TextView)view.findViewById(R.id.loadAccount_verificationCode_input_textView);
        verification_show_textView=(TextView)view.findViewById(R.id.loadAccount_verificationCode_show_textView);
        tips1_textView=(TextView)view.findViewById(R.id.loadAccount_tips1_textView);
        load_button=(Button)view.findViewById(R.id.account_loadAccount_load_Button);
        getVerificationCode_button=(Button)view.findViewById(R.id.account_loadAccount_getVerificationCode_Button);
        random=new Random();

        getVerificationCode_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verification_show_textView.setText(String.valueOf(Math.abs(random.nextInt(900000)+100000)));
            }
        });

        load_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username_textView.getText().toString().equals("")){
                    tips1_textView.setText("用户名不能为空");
                }else if(password_textView.getText().toString().equals("")){
                    tips1_textView.setText("密码不能为空");
                }else if(verification_input_textView.getText().toString().equals("")){
                    tips1_textView.setText("验证码不能为空");
                }else if(!verification_input_textView.getText().toString().equals(verification_show_textView.getText().toString())){
                    tips1_textView.setText("验证码输入错误");
                }else{
                    CurrentData.setFlag(9999);
                    sendLoad(username_textView.getText().toString(),password_textView.getText().toString());
                    while (true){
                        if(CurrentData.getFlag()==9998){
                            if(ParseJSONObject.parseIi(CurrentData.getBufferString1(),"status")==200){
                                runLoadRight();
                                CurrentData.setFlag(-9999);
                                break;
                            }else{
                                runLoadError();
                                CurrentData.setFlag(-9999);
                            }
                        }
                    }

                }
            }
        });
        return view;
    }

    public void sendLoad(final String username,final String password){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient=new OkHttpClient();
                    RequestBody requestBody=new FormBody.Builder().add("username",username).add("password",password).build();
                    Request request=new Request.Builder().url("http://bihu.jay86.com/login.php").post(requestBody).build();
                    Response response=okHttpClient.newCall(request).execute();
                    String data=response.body().string();
                    CurrentData.setBufferString1(data);
                    CurrentData.setFlag(9998);
                }catch (Exception e){
                    System.out.println("JSONError8");
                }
            }
        }).start();
    }

    public void runLoadRight(){
        CurrentData.setStatus(1);
        CurrentData.setID(ParseJSONObject.parseIIi(CurrentData.getBufferString1(),"data","id"));
        CurrentData.setUserName(ParseJSONObject.parseIIs(CurrentData.getBufferString1(),"data","username"));
        CurrentData.setAvatar(ParseJSONObject.parseIIs(CurrentData.getBufferString1(),"data","avatar"));
        CurrentData.setToken(ParseJSONObject.parseIIs(CurrentData.getBufferString1(),"data","token"));
        PageData.getPageData().community_publicPost_fragment=new Community_publicPost_Fragment();
        load_button.setText("登陆成功");
        load_button.setEnabled(false);
        tips1_textView.setText("欢迎回来,亲爱的 "+CurrentData.getUserName());
    }

    public void runLoadError(){
        tips1_textView.setText("登录失败，请检查用户名和密码是否正确，或请检查网络");
    }

}
