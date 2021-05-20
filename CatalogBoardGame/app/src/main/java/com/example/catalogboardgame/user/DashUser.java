package com.example.catalogboardgame.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.catalogboardgame.DaftarGameClient.DaftarGameCLientActiviti;
import com.example.catalogboardgame.R;
import com.example.catalogboardgame.Sign.Login;

public class DashUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_user);
    }

    public void DAFTARGAME(View view) {
        Intent intent=new Intent(DashUser.this, DaftarGameCLientActiviti.class);
        startActivity(intent);

    }

    public void Historyuser(View view) {
    }
}