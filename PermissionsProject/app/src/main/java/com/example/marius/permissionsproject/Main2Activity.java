package com.example.marius.permissionsproject;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView = findViewById(R.id.imageView);

        String val = getIntent().getStringExtra("300");
        if (val.equals("400")) {
            if (ContextCompat.checkSelfPermission(Main2Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Main2Activity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 400);
//            } else {
//                setImage();
//            }
            }
        }
        else {
            setImage();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"Pause",Toast.LENGTH_SHORT).show();
    }

    public void setImage()
    {
        String photoPath = Environment.getExternalStorageDirectory()+"/DCIM/100MEDIA/1.jpg";
        File file1 = new File(photoPath);
        Uri uri = Uri.fromFile(file1);
        Bitmap selectedImageBitMap = null;
        try {
            selectedImageBitMap= MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //bitmap = Bitmap.createScaledBitmap(bitmap,525,525,true);

        imageView.setImageBitmap(selectedImageBitMap);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 400)
        {
            if ((grantResults.length>0)&& grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                setImage();
            }
        }
    }
}
