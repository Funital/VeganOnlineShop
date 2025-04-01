package com.example.veganonlineshop;

import static com.example.veganonlineshop.MainActivity.cartRepositoryObj;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.MenuItemCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VeganThingActivity extends AppCompatActivity {

    ImageView imgObj;
    TextView veganIdObj;
    TextView nameObj;
    TextView priceObj;
    TextView dateObj;
    TextView brandObj;
    TextView sexObj;
    TextView descriptionObj;
    TextView categoryObj;

    Button cartplusObj;
    TextView cartCount;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegan_thing);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgObj = findViewById(R.id.vegan_iv1);
        veganIdObj = findViewById(R.id.vegan_tv1);
        nameObj = findViewById(R.id.vegan_tv2);
        priceObj = findViewById(R.id.vegan_tv3);
        dateObj = findViewById(R.id.vegan_tv4);
        brandObj = findViewById(R.id.vegan_tv5);
        sexObj = findViewById(R.id.vegan_tv6);
        descriptionObj = findViewById(R.id.vegan_tv7);
        categoryObj = findViewById(R.id.vegan_tv8);

        Intent intent = getIntent();
        veganIdObj.setText(intent.getStringExtra("veganId"));
        nameObj.setText(intent.getStringExtra("name"));
        priceObj.setText(intent.getStringExtra("price"));
        dateObj.setText(intent.getStringExtra("date"));
        sexObj.setText(intent.getStringExtra("sex"));
        brandObj.setText(intent.getStringExtra("brand"));
        descriptionObj.setText(intent.getStringExtra("description"));
        categoryObj.setText(intent.getStringExtra("category"));

        if (veganIdObj.getText().toString().equals("VEGAN1")) {
            imgObj.setImageResource(R.drawable.clothes1);
        } else if (veganIdObj.getText().toString().equals("VEGAN2")) {
            imgObj.setImageResource(R.drawable.clothes2);
        } else if (veganIdObj.getText().toString().equals("VEGAN3")) {
            imgObj.setImageResource(R.drawable.clothes4);
        } else if (veganIdObj.getText().toString().equals("VEGAN4")) {
            imgObj.setImageResource(R.drawable.food1);
        }

        VeganThings veganThings = new VeganThings();

        veganThings.veganId = veganIdObj.getText().toString();
        veganThings.name = nameObj.getText().toString();
        veganThings.price = Integer.parseInt(priceObj.getText().toString());
        veganThings.date = dateObj.getText().toString();
        veganThings.sex = sexObj.getText().toString();
        veganThings.brand =  brandObj.getText().toString();
        veganThings.description = descriptionObj.getText().toString();
        veganThings.category = categoryObj.getText().toString();
        veganThings.quantity = 0;

        cartplusObj = findViewById(R.id.vegan_bt1);
        cartplusObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(VeganThingActivity.this);

                alertDialog.setTitle("비건상품주문");
                alertDialog.setMessage("상품을 장바구니에 추가하겠습니까?");
                DialogInterface.OnClickListener listener  = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch(i){
                            case DialogInterface.BUTTON_POSITIVE  :
                                cartRepositoryObj.addCartItems(veganThings);

                                int count = Integer.parseInt(cartCount.getText().toString()) + 1;
                                cartCount.setText(Integer.toString(count));
                                dialogInterface.dismiss();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE   :
                                dialogInterface.cancel();
                                break;
                        }
                    }
                };

                alertDialog.setPositiveButton("예", listener);
                alertDialog.setNegativeButton("아니오", listener);
                alertDialog.show();

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_vegan, menu);

        MenuItem item = menu.findItem(R.id.menu_cart);
        MenuItemCompat.setActionView(item, R.layout.cart_count_layout);
        RelativeLayout notifyCount = (RelativeLayout)item.getActionView();
        cartCount = (TextView) notifyCount.findViewById(R.id.hotlist_hot);
        ImageView image = (ImageView)notifyCount.findViewById(R.id.hotlist_bell);


        cartCount.setText(Integer.toString(cartRepositoryObj.countCartItems()));

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(myIntent);
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        } else if (item.getItemId() == R.id.menu_home) {
            Toast.makeText(getApplicationContext(), "홈으로 메뉴가 클릭되었습니다", Toast.LENGTH_LONG).show();
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        } else if (item.getItemId() == R.id.menu_list) {
            Toast.makeText(getApplicationContext(), "비건목록 메뉴가 클릭되었습니다", Toast.LENGTH_LONG).show();
            finish();
            Intent intent2 = new Intent(this, VeganThingsActivity.class);
            startActivity(intent2);

        } else if (item.getItemId() == R.id.menu_cart) {
            Toast.makeText(getApplicationContext(), "장바구니 메뉴가 클릭되었습니다", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (cartCount!=null) cartCount.setText(Integer.toString(cartRepositoryObj.countCartItems()));
    }
}