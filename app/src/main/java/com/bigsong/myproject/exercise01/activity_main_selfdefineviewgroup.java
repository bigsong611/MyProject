package com.bigsong.myproject.exercise01;

import android.app.Activity;
import android.os.Bundle;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/6/20.
 */
public class activity_main_selfdefineviewgroup extends Activity {

    private List<String> mDatas = Arrays.asList("Android", "Java");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }*/
}
