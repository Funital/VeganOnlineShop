package com.example.veganonlineshop;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyPageActivity extends AppCompatActivity {
    ImageView loginimg ;
    FloatingActionButton fab_add_photo ;
    Button orderList;
    File m_imageFile;
    private int REQUEST_TAKE_PHOTO = 100;
    private int REQUEST_IMAGE_CHOOSE =101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loginimg = findViewById(R.id.loginimg);
        fab_add_photo = findViewById(R.id.add_photo);
        orderList = findViewById(R.id.bt_order);
        SharedPreferences pref = getSharedPreferences("prefile",  Context.MODE_PRIVATE);
        String nameStr = pref.getString("filename", null);

        if (nameStr != null && !nameStr.isEmpty()) {
            Glide.with(this).load(Uri.parse(nameStr)).into(loginimg);
        }

        fab_add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MyPageActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_choose_app, null);
                builder.setTitle("선택하세요");
                builder.setView(dialogView);
                builder.setNegativeButton("취소", null);
                AlertDialog dialog = builder.show();

                LinearLayout layoutCameraPick = dialogView.findViewById(R.id.layoutCameraPick);
                LinearLayout layoutGalleryPick = dialogView.findViewById(R.id.layoutGalleryPick);

                layoutCameraPick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        File photoFile = null;
                        try {
                            photoFile = createImageFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        String packageName = "com.example.veganonlineshop.fileprovider";
                        Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), packageName, photoFile);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(intent, REQUEST_TAKE_PHOTO);
                        m_imageFile = photoFile;
                        dialog.dismiss();
                    }
                });
                layoutGalleryPick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent, REQUEST_IMAGE_CHOOSE);
                        dialog.dismiss();
                    }
                });
            }
        });

        orderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPageActivity.this, OrderList.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            Uri photoUri = FileProvider.getUriForFile(this, "com.example.veganonlineshop.fileprovider", m_imageFile);
            Glide.with(this).load(photoUri).into(loginimg);
        }
        else if (requestCode == REQUEST_IMAGE_CHOOSE && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();
            if (selectedImage != null) {
                Glide.with(this).load(selectedImage).into(loginimg);
                // m_imageFile 제거 (절대 경로로의 접근을 피합니다)
                SharedPreferences pref = getSharedPreferences("prefile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("filename", selectedImage.toString());
                editor.apply();
            }
        }

        if (m_imageFile != null) {
            SharedPreferences pref = getSharedPreferences("prefile", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            Uri photoUri = FileProvider.getUriForFile(this, "com.example.veganonlineshop.fileprovider", m_imageFile);
            editor.putString("filename", photoUri.toString());
            editor.apply();
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = File.createTempFile(
                "JPEG_"+ timeStamp,
                ".jpg",
                storageDir);

        return imageFile;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}

