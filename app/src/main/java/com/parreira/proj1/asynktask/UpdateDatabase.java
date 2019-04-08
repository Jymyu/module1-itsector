package com.parreira.proj1.asynktask;

import android.os.AsyncTask;
import android.util.Log;

import com.parreira.proj1.activity.MainActivity;
import com.parreira.proj1.activity.Pessoa;
import com.parreira.proj1.database.PessoaDatabase;

import java.util.Arrays;
import java.util.List;

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
    List<Pessoa> mlist;
    public UpdateDatabase(PessoaDatabase pessoaDatabase,List<Pessoa> list) {
        this.mpessoaDatabase = pessoaDatabase;
        this.mlist = list;
    }


    @Override
    protected String doInBackground(Pessoa... pessoas) {

        mpessoaDatabase.daoAcess ().insertAll(mlist);
        Log.d("aSynkTask","executada");

        return null;
    }
}
