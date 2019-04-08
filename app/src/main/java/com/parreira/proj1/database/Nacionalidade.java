package com.parreira.proj1.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by João Parreira on 4/8/2019.
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

@Entity
public class Nacionalidade {

    @PrimaryKey
    @NonNull
    int id;

    public final String nacionalidade;


    public Nacionalidade(int id, String nacionalidade) {
        this.id = id;
        this.nacionalidade = nacionalidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public static Nacionalidade[] populateData() {
        return new Nacionalidade[] {
                new Nacionalidade(1, "Portugal"),
                new Nacionalidade(2, "Brazil"),
                new Nacionalidade(3, "Espanha")
        };
    }

}
