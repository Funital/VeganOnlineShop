package com.example.veganonlineshop;

import static com.example.veganonlineshop.MainActivity.cartRepositoryObj;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.veganonlineshop.R;
//import com.androidbook.bookmarket.model.Book;
//import com.androidbook.bookmarket.order.ShippingActivity;


import java.util.ArrayList;



public class CartActivity extends AppCompatActivity {

    CartAdapter cartAdapter;

    static CheckBox allChooseCheckBox;
    static TextView cartTotalPriceObj;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        cartTotalPriceObj = findViewById(R.id.tv_cart_total_price);

        cartTotalPriceObj.setText(Integer.toString(cartRepositoryObj.grandTotalCartItems()));

        RecyclerView recyclerviewCart = findViewById(R.id.cart_recyclerview);
        cartAdapter = new CartAdapter(CartActivity.this);
        recyclerviewCart.setAdapter(cartAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerviewCart.setLayoutManager(linearLayoutManager);

        cartAdapter.setOnSelectChangedListener(new CartAdapter.OnSelectChangedListener() {

            @Override
            public void onSelectChanged(ArrayList<VeganThings> item) {
                refreshRecyclerView();
            }
        });

        allChooseCheckBox = findViewById(R.id.cb_cart_selectall);
        allChooseCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               if (b)
                  doSelectAll();
                else
                    doSelectNone();
            }
        });

        Button orderButton  = findViewById(R.id.bt_cart_order);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, ShippingActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cart, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    void  doSelectAll()  {

        int num = cartAdapter.getItemCount();
        if (num > 0) {
            boolean isChanged = false;
            for (int i=0 ; i<=num-1;i++) {
                VeganThings item = cartRepositoryObj.cartVegans.get(i);
                if (!item.isCheck) {
                    item.isCheck = true;
                    isChanged = true;
                }
            }
            if (isChanged) {
                refreshRecyclerView();
            }
        }
    }

    void doSelectNone() {
        int num = cartAdapter.getItemCount();
        if ( num > 0) {
            boolean isChanged = false;
            for (int i = 0; i<=num-1 ;i++) {
                VeganThings item  = cartRepositoryObj.cartVegans.get(i);
                if (item.isCheck) {
                    item.isCheck = false;
                    isChanged = true;
                }
            }
            if (isChanged) {
                refreshRecyclerView();
            }
        }
    }

    private void refreshRecyclerView() {
        cartAdapter.notifyItemRangeRemoved(0, cartRepositoryObj.cartVegans.size());
        cartTotalPriceObj.setText(Integer.toString(cartRepositoryObj.grandTotalCartItems()));
        setSelectAllCheckBoxState();
    }
    private void setSelectAllCheckBoxState() {
        if (cartRepositoryObj.cartVegans == null) {
            allChooseCheckBox.setChecked(false);
            return;
        }
        for (int i=0 ; i < cartRepositoryObj.cartVegans.size(); i++) {
            if (!cartRepositoryObj.cartVegans.get(i).isCheck) {
                allChooseCheckBox.setChecked(false);
                return;
            }
        }
        allChooseCheckBox.setChecked(true);

    }

    public static void cartUpdate(){
        cartRepositoryObj.countCartItems();
        cartTotalPriceObj.setText(Integer.toString(cartRepositoryObj.grandTotalCartItems()));
    }

}
