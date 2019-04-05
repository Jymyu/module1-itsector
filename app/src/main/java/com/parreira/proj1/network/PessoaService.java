package com.parreira.proj1.network;

import com.parreira.proj1.activity.Pessoa;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

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
public interface PessoaService {

    @Headers({
            "JsonStub-User-Key: c8c5b1c8-d8fb-4c41-92a6-43dac8646042",
            "JsonStub-Project-Key: 7f581720-8447-457a-8cc4-2d865a9cbce4",
            "Content-Type: application/json"
    })

    @GET("/pessoas")
    Call<List<Pessoa>> getPessoas();

}
