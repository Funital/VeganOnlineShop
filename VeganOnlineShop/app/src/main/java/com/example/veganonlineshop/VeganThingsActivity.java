package com.example.veganonlineshop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VeganThingsActivity extends AppCompatActivity {

    ImageView vegan_back;
    ImageView vegan_back2;

    LinearLayout layout1Obj;
    LinearLayout layout2Obj;


    ImageView  vegan11Obj;
    ImageView vegan12Obj;
    ImageView vegan13Obj;
    ImageView vegan14Obj;
    ImageView vegan21Obj;
    ImageView vegan22Obj;
    ImageView vegan23Obj;
    ImageView vegan24Obj;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegan_things);

        vegan_back = findViewById(R.id.vegan_iv1);
        vegan_back2 = findViewById(R.id.vegan_iv2);
        layout1Obj = findViewById(R.id.list_layout01);
        layout2Obj = findViewById(R.id.list_layout02);


        vegan11Obj = findViewById(R.id.veganThing1_iv01);
        vegan12Obj = findViewById(R.id.veganThing1_iv02);
        vegan13Obj = findViewById(R.id.veganThing1_iv03);
        vegan14Obj = findViewById(R.id.veganThing1_iv04);

        vegan21Obj = findViewById(R.id.veganThing2_iv01);
        vegan22Obj = findViewById(R.id.veganThing2_iv02);
        vegan23Obj = findViewById(R.id.veganThing2_iv03);
        vegan24Obj = findViewById(R.id.veganThing2_iv04);

        vegan11Obj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VeganItem1();
            }
        });

        vegan12Obj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VeganItem2();
            }
        });
        vegan13Obj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VeganItem3();
            }
        });
        vegan14Obj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VeganItem4();
            }
        });
        vegan21Obj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VeganItem1();
            }
        });
        vegan22Obj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VeganItem2();
            }
        });
        vegan23Obj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VeganItem3();
            }
        });
        vegan24Obj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VeganItem4();
            }
        });
    }


    public void onVeganViewClick(View view) {
        if (view.getId() == R.id.vegan_iv1) {
            vegan_back.setImageResource(R.drawable.list_type1);
            vegan_back2.setImageResource(R.drawable.list_type22);
            layout1Obj.setVisibility(View.VISIBLE);
            layout2Obj.setVisibility(View.INVISIBLE);
        } else if (view.getId() == R.id.vegan_iv2) {
            vegan_back.setImageResource(R.drawable.list_type12);
            vegan_back2.setImageResource(R.drawable.list_type2);
            layout1Obj.setVisibility(View.INVISIBLE);
            layout2Obj.setVisibility(View.VISIBLE);
        }
    }

    public void VeganItem1(){
        Intent intent  = new Intent(this, VeganThingActivity.class);
        intent.putExtra("veganId", "VEGAN1");
        intent.putExtra("name", "스탠다드 블루종 스웨이드 자켓");
        intent.putExtra("price", "133000");
        intent.putExtra("date", "2024FW");
        intent.putExtra("sex", "남녀공용");
        intent.putExtra("brand", "스탠다드");
        intent.putExtra("description", "재킷 / 남녀공용 / 무늬: 무지 / 네크라인: 카라 \n 여밈방식: 집업");
        intent.putExtra("category", "의류");
        startActivity(intent);
    }

    public void VeganItem2(){
        Intent intent  = new Intent(this, VeganThingActivity.class);
        intent.putExtra("veganId", "VEGAN2");
        intent.putExtra("name", "웨스턴 비건 스웨이드 자켓 카키");
        intent.putExtra("price", "49000");
        intent.putExtra("date", "2024FW");
        intent.putExtra("sex", "남");
        intent.putExtra("brand", "언더오프");
        intent.putExtra("description", "아우터");
        intent.putExtra("category", "의류");
        startActivity(intent);
    }

    public void VeganItem3(){
        Intent intent  = new Intent(this, VeganThingActivity.class);
        intent.putExtra("veganId", "VEGAN3");
        intent.putExtra("name", "필드자켓 검정");
        intent.putExtra("price", "69000");
        intent.putExtra("date", "2024FW");
        intent.putExtra("sex", "남녀공용");
        intent.putExtra("brand", "필루미네이트");
        intent.putExtra("description", "아우터");
        intent.putExtra("category", "의류");
        startActivity(intent);
    }

    public void VeganItem4(){
        Intent intent  = new Intent(this, VeganThingActivity.class);
        intent.putExtra("veganId", "VEGAN4");
        intent.putExtra("name", "그릭요거트 무지방 플레인");
        intent.putExtra("price", "17000");
        intent.putExtra("date", "2024");
        intent.putExtra("sex", "");
        intent.putExtra("brand", "Chobani");
        intent.putExtra("description", "요구르트/무지방");
        intent.putExtra("category", "음식");
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_vegans, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("검색어(상품명)을 입력해주세요.");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(getApplicationContext(),query,Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
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
        } else if (item.getItemId() == R.id.main_video) {
            Toast.makeText(getApplicationContext(), "동영상강좌 메뉴가 클릭되었습니다", Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.menu_customer) {
            Toast.makeText(getApplicationContext(), "고객센터 메뉴가 클릭되었습니다", Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.menu_mypage) {
            Toast.makeText(getApplicationContext(), "마이페이지 메뉴가 클릭되었습니다", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}