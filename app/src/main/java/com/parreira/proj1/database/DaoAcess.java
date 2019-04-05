package com.parreira.proj1.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.parreira.proj1.activity.Pessoa;

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
@Dao
public interface DaoAcess {

    @Insert
    void insertPessoa (Pessoa pessoa);

    @Insert
    void insertMultiplasPessoas (List<Pessoa> listaPessoa);

    @Query ("SELECT * FROM Pessoa WHERE Pessoa.id = :pessoaID")
    Pessoa obterPessoa (int pessoaID);

    @Update
    void updatePessoa (Pessoa pessoa);

    @Delete
    void deletePessoa (Pessoa pessoa);


}
