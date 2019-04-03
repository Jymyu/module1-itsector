package com.parreira.proj1.activity;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parreira.proj1.R;
import com.parreira.proj1.adapter.MyAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate");

         final ArrayList myArray = new ArrayList();


        final ListView listView = (ListView) findViewById(R.id.lv_text);
        final EditText editText = (EditText) findViewById(R.id.et_intro);
        Button btnChangeName = (Button) findViewById(R.id.btn_change_name);

       // final MyAdapter myAdapter = new MyAdapter(this,R.layout.list_view,myArray);
        //listView.setAdapter(myAdapter);

        btnChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();

                if (text.equals("")) {

                }
                else{

                    //myAdapter.addItem(text);
                    //myAdapter.notifyDataSetChanged();
                    //editText.setText("");
                }


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Toast toast2 = Toast.makeText(this, getString(R.string.toast_text2), Toast.LENGTH_LONG);
        toast2.show();

        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Toast toast = Toast.makeText(this, getString(R.string.toast_text), Toast.LENGTH_LONG);

        toast.show();

        Log.d(TAG, "onRestart");
    }
}


