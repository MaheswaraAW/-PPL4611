package com.example.catalogboardgame.DashboardAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.catalogboardgame.DaftarGameClient.DaftarGameClientActivity;
import com.example.catalogboardgame.R;
import com.example.catalogboardgame.Sign.SignUp;

public class dbAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_admin);


    }

    public void DAFTARGAME(View view) {
        Intent intent=new Intent(dbAdmin.this, DaftarGameClientActivity.class);
        startActivity(intent);
    }

    public void Historyadmin(View view) {
    }
}