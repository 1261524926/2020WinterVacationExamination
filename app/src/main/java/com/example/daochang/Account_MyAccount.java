package com.example.daochang;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import java.io.File;


public class Account_MyAccount extends Fragment {
    public static final int TAKE_PHOTO=1;
    Uri imageUri;
    TextView userName;
    TextView id;
    TextView token;
    ImageView portrait;
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        View view=layoutInflater.inflate(R.layout.account_myaccount,container,false);

        userName=(TextView)view.findViewById(R.id.myAccount_userName_textView);
        id=(TextView)view.findViewById(R.id.myAccount_Id_textView);
        token=(TextView)view.findViewById(R.id.myAccount_token_textView);
        portrait=(ImageView) view.findViewById(R.id.myAccount_portrait_imageView);

        userName.setText("用户名    "+CurrentData.getUserName());
        id.setText("ID    "+String.valueOf(CurrentData.getID()));
        token.setText("认证码    \n\n"+CurrentData.getToken());
        Glide.with(getContext()).load(AutomaticData.getAvatar()).into(portrait);


        portrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setPositiveButton("相册选择", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_PICK, null);
                        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(intent,0);
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("拍照获取", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        File outputImage=new File(getContext().getExternalCacheDir(),"output_image.jpg");
                        try {
                            if(outputImage.exists()){
                                outputImage.delete();
                            }
                            outputImage.createNewFile();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        if(Build.VERSION.SDK_INT>=24){
                            imageUri= FileProvider.getUriForFile(getActivity(),"com.example.daochang.fileprovider",outputImage);
                        }else {
                            imageUri=Uri.fromFile(outputImage);
                        }
                        Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                        startActivityForResult(intent,TAKE_PHOTO);
                        dialog.dismiss();
                    }
                });

                builder.setNeutralButton("取消修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "取消修改", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog=builder.create();

                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                    }
                });

                alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                    }
                });
                alertDialog.show();
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.rgb(0,0,0));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.rgb(0,0,0));
                alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.rgb(0,0,0));


            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode){
            case TAKE_PHOTO:
                if(resultCode==getActivity().RESULT_OK){
                    try {
                        Bitmap bitmap= BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageUri));
                        portrait.setImageBitmap(bitmap);

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }


}
