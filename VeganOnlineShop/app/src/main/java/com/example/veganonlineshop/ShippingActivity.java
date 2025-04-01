package com.example.veganonlineshop;

import static com.example.veganonlineshop.MainActivity.cartRepositoryObj;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.veganonlineshop.R;
import com.example.veganonlineshop.CartActivity;

public class ShippingActivity extends  AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);

        EditText etName = findViewById(R.id.et_Name);
        EditText etDate = findViewById(R.id.et_Date);
        EditText etPhone = findViewById(R.id.et_Phone);
        EditText etZipcode = findViewById(R.id.et_Zipcode);
        EditText etAddress= findViewById(R.id.et_Address);


        Button btCancel = findViewById(R.id.bt_Cancel);
        Button btSubmit = findViewById(R.id.bt_Submit);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShippingActivity.this, OrderActivity.class);
                intent.putExtra("name", etName.getText().toString());
                intent.putExtra("date", etDate.getText().toString());
                intent.putExtra("phone", etPhone.getText().toString());
                intent.putExtra("zipcode", etZipcode.getText().toString());
                intent.putExtra("address", etAddress.getText().toString());
                intent.putExtra("total", Integer.toString(cartRepositoryObj.grandTotalCartItems()));
                startActivity(intent);
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShippingActivity.this, CartActivity.class);
                startActivity(intent);
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