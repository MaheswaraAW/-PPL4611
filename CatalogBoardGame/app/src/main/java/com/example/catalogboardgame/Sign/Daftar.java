package com.example.catalogboardgame.Sign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.catalogboardgame.R;
import com.example.catalogboardgame.model.akn;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Daftar extends AppCompatActivity {
    Button a,b;
    Button regis;
    EditText email, name,password;
    akn akun;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Akun");
    String ad="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        email=findViewById(R.id.regemailuser);
        name =findViewById(R.id.regnamauser);
        password=findViewById(R.id.regpassworduser);


        a= findViewById(R.id.masuk);
        b= findViewById(R.id.daftar);


        akun=new akn();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                akun.setNama(name.getText().toString());
                akun.setEmail(email.getText().toString());
                akun.setPassword(password.getText().toString());
                akun.setManag(ad);


                databaseReference.push().setValue(akun);
                Toast.makeText(Daftar.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();


            }
        });
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Daftar.this, Login.class);
                startActivity(intent);
            }
        });


    }
}