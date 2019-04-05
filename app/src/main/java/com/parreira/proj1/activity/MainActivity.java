package com.parreira.proj1.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parreira.proj1.BuildConfig;
import com.parreira.proj1.R;
import com.parreira.proj1.adapter.MyAdapter;
import com.parreira.proj1.asynktask.UpdateDatabase;
import com.parreira.proj1.database.PessoaDatabase;
import com.parreira.proj1.network.PessoaService;
import com.parreira.proj1.network.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Headers;

public class MainActivity extends AppCompatActivity{

    private final String TAG = MainActivity.class.getSimpleName();
    private static final String DATABASE_NAME = "pessoaDatabase";

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<Pessoa> pessoasOnLine = new ArrayList<Pessoa>();

        Log.d(TAG, "onCreate");

        /*Create handle for the RetrofitInstance interface*/
        PessoaService service = RetrofitClientInstance.getRetrofitInstance().create(PessoaService.class);
        Call<List<Pessoa>> call = service.getPessoas();
        call.enqueue(new Callback<List<Pessoa>>() {
            @Override
            public void onResponse(Call<List<Pessoa>> call, Response<List<Pessoa>> response) {
                Log.d("Callback", response.message());

                pessoasOnLine.addAll(response.body());

               initArray(pessoasOnLine);
            }

            @Override
            public void onFailure(Call<List<Pessoa>> call, Throwable t) {
                Log.d("Callback", t.getMessage());
                Log.d("Callback", call.request().headers().toString());
                Log.d("Callback", call.request().url().toString());

            }
        });


        final PessoaDatabase pessoaDatabase;
        pessoaDatabase = Room.databaseBuilder(getApplicationContext(),PessoaDatabase.class,DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();


                Pessoa pessoa3 =new Pessoa();
                pessoa3.setId(3);
                pessoa3.setNome("Manolo");
                pessoa3.setTexto("sdfsdf sd fsdfsd sd fsdf sd sdsddfsdf ");


        //new UpdateDatabase(pessoaDatabase).execute(pessoa3);
        pessoasOnLine.add(pessoa3);
        pessoasOnLine.add(pessoa3);
        pessoasOnLine.add(pessoa3);
        pessoasOnLine.add(pessoa3);
        pessoasOnLine.add(pessoa3);
        pessoasOnLine.add(pessoa3);
        pessoasOnLine.add(pessoa3);
        pessoasOnLine.add(pessoa3);


        //pessoasOnLine.add(pessoaDatabase.daoAcess().obterPessoa(3));
        initArray(pessoasOnLine);

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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        // Handle item selection
        switch (item.getItemId()) {
            case R.id.version:
                String versionName = BuildConfig.VERSION_NAME;
                Toast toast = Toast.makeText(this,"Version " + versionName,Toast.LENGTH_SHORT);
                toast.show();
        }
        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }



    private void initArray (List<Pessoa> list) {

       RecyclerView.Adapter mAdapter;
        LinearLayoutManager layoutManager;

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        //final ListView listView = (ListView) findViewById(R.id.lv_text);
        //final MyAdapter myAdapter = new MyAdapter(this, list);
        //listView.setAdapter(myAdapter);
        //myAdapter.notifyDataSetChanged();


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(this,list);
        recyclerView.setAdapter(mAdapter);




/*


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pessoa pessoa = (Pessoa) listView.getItemAtPosition(position);
                Intent myIntent = new Intent(MainActivity.this, SecondActivity.class);
                myIntent.putExtra(SecondActivity.KEY_PESSOA, pessoa);
                startActivity(myIntent);

            }
        });
*/

    }



    /*
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Pessoa pessoa = (Pessoa) parent.getItemAtPosition(position);
        switch (view.getId()) {
            case R.id.item_lista:

                Intent myIntent = new Intent(MainActivity.this, SecondActivity.class);
                myIntent.putExtra(SecondActivity.KEY_PESSOA, pessoa);
                startActivity(myIntent);
        }


    }*/
}


