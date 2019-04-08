package com.parreira.proj1.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();

    private List<Pessoa> pessoaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate");

        PessoaService service = RetrofitClientInstance.getRetrofitInstance().create(PessoaService.class);
        Call<List<Pessoa>> call = service.getPessoas();
        call.enqueue(new Callback<List<Pessoa>>() {
            @Override
            public void onResponse(Call<List<Pessoa>> call, Response<List<Pessoa>> response) {
                Log.d("Callback", response.message());

                pessoaList = response.body();

                for (Pessoa pessoa : pessoaList) {
                    if (PessoaDatabase.getAppDatabase(MainActivity.this).daoAcess().getPessoaById(pessoa.getId()) == null) {
                        PessoaDatabase.getAppDatabase(MainActivity.this).daoAcess().insertPessoa(pessoa);
                    }
                }



                initArray(pessoaList);
            }

            @Override
            public void onFailure(Call<List<Pessoa>> call, Throwable t) {
                Log.d("Callback", "Failure");

                pessoaList.addAll(PessoaDatabase.getAppDatabase(MainActivity.this).daoAcess().getPessoaAll());
                initArray(pessoaList);

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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        // Handle item selection
        switch (item.getItemId()) {
            case R.id.version:
                String versionName = BuildConfig.VERSION_NAME;
                Toast toast = Toast.makeText(this, "Version " + versionName, Toast.LENGTH_SHORT);
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


    private void initArray(List<Pessoa> list) {

        RecyclerView.Adapter mAdapter;
        LinearLayoutManager layoutManager;

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mAdapter = new MyAdapter(this, list);
        recyclerView.setAdapter(mAdapter);
    }
}


