package com.parreira.proj1.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.parreira.proj1.activity.Pessoa;

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
@Database(entities = {Pessoa.class}, version = 1, exportSchema = false)

public abstract class PessoaDatabase extends RoomDatabase {

        public abstract DaoAcess daoAcess() ;

}
