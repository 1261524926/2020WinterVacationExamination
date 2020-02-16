package com.example.daochang;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import java.util.ArrayList;
import java.util.List;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Community_publicPost_Fragment extends Fragment {
    List<Post> postList=new ArrayList<>();
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    PostAdapter postAdapter;
    DividerItemDecoration decoration;


    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        View view=layoutInflater.inflate(R.layout.community_publicpost,container,false);


        initSystemData();


        recyclerView=(RecyclerView)view.findViewById(R.id.community_publicPost_recyclerView);
        linearLayoutManager=new LinearLayoutManager(getContext());//设置布局样式
        recyclerView.setLayoutManager(linearLayoutManager);
        postAdapter=new PostAdapter(getContext(),postList);
        recyclerView.setAdapter(postAdapter);
        //recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));//设置默认分割线
        decoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        decoration.setDrawable(getResources().getDrawable(R.drawable.divider,null));
        recyclerView.addItemDecoration(decoration);//自定义分割线，对应drawable文件夹下的divider.xml文件


        final SwipeRefreshLayout swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.community_publicPost_refreshLayout);
        swipeRefreshLayout.setColorSchemeColors(Color.YELLOW,Color.BLUE,Color.RED,Color.GREEN);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                PostData.setFlag(9999);
                loadNewPost();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },500);
            }
        });


        //上拉加载，不通过SwipeRefreshLayout，没有动画。
        recyclerView.addOnScrollListener(new onLoadMoreListener() {
            @Override
            protected void onLoading(int countItem, int lastItem) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        PostData.setFlag(9999);
                        loadNewPost();
                    }
                },100);
            }
        });
        return view;
    }



    public void initSystemData(){
        PostData.setCurrentLoadPage(0);
        addPostList(0,"欢迎来到社区",AutomaticData.getAvatar(),"System",0,0,"系统提示","",BaseToolFunction.getTime(),0,BaseToolFunction.getTime(),1,false,false,false);
        if(CurrentData.getStatus()!=1){
            addPostList(0,"您当前处于未登录状态，所有功能均关闭，请尽快登陆。",AutomaticData.getAvatar(),"System",0,0,"系统提示","",BaseToolFunction.getTime(),0,BaseToolFunction.getTime(),1,false,false,false);
        }else if(CurrentData.getStatus()==1&&CurrentData.getUserName().equals("visitor")){
            addPostList(0,"您当前处于游客状态，请尽快登陆。",AutomaticData.getAvatar(),"System",0,0,"系统提示","",BaseToolFunction.getTime(),0,BaseToolFunction.getTime(),1,false,false,false);
        }else{
            addPostList(0,"当前用户信息为：\n\n"+"ID:  "+CurrentData.getCurrentData().id+"\n\n"+"UserName:  "+CurrentData.getCurrentData().userName+"\n\n"+"Avatar:  "+CurrentData.getCurrentData().avatar+"\n\n"+"Token:  "+CurrentData.getCurrentData().token,AutomaticData.getAvatar(),"System",0,0,"系统提示","",BaseToolFunction.getTime(),0,BaseToolFunction.getTime(),1,false,false,false);
        }
        for(int i=0;i<20;i++){
            addPostListWithQuestion(i);
        }
    }


    public void addPostListWithQuestion(int index){
        addPostList(ParseJSONObject.parseQuestionI(index,"authorId"),ParseJSONObject.parseQuestionS(index,"content"),ParseJSONObject.parseQuestionS(index,"authorAvatar"),ParseJSONObject.parseQuestionS(index,"authorName"),ParseJSONObject.parseQuestionI(index,"exciting"),ParseJSONObject.parseQuestionI(index,"naive"),ParseJSONObject.parseQuestionS(index,"title"),"",ParseJSONObject.parseQuestionS(index,"date"),ParseJSONObject.parseQuestionI(index,"id"),ParseJSONObject.parseQuestionS(index,"recent"),ParseJSONObject.parseQuestionI(index,"answerCount"),ParseJSONObject.parseQuestionB(index,"is_exciting"),ParseJSONObject.parseQuestionB(index,"is_naive"),ParseJSONObject.parseQuestionB(index,"is_favorite"));
    }


    public void addPostList(final int id,final String content,final String portrait,final String name,final int praise,final int discouragement,final String title,final String images,final String publishDate,final int postId,final String recentAnswerDate,final int answerCount,final boolean is_praise,final boolean is_discouragement,final boolean is_favorite){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                postList.add(new Post(id,content,portrait,name,praise,discouragement,title,images,publishDate,postId,recentAnswerDate,answerCount,is_praise,is_discouragement,is_favorite));
            }
        });
    }



    public void loadNewPost(){

        Thread loadNewPost_Thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    OkHttpClient okHttpClient=new OkHttpClient();
                    RequestBody requestBody_getQuestionList=new FormBody.Builder().add("page",String.valueOf(PostData.getCurrentLoadPage()+1)).add("count","20").add("token",CurrentData.getToken()).build();
                    Request request_getQuestionList=new Request.Builder().url("http://bihu.jay86.com/getQuestionList.php").post(requestBody_getQuestionList).build();
                    Response response_getQuestionList=okHttpClient.newCall(request_getQuestionList).execute();
                    final String data_geQuestionList=response_getQuestionList.body().string();
                    PostData.setInformation(data_geQuestionList);


                    PostData.setReturnStatus(ParseJSONObject.parseIi(PostData.getPostData().information,"status"));
                    PostData.setTotalCount(ParseJSONObject.parseIIi(PostData.getPostData().information,"data","totalCount"));
                    PostData.setTotalPage(ParseJSONObject.parseIIi(PostData.getPostData().information,"data","totalPage"));
                    PostData.setQuestions(ParseJSONObject.parseIIs(PostData.getPostData().information,"data","questions"));
                    PostData.setCurrentLoadPage(PostData.getCurrentLoadPage()+1);
                    PostData.setFlag(9998);
                }catch (Exception e){
                    System.out.println("JSONError9");
                }
            }
        });

        if(PostData.getCurrentLoadPage()<PostData.getTotalPage()&&PostData.getFlag()==9999) {
            loadNewPost_Thread.start();
            while (true){
                if(PostData.getFlag()==9998){
                    for (int i = 0; i < 20; i++) {
                        addPostListWithQuestion(i);
                    }
                    PostData.setFlag(9997);
                    break;
                }
            }

            while (true) {
                if (PostData.getFlag() == 9997) {
                    postAdapter.notifyDataSetChanged();
                    PostData.setFlag(-9999);
                    break;
                }
            }
        }else {
            addPostList(0,"已经到底了0.0",AutomaticData.getAvatar(),"System",0,0,"系统提示","",BaseToolFunction.getTime(),0,BaseToolFunction.getTime(),1,false,false,false);
            postAdapter.notifyDataSetChanged();
            PostData.setFlag(-9999);
        }
    }
}
