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

public class Account_NewAccount extends Fragment {

    Random rand;
    TextView username_textView;
    TextView password_textView;
    TextView verificationCode_input_textView;
    TextView verificationCode_show_textView;
    Button register_button;
    Button getVerificationCode_button;
    TextView tips1_textView;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        View view=layoutInflater.inflate(R.layout.account_newaccount,container,false);

        username_textView=(TextView)view.findViewById(R.id.newAccount_userName_textView);
        password_textView=(TextView)view.findViewById(R.id.newAccount_password_textView);
        verificationCode_input_textView=(TextView)view.findViewById(R.id.newAccount_verificationCode_input_textView);
        verificationCode_show_textView=(TextView)view.findViewById(R.id.newAccount_verificationCode_show_textView);
        getVerificationCode_button=(Button)view.findViewById(R.id.account_newAccount_getVerificationCode_Button);
        register_button=(Button)view.findViewById(R.id.account_newAccount_register_Button);
        tips1_textView=(TextView)view.findViewById(R.id.newAccount_tips1_textView);


        rand=new Random();
        getVerificationCode_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificationCode_show_textView.setText(String.valueOf(Math.abs(rand.nextInt(900000)+100000)));
            }
        });


        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                        if(username_textView.getText().toString().equals("")){
                            tips1_textView.setText("用户名不能为空");
                        }else if(password_textView.getText().toString().equals("")){
                            tips1_textView.setText("密码不能为空");
                        }else if(verificationCode_input_textView.getText().toString().equals("")){
                            tips1_textView.setText("验证码不能为空");
                        }else if(!verificationCode_input_textView.getText().toString().equals(verificationCode_show_textView.getText().toString())){
                            tips1_textView.setText("验证码输入错误");
                        }else{
                              RegisterData.setFlag(9999);
                              sendRegister(username_textView.getText().toString(),password_textView.getText().toString());
                              while(true){
                                  if(RegisterData.getFlag()==9998){
                                      if(RegisterData.getStatus()==200){
                                          runRegisterRight();
                                          RegisterData.setStatus(-9999);
                                          RegisterData.setFlag(-9999);
                                          break;
                                      }else{
                                          runRegisterError();
                                          RegisterData.setFlag(-9999);
                                          break;
                                      }
                                  }
                              }


                        }

            }
        });

        return view;
    }


    public void sendRegister(final String username, final String password){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient=new OkHttpClient();
                    RequestBody requestBody=new FormBody.Builder().add("username",username).add("password",password).build();
                    Request request=new Request.Builder().url("http://bihu.jay86.com/register.php").post(requestBody).build();
                    Response response=okHttpClient.newCall(request).execute();
                    String data=response.body().string();
                    RegisterData.setInformation(data);

                    RegisterData.setStatus(Integer.valueOf(ParseJSONObject.parseIi(RegisterData.getInformation(),"status")));
                    RegisterData.setId(Integer.valueOf(ParseJSONObject.parseIIi(RegisterData.getInformation(),"data","id")));
                    RegisterData.setUsername(ParseJSONObject.parseIIs(RegisterData.getInformation(),"data","username"));
                    RegisterData.setPassword(ParseJSONObject.parseIIs(RegisterData.getInformation(),"data","password"));
                    RegisterData.setAvatar(ParseJSONObject.parseIIs(RegisterData.getInformation(),"data","avatar"));
                    RegisterData.setToken(ParseJSONObject.parseIIs(RegisterData.getInformation(),"data","token"));
                    RegisterData.setFlag(9998);
                }catch (Exception e){
                    System.out.println("JSONError7");
                }
            }
        }).start();

    }



    public void runRegisterRight(){
        register_button.setEnabled(false);
        register_button.setText("注册成功");
        tips1_textView.setText("请牢记您的信息：\n\n"+"用户名:  "+RegisterData.getUsername()+"\nID:  "+RegisterData.getId()+"\n密码:  "+RegisterData.getPassword());
        username_textView.setText("");
        password_textView.setText("");
        verificationCode_input_textView.setText("");
        verificationCode_show_textView.setText(String.valueOf(Math.abs(rand.nextInt(900000)+100000)));
    }


    public void runRegisterError(){
        register_button.setEnabled(true);
        tips1_textView.setText("注册失败，请检查该用户名是否已被注册或请检查您的网络。");
    }


}
