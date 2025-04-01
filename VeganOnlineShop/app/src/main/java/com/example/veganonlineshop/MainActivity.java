package com.example.veganonlineshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.veganonlineshop.CartRepo;


import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.veganonlineshop.MyPageActivity;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    ImageView  menuObj;

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigation;
    public static CartRepo cartRepositoryObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cartRepositoryObj = new CartRepo();

        menuObj = findViewById(R.id.imageView);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigation = findViewById(R.id.navigation);
        drawerLayout =  findViewById(R.id.drawer_layout);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        navigation.setNavigationItemSelectedListener(this);

    }

    public void onVeganClick(View view){
        Toast.makeText(getApplicationContext(),"비건 상품 목록 버튼이 클릭되었습니다",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, VeganThingsActivity.class);
        startActivity(intent);

    }
    public void onVideoClick(View view){
        Toast.makeText(getApplicationContext(),"동영상 버튼이 클릭되었습니다",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, VeganVideoActivity.class);
        startActivity(intent);
    }

    public void onMemoClick(View view){
        Toast.makeText(getApplicationContext(),"메모 버튼이 클릭되었습니다",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MemoActivity.class);
        startActivity(intent);
    }

    public void onMyPageClick(View view){
        Toast.makeText(getApplicationContext(),"마이페이지버튼이 클릭되었습니다",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MyPageActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu01) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            LayoutInflater inflater = getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog_login, null);
            builder.setView(dialogView);
            AlertDialog dialog = builder.show();

            Button loginBtn   = dialogView.findViewById(R.id.loginBtn);
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView tvID  = dialogView.findViewById(R.id.userID);
                    TextView tvPW  = dialogView.findViewById(R.id.userPW);

                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "아이디: "+ tvID.getText() + "비밀번호: " + tvPW.getText(), Toast.LENGTH_LONG).show();
                }
            });
        } else if (item.getItemId() == R.id.menu02) {
            Toast.makeText(this, " 메뉴2 : " + item.getTitle(), Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.menu03) {
            Toast.makeText(this, " 메뉴3 : " + item.getTitle(), Toast.LENGTH_SHORT).show();
        }

        drawerLayout.closeDrawers();
        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }
}
