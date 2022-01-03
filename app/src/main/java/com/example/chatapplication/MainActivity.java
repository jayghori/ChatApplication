package com.example.chatapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnSiri,btnAlexa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAlexa=findViewById(R.id.btnAlexa);
        btnSiri=findViewById(R.id.btnSiri);

        btnSiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ChatActivity.class);
                intent.putExtra("sender","Siri");
                intent.putExtra("receiver","Alexa");
                startActivity(intent);
            }
        });

        btnAlexa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,ChatActivity.class);
                intent.putExtra("sender","Alexa");
                intent.putExtra("receiver","Siri");
                startActivity(intent);

            }
        });
    }
}