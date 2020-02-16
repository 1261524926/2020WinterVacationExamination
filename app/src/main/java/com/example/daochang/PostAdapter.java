package com.example.daochang;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    //提供数据源
    private List<Post> postListist;
    Context context;

    PostAdapter(Context context,List<Post> postListist){
        this.context=context;
        this.postListist=postListist;
    }


    //填充视图
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView portrait;
        TextView name_id_text_view;
        TextView publishDate_text_view;
        TextView postId_text_view;
        TextView title_text_view;
        TextView content_text_view;
        TextView answerAccount_text_view;
        TextView praise_text_view;
        TextView discouragement_text_view;
        ImageButton praise_button;
        ImageButton discouragement_button;
        ImageButton comment_button;

        public ViewHolder(View view){
            super(view);
            portrait=(ImageView)view.findViewById(R.id.portrait);
            name_id_text_view=(TextView)view.findViewById(R.id.name_id_text_view);
            publishDate_text_view=(TextView)view.findViewById(R.id.publishDate_text_view);
            postId_text_view=(TextView)view.findViewById(R.id.postId_text_view);
            title_text_view=(TextView)view.findViewById(R.id.title_text_view);
            content_text_view=(TextView)view.findViewById(R.id.content_text_view);
            answerAccount_text_view=(TextView)view.findViewById(R.id.answerAccount_text_view);
            praise_text_view=(TextView)view.findViewById(R.id.praise_text_view);
            discouragement_text_view=(TextView)view.findViewById(R.id.discouragement_text_view);
            praise_button=(ImageButton)view.findViewById(R.id.praise_button);
            discouragement_button=(ImageButton)view.findViewById(R.id.discouragement_button);
            comment_button=(ImageButton)view.findViewById(R.id.comment_button);

        }

    }


    public void onBindViewHolder(final ViewHolder viewHolder, final int position){
        final Post post=postListist.get(position);
/*
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        try {
            Bitmap bitmap = getBitmap(post.getPortrait());
            viewHolder.portrait.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        Glide.with(context).load(post.getPortrait()).placeholder(R.drawable.originalportrait2).into(viewHolder.portrait);
        viewHolder.name_id_text_view.setText(post.getName()+"       \nID:"+String.valueOf(post.getID()));
        viewHolder.publishDate_text_view.setText(post.getPublishDate());
        viewHolder.postId_text_view.setText(String.valueOf(post.getPostId()));
        viewHolder.title_text_view.setText(post.getTitle());
        viewHolder.content_text_view.setText(post.getContent());
        viewHolder.answerAccount_text_view.setText(String.valueOf(post.getAnswerCount()));
        viewHolder.praise_text_view.setText(String.valueOf(post.getPraise()));
        viewHolder.discouragement_text_view.setText(String.valueOf(post.getDiscouragement()));


        if(post.isIs_praise()==true){
            viewHolder.praise_button.setBackgroundResource(R.drawable.community_praise_no);
        }else{
            viewHolder.praise_button.setBackgroundResource(R.drawable.community_praise_ok);
        }
        if(post.isIs_discouragement()==true){
            viewHolder.discouragement_button.setBackgroundResource(R.drawable.community_discouragement_no);
        }else{
            viewHolder.discouragement_button.setBackgroundResource(R.drawable.community_discouragement_ok);
        }
        viewHolder.comment_button.setEnabled(true);
        viewHolder.comment_button.setBackgroundResource(R.drawable.community_comment_ok);
        viewHolder.praise_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(post.isIs_praise()==false){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                OkHttpClient okHttpClient=new OkHttpClient();
                                RequestBody requestBody=new FormBody.Builder().add("id",String.valueOf(post.getPostId())).add("type","1").add("token",CurrentData.getToken()).build();
                                Request request=new Request.Builder().url("http://bihu.jay86.com/exciting.php").post(requestBody).build();
                                Response response=okHttpClient.newCall(request).execute();
                                String data=response.body().string();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    post.setIs_praise(true);
                    post.setPraise(post.getPraise()+1);
                    viewHolder.praise_button.setBackgroundResource(R.drawable.community_praise_no);
                    PostAdapter.super.notifyDataSetChanged();
                }else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                OkHttpClient okHttpClient=new OkHttpClient();
                                RequestBody requestBody=new FormBody.Builder().add("id",String.valueOf(post.getPostId())).add("type","1").add("token",CurrentData.getToken()).build();
                                Request request=new Request.Builder().url("http://bihu.jay86.com/cancelExciting.php").post(requestBody).build();
                                Response response=okHttpClient.newCall(request).execute();
                                String data=response.body().string();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    post.setIs_praise(false);
                    post.setPraise(post.getPraise()-1);
                    viewHolder.praise_button.setBackgroundResource(R.drawable.community_praise_ok);
                    PostAdapter.super.notifyDataSetChanged();
                }


            }
        });
        viewHolder.discouragement_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(post.isIs_discouragement()==false){

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                OkHttpClient okHttpClient=new OkHttpClient();
                                RequestBody requestBody=new FormBody.Builder().add("id",String.valueOf(post.getPostId())).add("type","1").add("token",CurrentData.getToken()).build();
                                Request request=new Request.Builder().url("http://bihu.jay86.com/naive.php").post(requestBody).build();
                                Response response=okHttpClient.newCall(request).execute();
                                String data=response.body().string();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    post.setIs_discouragement(true);
                    post.setDiscouragement(post.getDiscouragement()+1);
                    viewHolder.discouragement_button.setBackgroundResource(R.drawable.community_discouragement_no);
                    PostAdapter.super.notifyDataSetChanged();

                }else{

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                OkHttpClient okHttpClient=new OkHttpClient();
                                RequestBody requestBody=new FormBody.Builder().add("id",String.valueOf(post.getPostId())).add("type","1").add("token",CurrentData.getToken()).build();
                                Request request=new Request.Builder().url("http://bihu.jay86.com/cancelNaive.php").post(requestBody).build();
                                Response response=okHttpClient.newCall(request).execute();
                                String data=response.body().string();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }).start();
                    post.setIs_discouragement(false);
                    post.setDiscouragement(post.getDiscouragement()-1);
                    viewHolder.discouragement_button.setBackgroundResource(R.drawable.community_discouragement_ok);
                    PostAdapter.super.notifyDataSetChanged();
                }

            }

        });

    }

    //创建ViewHolder
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.post,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }


    //返回项目个数
    public int getItemCount(){
        return postListist.size();
    }

/*
    public Bitmap getBitmap(String path) throws IOException {
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                Bitmap resultBitmap=Bitmap.createBitmap(bitmap,0,0,BaseToolFunction.Min(bitmap.getHeight(),bitmap.getWidth()),BaseToolFunction.Min(bitmap.getHeight(),bitmap.getWidth()));
                return resultBitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
*/

}
