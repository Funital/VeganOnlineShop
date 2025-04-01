package com.example.veganonlineshop;

import static com.example.veganonlineshop.MainActivity.cartRepositoryObj;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OrderActivity extends AppCompatActivity {
    OrderAdapter  orderAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String date = intent.getStringExtra("date");
        String phone = intent.getStringExtra("phone");
        String zipcode = intent.getStringExtra("zipcode");
        String address = intent.getStringExtra("address");
        String total = intent.getStringExtra("total");

        TextView tvDate = findViewById(R.id.tv_date);
        TextView tvName = findViewById(R.id.tv_name);
        TextView tvZipcode = findViewById(R.id.tv_zipcode);
        TextView tvAddress = findViewById(R.id.tv_address);
        TextView tvTotal = findViewById(R.id.tv_total);

        tvDate.setText("배송일 : " + date);
        tvName.setText("성명 : " + name + "(" + phone + ")");
        tvZipcode.setText("우편번호 : " + zipcode);
        tvAddress.setText("주소 : " + address);
        tvTotal.setText(total);

        RecyclerView recyclerview = findViewById(R.id.rv_order);
        orderAdapter = new OrderAdapter(OrderActivity.this);
        recyclerview.setAdapter(orderAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(linearLayoutManager);

        Button orderSubmit = findViewById(R.id.bt_submit);
        Button orderCancel = findViewById(R.id.bt_cancel);

        orderSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderActivity.this);
                alertDialog.setTitle("비건 상품 주문 완료");
                alertDialog.setMessage("주문해 주셔서 감사합니다.");


                alertDialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences pref = getSharedPreferences("order_prefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.putString("name", tvName.getText().toString());
                        editor.putString("date", tvDate.getText().toString());
                        editor.putString("zipcode", tvZipcode.getText().toString());
                        editor.putString("address", tvAddress.getText().toString());
                        editor.putString("total", tvTotal.getText().toString());
                        editor.apply();

                        cartRepositoryObj.cartVegans.clear();
                        Intent intent = new Intent(OrderActivity.this, OrderList.class);
                        startActivity(intent);
                    }
                });

                alertDialog.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alertDialog.show();
            }
        });
        orderCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(OrderActivity.this);
                alertDialog.setTitle("비건 상품 주문 취소");
                alertDialog.setMessage("주문을 취소합니다.");


                alertDialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cartRepositoryObj.cartVegans.clear();
                        Intent intent = new Intent(OrderActivity.this, MainActivity.class);
                        startActivity(intent);
                       finish();
                    }
                });
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
