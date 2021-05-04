package com.example.messagemailcall;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MessageActivity extends AppCompatActivity {
    EditText phoneno,message;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        phoneno = findViewById(R.id.phoneNo);
        message = findViewById(R.id.message);
        send = findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                    sendSms();
                    Intent intent = new Intent(MessageActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                else {
                    requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                }
            }

            }
        });
    }

    private void sendSms(){
        String phoneNo = phoneno.getText().toString().trim();
        String messageTxt = message.getText().toString().trim();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo,null,messageTxt,null,null);
            Toast.makeText(this,"Message has been Sent",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Toast.makeText(this,"Failed Sending the Message",Toast.LENGTH_SHORT).show();
        }

    }
}