package com.jkds.saveinput;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> listMap;
    private LinearLayout sLinerLayout;
    private boolean IsSave = true;//默认打开保存
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listMap = new ArrayList<String>();
        sLinerLayout = findViewById(R.id.ll);
        List<String> listMap = SPUtils.getDataListpz("listMap", MainActivity.this);
        for (int i = 0; i < sLinerLayout.getChildCount(); i++) {
            View v = sLinerLayout.getChildAt(i);
            if (v instanceof EditText) {
                EditText editText = (EditText) sLinerLayout.getChildAt(i);
                if (listMap != null && listMap.size() > 0) {
                    editText.setText(listMap.get(i));
                }

            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (IsSave) {
            for (int i = 0; i < sLinerLayout.getChildCount(); i++) {
                View v = sLinerLayout.getChildAt(i);
                if (v instanceof EditText) {
                    EditText mRadioGroup = (EditText) sLinerLayout.getChildAt(i);
                    listMap.add(mRadioGroup.getText().toString().trim());
                }
                SPUtils.setDataListpz("listMap", listMap, MainActivity.this);
            }
        }


    }
}