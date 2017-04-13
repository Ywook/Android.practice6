package com.examples.androidpractice5;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listview;

    resAdapter adapter;

    EditText tv;

    int Addition = 1;

    ArrayList<restaurant> storage = new ArrayList<>();

    Button btn1, btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("나의 맛집");

        init();
    }


    void init(){
        listview = (ListView)findViewById(R.id.listview);
        tv = (EditText) findViewById(R.id.tv);

        adapter = new resAdapter(storage,this);
        String[] menu = {"123","123","123"};

        storage.add(new restaurant("feff","2222","11111","11111",menu,1));
        storage.add(new restaurant("ccc","5555","11111","11111",menu,3));
        storage.add(new restaurant("ddqwd","33333","11111","11111",menu,2));
        storage.add(new restaurant("BBQ","111111","11111","11111",menu,1));

        listview.setAdapter(adapter);

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                final int position = i;
                dlg.setTitle("삭제")
                        .setMessage("삭제하시겠습니까 ?")
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                storage.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        }).show();
                return true;
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);

                intent.putExtra("restaurant", storage.get(i));

                startActivity(intent);
            }
        });

        tv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void onClick(View v){
        if (v.getId() == R.id.btn1) {
            Intent intent = new Intent(this, Main2Activity.class);
            startActivityForResult(intent, Addition);
        }else if(v.getId() == R.id.btn2){
            adapter.setSort(adapter.NAME_ASC);
        }else if(v.getId() == R.id.btn3){
            adapter.setSort(adapter.TYPE_ASC);
        }else if(v.getId() == R.id.btn4){
            for(int i = 0 ; i < storage.size(); i++){
                View child = listview.getChildAt(i);
                CheckBox cb = (CheckBox)child.findViewById(R.id.checkBox);
                cb.setVisibility(View.GONE);

            }
        }else{

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Addition){
            if(resultCode == RESULT_OK){
                restaurant r = data.getParcelableExtra("restaurant");
                storage.add(r);
                adapter.notifyDataSetChanged();
            }
        }
    }


}
