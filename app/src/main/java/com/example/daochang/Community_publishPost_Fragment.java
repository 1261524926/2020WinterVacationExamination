package com.example.daochang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Community_publishPost_Fragment extends Fragment {
    TextView title_textView;
    TextView content_textView;
    Button send_Button;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        View view=layoutInflater.inflate(R.layout.community_publishpost,container,false);

        title_textView=(TextView)view.findViewById(R.id.publishPost_title_PlainText);
        content_textView=(TextView)view.findViewById(R.id.publishPost_content_PlainText);
        send_Button=(Button)view.findViewById(R.id.publishPost_send_Button);
        send_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publish_Post(title_textView.getText().toString(),content_textView.getText().toString(),"",CurrentData.getToken());
            }
        });
        return view;
    }

    public void publish_Post(final String title,final String  content,final String images,final String token){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient okHttpClient=new OkHttpClient();
                    RequestBody requestBody=new FormBody.Builder().add("title",title).add("content",content).add("images",images).add("token",token).build();
                    Request request=new Request.Builder().url("http://bihu.jay86.com/question.php").post(requestBody).build();
                    Response response=okHttpClient.newCall(request).execute();
                    String data=response.body().string();

                    PostData.setStatus(ParseJSONObject.parseIi(data,"status"));
                    if(PostData.getStatus()==200){
                        clear();
                        runSendFinished("发送成功");
                    }else{
                        runSendFinished("发送失败");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    runSendFinished("发送失败");
                }
            }
        }).start();
    }

    public void runSendFinished(final String string){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(),string,Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void clear(){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                title_textView.setText("");
                content_textView.setText("");
            }
        });
    }
}
