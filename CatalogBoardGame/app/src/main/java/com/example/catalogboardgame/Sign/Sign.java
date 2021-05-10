package com.example.catalogboardgame.Sign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.catalogboardgame.DaftarGameClient.DaftarGameClientActivity;
import com.example.catalogboardgame.DashboardAdmin.dbAdmin;
import com.example.catalogboardgame.MainActivity;
import com.example.catalogboardgame.R;
import com.example.catalogboardgame.model.Akun;
import com.example.catalogboardgame.model.akn;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sign extends AppCompatActivity {
    private static final String TAG = "TAG";

    Button a,b;
    Button regis;
    EditText email, name,password;
    akn akun;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Akun");
    String ad="1";
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    String USer,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        email=findViewById(R.id.regemailuser);
        name =findViewById(R.id.regnamauser);
        password=findViewById(R.id.regpassworduser);


        a= findViewById(R.id.masuk);
        b= findViewById(R.id.daftar);

        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        akun=new akn();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                akun.setNama(name.getText().toString());
                akun.setEmail(email.getText().toString());
                akun.setPassword(password.getText().toString());
                akun.setManag(ad);


                databaseReference.push().setValue(akun);
                Toast.makeText(Sign.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();


            }
        });

//        email=findViewById(R.id.regemailuser);
//        name =findViewById(R.id.regnamauser);
//        password=findViewById(R.id.regpassworduser);
//
//
//        a= findViewById(R.id.masuk);
//        b= findViewById(R.id.daftar);
//
//        firebaseAuth=firebaseAuth.getInstance();
//        firebaseDatabase=FirebaseDatabase.getInstance();
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String mail= email.getText().toString();
//                final String pass= password.getText().toString();
//                final String nama= name.getText().toString();
//                final String man= ad;
//
//
//                firebaseAuth.createUserWithEmailAndPassword(mail,pass)
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//
//                                if(task.isSuccessful()){
//                                    FirebaseUser fuser=firebaseAuth.getCurrentUser();
//                                    fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
//                                        @Override
//                                        public void onSuccess(Void aVoid) {
//                                            Toast.makeText(Sign.this, "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
//
//                                        }
//                                    }).addOnFailureListener(new OnFailureListener() {
//
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            Log.d(TAG, "onFailure: Email not sent " + e.getMessage());
//                                        }
//                                    });
//                                    Toast.makeText(Sign.this, "User Created.", Toast.LENGTH_SHORT).show();
//                                    USer=firebaseAuth.getCurrentUser().getUid();
//                                    databaseReference.
//
//                                    akn akun=new akn(nama,mail,pass,man);
//                                    String UID=task.getResult().getUser().getUid();
//                                    firebaseDatabase.getReference(UID).setValue(akun)
//                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                @Override
//                                                public void onSuccess(Void aVoid) {
//                                                    Intent intent= new Intent(Sign.this,Masuk.class);
//                                                    intent.putExtra("name", name + " " );
//                                                    startActivity(intent);
//                                                }
//                                            });
//                                }
//                                else {
//                                    Toast.makeText(Sign.this,"gagal",Toast.LENGTH_SHORT).show();
//
//                                }
//                            }
//                        });
//
//            }
//        });


    }
}