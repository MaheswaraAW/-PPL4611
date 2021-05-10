package com.example.catalogboardgame.Sign;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.catalogboardgame.DaftarGameClient.DaftarGameClientActivity;
import com.example.catalogboardgame.DashboardAdmin.dbAdmin;
import com.example.catalogboardgame.R;

import com.example.catalogboardgame.model.Akun;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    Button sin;
    Button regis;
    EditText email, name,password;
    Akun akun;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Akun");
    String ad="1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

         sin= findViewById(R.id.logs);
         regis= findViewById(R.id.sa);

//        email=findViewById(R.id.regemailuser);
//        name =findViewById(R.id.regnamauser);
//        password=findViewById(R.id.regpassworduser);

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUp.this, dbAdmin.class);
                startActivity(intent);

            }
        });
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                        akun.setNama(name.getText().toString());
////                        akun.setEmail(email.getText().toString());
////                        akun.setPassword(password.getText().toString());
////                        akun.setManag(ad);
////                        databaseReference.push().setValue(akun);
//                Toast.makeText(SignUp.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
//
//
//            }
//        });
//        sign.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(SignUp.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(SignUp.this,SignIn.class);
//                startActivity(intent);
//            }
//        });
    }
//
//    public void login(View view) {
//        Intent intent = new Intent(SignUp.this,dbAdmin.class);
//        startActivity(intent);
//
//    }
//
//    public void register(View view) {
//    }
}