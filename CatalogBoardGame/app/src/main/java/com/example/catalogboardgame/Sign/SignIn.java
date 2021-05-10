package com.example.catalogboardgame.Sign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.catalogboardgame.DaftarGameClient.DaftarGameClientActivity;
import com.example.catalogboardgame.DashboardAdmin.dbAdmin;
import com.example.catalogboardgame.R;

public class SignIn extends AppCompatActivity {

    Button ss,sd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ss=findViewById(R.id.ss);
        sd=findViewById(R.id.sd);

        ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignIn.this, dbAdmin.class);
                startActivity(intent);

            }
        });
    }
//
//    public void Login(View view) {
//    }
//
//    public void Register(View view) {
//        Intent intent = new Intent(SignIn.this,SignUp.class);
//        startActivity(intent);
//    }
}