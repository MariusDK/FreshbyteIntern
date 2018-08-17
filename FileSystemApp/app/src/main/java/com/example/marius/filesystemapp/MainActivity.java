package com.example.marius.filesystemapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private EditText editText;
    private Button save_button;
    private File file;
    private FileOutputStream fileOutputStream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        file = new File(this.getFilesDir(), "myFile");




        imageView = (ImageView) findViewById(R.id.image_id);
        editText = (EditText) findViewById(R.id.editText_id);
        save_button = (Button) findViewById(R.id.save_id);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            System.out.println("Permisiunea nu este data!");
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},300);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        {
            System.out.println("Permisiuneaeste data!");
            //ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},300);
        }


        String state = Environment.getExternalStorageState();
        if ((state.equals(Environment.MEDIA_MOUNTED))||(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)))
        {
            String photoPath = Environment.getExternalStorageDirectory()+"/DCIM/100MEDIA/1.jpg";
            //String filepath = Environment.getExternalStorageDirectory().getPath();
//            String m_path = Environment.getExternalStoragePublicDirectory(
////                    Environment.DIRECTORY_DCIM).getAbsolutePath();
            File file1 = new File(photoPath);

            boolean fileExist = file1.exists();
            Uri uri = Uri.fromFile(file1);
            //Bitmap bitmap1 = BitmapFactory.decodeFile(photoPath);
            //imageView.setImageBitmap(bitmap1);
            //imageView.setImageURI(uri);
//            imageView.setImageBitmap(BitmapFactory.decodeFile(file1.getAbsolutePath()));
//            imageView.setVisibility(View.VISIBLE);
//            Uri imageUri = null;
//            try {
//                imageUri = Uri.parse(
//                    android.provider.MediaStore.Images.Media.insertImage(
//                            getContentResolver(),
//                            file1.getAbsolutePath(), null, null));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
//            Bitmap bitmap = BitmapFactory.decodeFile(file1.getAbsolutePath(),bmOptions);
         Bitmap selectedImageBitMap = null;
            try {
                selectedImageBitMap= MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //bitmap = Bitmap.createScaledBitmap(bitmap,525,525,true);

            imageView.setImageBitmap(selectedImageBitMap);
            //imageView.setImageURI(imageUri);
            //imageView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        String state = Environment.getExternalStorageState();
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fileExist("myFile")==true)
                {
                    try {
                        FileOutputStream myFile = fileOutputStream = openFileOutput("myFile", Context.MODE_APPEND);
                        String s = editText.getText().toString()+" ";
                        myFile.write(s.getBytes());
                        myFile.close();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    try {
                        FileOutputStream myFile = fileOutputStream = openFileOutput("myFile", Context.MODE_PRIVATE);
                        myFile.write(editText.getText().toString().getBytes());
                        myFile.close();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }


        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case 300:{
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    String photoPath = Environment.getExternalStorageDirectory()+"/DCIM/100MEDIA/1.jpg";
                    File file1 = new File(photoPath);

                    boolean fileExist = file1.exists();
                    Uri uri = Uri.fromFile(file1);
                    Bitmap selectedImageBitMap = null;
            try {
                selectedImageBitMap= MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
                    imageView.setImageBitmap(selectedImageBitMap);
                }
                else
                {}
                return;
            }
        }
    }

    public boolean fileExist(String filename)
    {
        File file1 = getBaseContext().getFileStreamPath(filename);
        return file1.exists();
    }
}
