package com.parreira.proj1.activity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.parreira.proj1.database.Nacionalidade;

import java.io.Serializable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by João Parreira on 4/4/2019.
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
@Entity()
public class Pessoa implements Serializable {


    @PrimaryKey
    private Integer id;

    @ColumnInfo(name = "nome_pessoa")
    @NonNull
    private String nome;

    @ColumnInfo(name = "idade")
    private int idade;

    @ForeignKey(entity = Nacionalidade.class, parentColumns = "id",childColumns = "Nacionalidade", onDelete = CASCADE)

    @ColumnInfo(name = "Nacionalidade")
    int nacionalidade;

    @JsonProperty("descricao")
    private String texto;

    @JsonProperty("url-image")
    private String image;


    public Pessoa() {
    }

    public Pessoa(@NonNull Integer id, @NonNull String nome, int idade, int nacionalidade, String texto, String image) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.nacionalidade = nacionalidade;
        this.texto = texto;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(int nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
}
