package com.parreira.proj1.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.parreira.proj1.activity.MainActivity;
import com.parreira.proj1.activity.Pessoa;

import java.util.concurrent.Executors;

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
@Database(entities = {Pessoa.class, Nacionalidade.class}, version = 1, exportSchema = true)

public abstract class PessoaDatabase extends RoomDatabase {

    private static PessoaDatabase INSTANCE;
    public abstract DaoAcess daoAcess();
    public abstract NacionalidadeDao NacionalidadeDao();

    public static PessoaDatabase getAppDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), PessoaDatabase.class, "pessoa_database")
                            .allowMainThreadQueries().addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                @Override
                                public void run() {
                                    getAppDatabase(context).NacionalidadeDao().insertNacionalidadeAll(Nacionalidade.populateData());
                                }
                            });
                        }
                    })
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
