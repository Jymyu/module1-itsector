package com.parreira.proj1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parreira.proj1.R;
import com.parreira.proj1.database.PessoaDatabase;
import com.parreira.proj1.network.PessoaService;
import com.parreira.proj1.network.RetrofitClientInstance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

/**
 * Created by João Parreira on 4/9/2019.
 * <p>
 * ITSector ITSector
 * joao.parreira@itsector.pt
 * <p>
 * Copyright (c) ITSector Software. All rights reserved.
 * <p>
 * ITSector Software Confidential and Proprietary information. It is strictly forbidden for 3rd
 * parties to modify, decompile, disassemble, defeat, disable or circumvent any protection
 * mechanism; to sell, license, lease, rent, redistribute or make accessible to any third party,
 * whether for profit or without charge.
 * *
 */

public class AddPostActivity extends AppCompatActivity {

    final static public String TAG = "call";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final List<Pessoa> pessoas = new ArrayList<>();

        setContentView(R.layout.activity_add_post);

        final EditText editTextNome = (EditText) findViewById(R.id.et_pessoa2);

        final EditText editTextNacionalidade = (EditText) findViewById(R.id.et_nacionalidade2);
        final EditText editTextPost = (EditText) findViewById(R.id.et_post2);
        Button btn = (Button) findViewById(R.id.btn_adiciona2);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pessoa pessoa = new Pessoa();
                pessoa.setNome(editTextNome.getText().toString());
                pessoa.setTexto(editTextPost.getText().toString());
                pessoa.setImage("https://res.cloudinary.com/demo/image/facebook/65646572251.jpg");

                String nacionalidade = editTextNacionalidade.getText().toString();
                if (PessoaDatabase.getAppDatabase(AddPostActivity.this).NacionalidadeDao().getNacionalidadeByNome(nacionalidade) > 0) {
                    pessoa.setNacionalidade(PessoaDatabase.getAppDatabase(AddPostActivity.this).NacionalidadeDao().getNacionalidadeByNome(nacionalidade));
                } else {
                    pessoa.setNacionalidade(1);
                }

                pessoas.add(pessoa);
                PessoaService service = RetrofitClientInstance.getRetrofitInstance().create(PessoaService.class);
                Call<List<Pessoa>> call = service.insertPessoa(pessoas);
                call.enqueue(new Callback<List<Pessoa>>() {
                    @Override
                    public void onResponse(Call<List<Pessoa>> call, Response<List<Pessoa>> response) {

                        if (response.isSuccessful()) {
                            Log.d(TAG, "post submitted to API." + response.body().toString());
                        }


                    }

                    @Override
                    public void onFailure(Call<List<Pessoa>> call, Throwable t) {
                        Log.d(TAG, "Unable to submit post to API.");

                    }
                });

                    PessoaDatabase.getAppDatabase(AddPostActivity.this).daoAcess().insertAll(pessoas);

                Intent intent = new Intent(AddPostActivity.this, MainActivity.class);
                intent.putExtra(SecondActivity.KEY_PESSOA, pessoa);
                startActivity(intent);


            }
        });
    }


}

