package com.parreira.proj1.asynktask;

import android.os.AsyncTask;

import com.parreira.proj1.activity.MainActivity;
import com.parreira.proj1.activity.Pessoa;
import com.parreira.proj1.database.PessoaDatabase;

/**
 * Created by João Parreira on 4/5/2019.
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
public class UpdateDatabase extends AsyncTask<Pessoa,Integer,String>{

    PessoaDatabase mpessoaDatabase;
    public UpdateDatabase(PessoaDatabase pessoaDatabase) {
        this.mpessoaDatabase = pessoaDatabase;
    }

    @Override
    protected String doInBackground(Pessoa... pessoa){

            Pessoa pessoa1 = pessoa[0];
            mpessoaDatabase.daoAcess ().insertPessoa(pessoa1);
        return "finished asynktask";
    }
}
