package com.parreira.proj1.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.parreira.proj1.activity.SecondActivity;

import static com.parreira.proj1.activity.SecondActivity.showCharging;


/**
 * Created by João Parreira on 4/11/2019.
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
public class ChargingBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Boolean isCharging = action.equals(Intent.ACTION_POWER_CONNECTED);
        showCharging (isCharging);
    }
}
