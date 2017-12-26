package com.dev.kiko.myinvoice.manager.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by kikogoinhas on 17/12/2017.
 */

public class InvoiceIntentService extends IntentService {

    public InvoiceIntentService() {
        super(InvoiceIntentService.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
