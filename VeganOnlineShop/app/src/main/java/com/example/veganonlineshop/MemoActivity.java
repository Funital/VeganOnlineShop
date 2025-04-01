package com.example.veganonlineshop;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class MemoActivity extends AppCompatActivity {
    DatabaseHelperMemo dbHelper;
    int  selectItemPos ;
    SimpleAdapter adapter;
    ListView listView ;
    ArrayList<HashMap<String, String>> memoList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHelper = new DatabaseHelperMemo(this);
        memoList = new ArrayList<HashMap<String, String>>();

        listView = findViewById(R.id.memo_list);
        Button insert  = findViewById(R.id.bt_insert);
        Button update  = findViewById(R.id.bt_update);
        Button delete  = findViewById(R.id.bt_delete);

        ReadData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectItemPos = i; //항목 선택 위치
            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MemoActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.activity_memo_dialog, null);
                builder.setView(dialogView);
                AlertDialog dialog = builder.show();

                Button dlginsert = dialogView.findViewById(R.id.insert);
                Button dlgcancel = dialogView.findViewById(R.id.cancel);

                dlginsert.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LocalDate date = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            date = LocalDate.now();
                        }
                        EditText title = dialogView.findViewById(R.id.title);
                        EditText content = dialogView.findViewById(R.id.content);
                        dbHelper.insertData(date.toString(), title.getText().toString(), content.getText().toString());
                        dialog.dismiss();
                        ReadData();
                    }
                });
                dlgcancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog  = new AlertDialog.Builder(MemoActivity.this);
                alertDialog.setTitle("메모장 삭제");
                alertDialog.setMessage("선택한 메모를 삭제하겠습니까?");

                alertDialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                        int count = adapter.getCount();
                        int checked;

                        if (count >= 0) {
                            checked = selectItemPos;

                            if (checked > -1 && checked < count) {

                                int id = Integer.parseInt(memoList.get(selectItemPos).get("id"));
                                dbHelper.deleteData(id);
                                memoList.clear();
                                ReadData();
                            }
                        }
                    }

                });
                alertDialog.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                if (selectItemPos> -1) alertDialog.show();
                else   Toast.makeText(getApplicationContext(), "항목을 선택하세요", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MemoActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.activity_memo_dialog, null);
                builder.setView(dialogView);

                EditText dlgtitle = dialogView.findViewById(R.id.title);
                EditText dlgcontent = dialogView.findViewById(R.id.content);

                Button dlginsert = dialogView.findViewById(R.id.insert);
                dlginsert.setText("수정");
                Button dlgcancel  = dialogView.findViewById(R.id.cancel);

                int count= adapter.getCount();
                int checked;

                if (count > 0) {
                    checked = selectItemPos;
                    if (checked > -1 && checked < count) {
                        dlgtitle.setText(memoList.get(selectItemPos).get("title").toString());
                        dlgcontent.setText(memoList.get(selectItemPos).get("content").toString());

                        AlertDialog dialog = builder.show();

                        dlginsert.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int id = Integer.parseInt(memoList.get(selectItemPos).get("id"));
                                LocalDate date = null;
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                    date = LocalDate.now();
                                }
                                EditText title  = dialogView.findViewById(R.id.title);
                                EditText content  = dialogView.findViewById(R.id.content);
                                dbHelper.updateData(date.toString(), title.getText().toString(), content.getText().toString(), id);
                                dialog.dismiss();
                                memoList.clear();


                                ReadData();
                            }
                        });

                        dlgcancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });

                    }
                    else Toast.makeText(getApplicationContext(), "항목을 선택하세요", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


    void ReadData(){
        Cursor cursor = dbHelper.readData();
        memoList.clear();
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "데이터를 찾을 수 없습니다", Toast.LENGTH_LONG).show();

        }
        while(cursor.moveToNext()){

            HashMap<String, String> user = new HashMap<>();
            user.put("id", Integer.toString(cursor.getInt(0)));
            user.put("date", cursor.getString(1));
            user.put("title", cursor.getString(2));
            user.put("content",cursor.getString(3));

            memoList.add(user);
        }

        adapter= new SimpleAdapter(MemoActivity.this,
                memoList,
                R.layout.activity_memo_item,
                new String[]{"date", "title", "content"},
                new int[] {R.id.tvDate, R.id.tvTitle, R.id.tvContent}
        );

        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setQueryHint("Search View Hint");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                memoList.clear();
                Cursor cursor = dbHelper.searchReadData(s.toString());
                while(cursor.moveToNext()){

                    HashMap<String, String> memo = new HashMap<>();
                    memo.put("id", Integer.toString(cursor.getInt(0)));
                    memo.put("date", cursor.getString(1));
                    memo.put("title", cursor.getString(2));
                    memo.put("content",cursor.getString(3));

                    memoList.add(memo);

                }

                adapter= new SimpleAdapter(
                        MemoActivity.this,
                        memoList,
                        R.layout.activity_memo_item,
                        new String[]{"date", "title", "content"},
                        new int[] {R.id.tvDate, R.id.tvTitle, R.id.tvContent}
                );
                listView.setAdapter(adapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                ReadData();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}

