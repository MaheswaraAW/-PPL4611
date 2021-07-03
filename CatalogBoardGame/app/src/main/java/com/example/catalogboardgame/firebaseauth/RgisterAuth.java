package com.example.catalogboardgame.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.catalogboardgame.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RgisterAuth extends AppCompatActivity {

    EditText email,name,password;
    Button Signup,Login;
    boolean valid=true;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Akuns");
    Akunfirebase akunfirebase;
    String manag="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rgister_auth);

        email=findViewById(R.id.regemailusers);
        name= findViewById(R.id.regnamausers);
        password=findViewById(R.id.regpasswordusers);
        Signup=findViewById(R.id.daftars);
        Login=findViewById(R.id.masuks);
        firebaseAuth=FirebaseAuth.getInstance();


        akunfirebase= new Akunfirebase();

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkField(name);
                checkField(email);
                checkField(password);
                if (valid){
                    firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {

                            FirebaseUser user= firebaseAuth.getCurrentUser();

                            akunfirebase.setNama(name.getText().toString());
                            akunfirebase.setEmail(email.getText().toString());
                            akunfirebase.setPassword(password.getText().toString());
                            akunfirebase.setManag(manag);
                            String ID=databaseReference.push().getKey();
                            akunfirebase.setID(ID);
                            akunfirebase.setUID(user.getUid());

                            Toast.makeText(RgisterAuth.this,"Akun berhasil dibuat",Toast.LENGTH_SHORT).show();

//                            Map<String ,Object> userInfo= new HashMap<>();
//                            userInfo.put("nama",name.getText().toString());
//                            userInfo.put("password",password.getText().toString());
//                            userInfo.put("e-mail",email.getText().toString());
//
//                            userInfo.put("isUser","0");
//                            databaseReference.setValue(userInfo);

                    databaseReference.child(user.getUid()).setValue(akunfirebase);
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getApplicationContext(),LoginAuth.class));
                    finish();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RgisterAuth.this,"Akun Gagal Dibuat",Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginAuth.class));
            }
        });

    }

    private boolean checkField(EditText textField) {
        if (textField.getText().toString().isEmpty()){
            textField.setError("ERROR");
            valid=false;
        }
        else{
            valid=true;
        }
        return valid;
    }
}