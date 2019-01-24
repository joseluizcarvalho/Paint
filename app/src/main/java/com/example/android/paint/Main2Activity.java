package com.example.android.paint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;

public class Main2Activity extends AppCompatActivity {



    public ImageView paintView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        /**
         *
          paintView2 = (ImageView) findViewById(R.id.paintView2);
         String path2;
         if (savedInstanceState == null) {
         Bundle extras = getIntent().getExtras();
         if(extras == null) {
         path2= null;
         } else {
         path2= extras.getString("path2");
         }
         } else {
         path2= (String) savedInstanceState.getSerializable("path2");
         }

         File sd = Environment.getExternalStorageDirectory();
         File image = new File(sd+path2, "imageName");
         BitmapFactory.Options bmOptions = new BitmapFactory.Options();
         Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);
         paintView2.setImageBitmap(bitmap);

         */

        Bitmap bmp;

        byte[] byteArray = getIntent().getByteArrayExtra("image");
        bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);


        paintView2 = (ImageView) findViewById(R.id.paintView2);
        paintView2.setImageBitmap(bmp);




    }
}