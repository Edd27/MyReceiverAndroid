package com.example.myreceiverandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.myreceiverandroid.receivers.MyBroadcastReceiver;

public class MainActivity extends AppCompatActivity {

    MyBroadcastReceiver mbr;
    EditText txtNumber, txtMessage;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //RECEPTORES REGISTRADOS A NIVEL DE CONTEXTO
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BOOT_COMPLETED);
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        intentFilter.addAction("com.example.myreceiverandroid.MYDIFUSION");
        mbr = new MyBroadcastReceiver();
        registerReceiver(mbr, intentFilter);

        txtNumber = (EditText) findViewById(R.id.txtNumber);
        txtMessage = (EditText) findViewById(R.id.txtMessage);
        btnSend = (Button) findViewById(R.id.btnSend);

        btnSend.setOnClickListener(view -> sendMessage());

    }

    private void sendMessage(){
        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(txtNumber.getText().toString(), null, txtMessage.getText().toString(), null, null);
        Intent intent = new Intent("com.example.myreceiverandroid.MYDIFUSION");
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mbr);
    }
}