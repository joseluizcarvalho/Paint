package com.example.android.paint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


public class MainActivity extends AppCompatActivity {







    public PaintView paintView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        paintView = (PaintView) findViewById((R.id.paintView));
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);


    }

    public void click (View view){

    }

    public void shareDrawing(){
        paintView.setDrawingCacheEnabled(true);
        paintView.invalidate();
        String path = Environment.getExternalStorageDirectory().toString();
        OutputStream fOut = null;
        File file = new File(path,
                "android_drawing_app.png");
        file.getParentFile().mkdirs();

        try {
            file.createNewFile();
        } catch (Exception e) {
            Log.e("LOG_CAT", e.getCause() + e.getMessage());
        }

        try {
            fOut = new FileOutputStream(file);
        } catch (Exception e) {
            Log.e("LOG_CAT", e.getCause() + e.getMessage());
        }

        if (paintView.getDrawingCache() == null) {
            Log.e("LOG_CAT","Unable to get drawing cache ");
        }

        paintView.getDrawingCache()
                .compress(Bitmap.CompressFormat.JPEG, 85, fOut);

        try {
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            Log.e("LOG_CAT", e.getCause() + e.getMessage());
        }

        /*Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(file));
        shareIntent.setType("image/png");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(shareIntent, "Share image"));*/
        Bitmap b = paintView.getDrawingCache();

        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        byte[] byteArray = bStream.toByteArray();


        Intent i = new Intent(this, Main2Activity.class);
        i.putExtra("image", byteArray);
        startActivity(i);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.normal :
                paintView.normal();
                return true;

            case R.id.emboss :
                paintView.emboss();
                return true;

            case R.id.blur :
                paintView.blur();
                return true;

            case R.id.clear :
                paintView.clear();
                return true;

            case R.id.end :
                shareDrawing();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
