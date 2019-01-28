package com.example.android.paint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.io.ByteArrayOutputStream;


public class MainActivity extends AppCompatActivity {


    public PaintView paintView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** Criando a PaintView (Tela de desenho) **/

        paintView = (PaintView) findViewById((R.id.paintView));
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);

    }


    /**
     * shareDrawing cria um bitMap do desenho da tela, possibilitando a exportação para outras activities ou fragments
     **/

    public void shareDrawing() {

        paintView.setDrawingCacheEnabled(true);
        paintView.invalidate();

        Bitmap b = paintView.getDrawingCache();

        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        byte[] byteArray = bStream.toByteArray();


        Intent i = new Intent(this, Main2Activity.class);
        i.putExtra("image", byteArray);
        startActivity(i);

    }

    /**
     * Menu de seleção de opções
     **/
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.normal:
                paintView.normal();
                return true;

            case R.id.emboss:
                paintView.emboss();
                return true;

            case R.id.blur:
                paintView.blur();
                return true;

            case R.id.clear:
                paintView.clear();
                return true;

            case R.id.end:
                shareDrawing();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
