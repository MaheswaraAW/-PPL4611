package com.example.catalogboardgame.Sign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.catalogboardgame.DaftarGameClient.DaftarGameClientActivity;
import com.example.catalogboardgame.DashboardAdmin.dbAdmin;
import com.example.catalogboardgame.R;
import com.example.catalogboardgame.model.CatalogBoardGame;
import com.example.catalogboardgame.model.akn;
import com.example.catalogboardgame.user.DashboardUser;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Masuk extends AppCompatActivity {

    Button login,daftar;
    Button regis;
    EditText mail,password;
    akn akun;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Akun");
    String ad="1";
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk);

        mail=findViewById(R.id.emailuser);
        password=findViewById(R.id.passworduser);

        login= findViewById(R.id.ss);
        daftar= findViewById(R.id.sd);
        akun=new akn();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 final String input1=mail.getText().toString();
                 final String input2=password.getText().toString();
                final String input3="0";
                Query query= databaseReference.orderByChild("email").equalTo(input1);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists())
                        {
                            for (DataSnapshot user: snapshot.getChildren()){
                                akn Akn= user.getValue(akn.class);
                                if (Akn.getPassword().equals(input2)){
                                    if(Akn.getManag().equals("1")){
                                        Toast.makeText(Masuk.this, "1", Toast.LENGTH_LONG).show();

                                        Intent a=new Intent(Masuk.this, dbAdmin.class);
                                        startActivity(a);
                                    }
                                    else if (Akn.getManag().equals("0")){
                                        Intent intents=new Intent(Masuk.this, DashboardUser.class);
                                        startActivity(intents);
                                        Toast.makeText(Masuk.this, "2", Toast.LENGTH_LONG).show();

                                    }

                                }else
                                {
                                    Toast.makeText(Masuk.this, "Password salah", Toast.LENGTH_LONG).show();
                                }
                            }
                        }else {
                            Toast.makeText(Masuk.this, "Masukkan Username", Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




//                databaseReference.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
////                        String value=snapshot.getValue(akn.class);
////                        String mails=snapshot.child("email").getValue(akn.class);
////                        String pass=snapshot.child("password").getValue(akn.class);
////                        String manag=snapshot.child("manag").getValue(akn.class);
//                        final String input1=mail.getText().toString();
//                        final String input2=password.getText().toString();
//
//                        if (snapshot.child(input1).exists()){
//                            if (snapshot.child(input2).exists()){
//                                if (snapshot.child("manag").equals("1")){
//                                                                Intent intent=new Intent(Masuk.this, dbAdmin.class);
//                            startActivity(intent);
//
//                                }
//
//
//                            }
//                        }
//
//
////                        if (mails.equals(input1)&&pass.equals(input2)&&manag.equals("1")){
////                            Intent intent=new Intent(Masuk.this, dbAdmin.class);
////                            startActivity(intent);
////
////                        }else if (mails.equals(input1)&&pass.equals(input2)&&manag.equals("0"))
////                        {
////                            Intent intent=new Intent(Masuk.this, DashboardUser.class);
////                            startActivity(intent);
////                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//
//
////                Query check= databaseReference.orderByChild("email").equalTo(input2);
////
////                check.addListenerForSingleValueEvent(new ValueEventListener() {
////                    @Override
////                    public void onDataChange(@NonNull DataSnapshot snapshot) {
////                        if (snapshot.exists()){
////                            String passdb=snapshot.child(input1).child("password").getValue(String.class);
////                            if (passdb.equals(input2)){
////                                    Intent intent=new Intent(Masuk.this, dbAdmin.class);
////                                    startActivity(intent);
////                            }
////                        }
////                    }
////
////                    @Override
////                    public void onCancelled(@NonNull DatabaseError error) {
////
////                    }
////                });
//
//
//
////                databaseReference.child("Akun").addValueEventListener(new ValueEventListener() {
////                    @Override
////                    public void onDataChange(@NonNull DataSnapshot snapshot) {
////                        String input1=mail.getText().toString();
////                        String input2=password.getText().toString();
////                        Query check= databaseReference.orderByChild("email").equalTo(input1);
//////                        akn AKN= snapshot.getValue(akn.class);
//////                        String em=snapshot.child("email").getValue(String.class);
//////                        String ps=snapshot.child("password").getValue(String.class);
//////                        String mn=snapshot.child("manag").getValue(String.class);
//////
//////                        if (em.equals(input1)){
//////                            if(ps.equals(input2)){
//////                                if(mn.equals("1")){
//////                                    Intent intent=new Intent(Masuk.this, dbAdmin.class);
//////                                    startActivity(intent);
//////
//////                                }else{
//////                                    Intent intent=new Intent(Masuk.this, DashboardUser.class);
//////                                    startActivity(intent);
//////
//////                                }
//////                            }else {
//////                                Toast.makeText(Masuk.this,"Password Salah",Toast.LENGTH_SHORT).show();
//////                            }
//////                        }else {
//////                            Toast.makeText(Masuk.this,"Data Kosong",Toast.LENGTH_SHORT).show();
//////                        }
////
////                        if (snapshot.child(input1).exists()){
////                            if (snapshot.child("password").child("as").getValue(akn.class).equals(input2)){
////                                if (snapshot.child(input1).child("manag").getValue(akn.class).equals(("1"))){
////                                    Intent intent=new Intent(Masuk.this, dbAdmin.class);
////                                    startActivity(intent);
////                                }else if (snapshot.child(input1).child("manag").getValue(akn.class).equals(("0"))){
////                                    Intent intent=new Intent(Masuk.this, DashboardUser.class);
////                                    startActivity(intent);
////                                }
////
////                            }else {
////                                Toast.makeText(Masuk.this,"Password Kosong",Toast.LENGTH_SHORT).show();
////                            }
////                        }else {
////                            Toast.makeText(Masuk.this,"Data Kosong",Toast.LENGTH_SHORT).show();
////                        }
////                    }
////
////                    @Override
////                    public void onCancelled(@NonNull DatabaseError error) {
////
//                    }
//                });
            }
        });

    }
}