package com.example.myreceiverandroid.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceiver";
    private String message = "";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if(action.equals(Intent.ACTION_BOOT_COMPLETED)){

        }

        if(Intent.ACTION_INPUT_METHOD_CHANGED == action){

        }

        if(action.equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)){
            Bundle bundle = intent.getExtras();
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage[] sms = new SmsMessage[pdus.length];

            for(int i = 0; i < sms.length; i++){
                sms[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                message = "Mensaje: " + sms[i].getOriginatingAddress() + "\n" + sms[i].getMessageBody().toString();
            }

            Log.d(TAG, message);
        }

        Log.d(TAG, action);
        Toast.makeText(context.getApplicationContext(), action, Toast.LENGTH_SHORT).show();
        Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT).show();

        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("Action: " + intent.getAction() + "\n");
        strBuilder.append("URI: " + intent.toUri(Intent.URI_INTENT_SCHEME).toString() + "\n");
        String log = strBuilder.toString();
        Log.d(TAG, log);
        Toast.makeText(context, log, Toast.LENGTH_LONG).show();
    }
}
