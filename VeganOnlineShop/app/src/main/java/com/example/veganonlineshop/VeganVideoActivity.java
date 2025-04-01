package com.example.veganonlineshop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class VeganVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegan_video);

        ListView listView = findViewById(R.id.listView);

        String[] items = {"초식마녀 1","Gaz Oakley 1","요리하는 유리 1",
                          "초식마녀 2","Gaz Oakley 2","요리하는 유리 2",
                          "초식마녀 3","Gaz Oakley 3","요리하는 유리 3",
                          "초식마녀 4","Gaz Oakley 4","요리하는 유리 4",
                          "초식마녀 5","Gaz Oakley 5","요리하는 유리 5"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                items
        );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            if (position == 0) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=x_1jJ8bcreI"));
                startActivity(intent);
            } else if (position == 1) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=wA5N3r4FI2k"));
                startActivity(intent);
            } else if (position == 2) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=_t30yQTM4sk"));
                startActivity(intent);
            } else if (position == 3) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=nTOw4iggUF4"));
                startActivity(intent);
            } else if (position == 4) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=empcQwWu-OE"));
                startActivity(intent);
            } else if (position == 5) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=hqNX34MgSe8"));
                startActivity(intent);
            } else if (position == 6) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=PO0rK51RXm8"));
                startActivity(intent);
            } else if (position == 7) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=1ELaLO9Bs0Y"));
                startActivity(intent);
            } else if (position == 8) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=oKCbpsS5DX8"));
                startActivity(intent);
            } else if (position == 9) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=egJHAkkx0fI"));
                startActivity(intent);
            } else if (position == 10) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=1ELaLO9Bs0Y"));
                startActivity(intent);
            } else if (position == 11) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=vFX2aCc9ltA"));
                startActivity(intent);
            } else if (position == 12) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=c8_Kcn2CWpw"));
                startActivity(intent);
            } else if (position == 13) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=v-93Ap4dR8E"));
                startActivity(intent);
            } else if (position == 14) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=GV4WLtDH1mE"));
                startActivity(intent);
            }
        });
    }
}