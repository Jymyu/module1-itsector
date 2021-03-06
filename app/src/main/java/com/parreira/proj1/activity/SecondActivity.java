package com.parreira.proj1.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.parreira.proj1.R;
import com.parreira.proj1.broadcast.ChargingBroadcastReceiver;
import com.parreira.proj1.database.Nacionalidade;
import com.parreira.proj1.database.PessoaDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

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
    private CircleImageView profileImage;

    private Pessoa pessoa;
    private static ImageView chargingImage;

    IntentFilter mChargingIntentFilter;
    BroadcastReceiver mChargingReciver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        pessoa = (Pessoa) getIntent().getExtras().get(KEY_PESSOA);

        TextView nome = (TextView) findViewById(R.id.tv_name2);
        TextView text = (TextView) findViewById(R.id.tv_text2);
        TextView nacionalidade = (TextView) findViewById(R.id.tv_nacionalidade);
        profileImage = findViewById(R.id.img_profile_image2);
        chargingImage = findViewById(R.id.charging_image);

        Nacionalidade nac = PessoaDatabase.getAppDatabase(this).NacionalidadeDao().getNacionalidadeById(pessoa.getNacionalidade());

        nome.setText(pessoa.getNome());
        text.setText(pessoa.getTexto());
        nacionalidade.setText(nac.getNacionalidade());
        Picasso.get().load(pessoa.getImage()).into(profileImage);

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");


                // String[] mimeTypes = {"image/jpeg", "image/png"};
                String[] mimeTypes = {"image/jpeg", "image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);

                startActivityForResult(intent, 1);

            }
        });

        Log.d(TAG, pessoa.getNome());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mChargingIntentFilter = new IntentFilter();
        mChargingIntentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        mChargingIntentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        mChargingReciver = new ChargingBroadcastReceiver();
        registerReceiver(mChargingReciver,mChargingIntentFilter);
    }
    //set up icon off charger

    public static void showCharging(Boolean isCharging) {
        if (isCharging) {
            chargingImage.setImageResource(R.drawable.ic_poweron);
        } else {
            chargingImage.setImageResource(R.drawable.ic_poweroff);
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case 1:
                    //data.getData returns the content URI for the selected Image
                    Uri selectedImage = data.getData();

                    profileImage.setImageURI(selectedImage);

                    pessoa.setImage(selectedImage.toString());
                    // PessoaDatabase.getAppDatabase(SecondActivity.this).daoAcess().updatePessoa(pessoa);
                    break;
            }

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(mChargingReciver,mChargingIntentFilter);

        BatteryManager batteryManager = (BatteryManager) getSystemService(BATTERY_SERVICE);
        boolean isCharging = batteryManager.isCharging();
        showCharging(isCharging);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(mChargingReciver);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
