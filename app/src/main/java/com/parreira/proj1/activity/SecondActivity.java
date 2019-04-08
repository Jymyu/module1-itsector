package com.parreira.proj1.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.parreira.proj1.R;
import com.parreira.proj1.database.Nacionalidade;
import com.parreira.proj1.database.PessoaDatabase;

/**
 * Created by João Parreira on 4/3/2019.
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
 */
public class SecondActivity extends AppCompatActivity {

    public static final String KEY_PESSOA = "pessoa";
    private final String TAG = SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Pessoa pessoa = (Pessoa) getIntent().getExtras().get(KEY_PESSOA);

        TextView nome = (TextView) findViewById(R.id.tv_name2);
        TextView text = (TextView) findViewById(R.id.tv_text2);
        TextView nacionalidade = (TextView) findViewById(R.id.tv_nacionalidade);

        Nacionalidade nac = PessoaDatabase.getAppDatabase(this).NacionalidadeDao().getNacionalidadeById(pessoa.getNacionalidade());

        nome.setText(pessoa.getNome());
        text.setText(pessoa.getTexto());
        nacionalidade.setText(nac.getNacionalidade());

        Log.d(TAG,pessoa.getNome());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
        case android.R.id.home:
        onBackPressed();}
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
