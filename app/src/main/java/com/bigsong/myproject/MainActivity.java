package com.bigsong.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bigsong.myproject.exercise01.activity_main_selfdefineviewgroup;
import com.bigsong.myproject.exercise02.MainActivity_selfDefineView;

public class MainActivity extends AppCompatActivity {

    String[] data = {"1.自定义ViewGroup", "2.自定义View"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView mainListView = (ListView) findViewById(R.id.listview_main);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        mainListView.setAdapter(adapter);
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(MainActivity.this, activity_main_selfdefineviewgroup.class);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, MainActivity_selfDefineView.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }
}
