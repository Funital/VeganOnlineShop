package com.example.veganonlineshop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OrderList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences pref = getSharedPreferences("order_prefs", Context.MODE_PRIVATE);
        String name = pref.getString("name", "주문자 없음");
        String date = pref.getString("date", "주문 날짜 없음");
        String zipcode = pref.getString("zipcode", "우편번호 없음");
        String address = pref.getString("address", "주소 없음");
        String total = pref.getString("total", "0");

        TextView tvName = findViewById(R.id.tv_OrderListName);
        TextView tvDate = findViewById(R.id.tv_OrderListDate);
        TextView tvZipcode = findViewById(R.id.tv_OrderListZipcode);
        TextView tvAddress = findViewById(R.id.tv_OrderListAddress);
        TextView tvTotal = findViewById(R.id.tv_OrderListTotal);

        tvName.setText(name);
        tvDate.setText(date);
        tvZipcode.setText(zipcode);
        tvAddress.setText(address);
        tvTotal.setText(total + "원");

        Button home = findViewById(R.id.bt_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(OrderList.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}