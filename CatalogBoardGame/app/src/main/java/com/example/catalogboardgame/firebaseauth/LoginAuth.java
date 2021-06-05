package com.example.catalogboardgame.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.catalogboardgame.DashboardAdmin.DashAdmin;
import com.example.catalogboardgame.R;
import com.example.catalogboardgame.model.akn;
import com.example.catalogboardgame.user.DashUser;
import com.example.catalogboardgame.user.DashboardUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginAuth extends AppCompatActivity {

    EditText email,password;
    Button login,signup;
    boolean valid=true;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
            //= FirebaseDatabase.getInstance().getReference().child("Akuns");
    Akunfirebase akunfirebase;

    ArrayAdapter arrayAdapter;
    ArrayList<String> arrayTampil=new ArrayList<>();
    ArrayList<String> arrayEdit=new ArrayList<>();
    ArrayList<String> arrayHapus=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_auth);

        firebaseAuth=FirebaseAuth.getInstance();
        akunfirebase= new Akunfirebase();

        email=findViewById(R.id.emailusers);
        password=findViewById(R.id.passwordusers);
        login=findViewById(R.id.loginss);
        signup=findViewById(R.id.signupss);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkField(email);
                checkField(password);
                if (valid){
                    firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(LoginAuth.this,"Berhasil Masuk", Toast.LENGTH_SHORT).show();
                            checkuserAccesLevel(authResult.getUser().getUid());

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginAuth.this,e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RgisterAuth.class));

            }
        });

    }

    private void checkuserAccesLevel(String uid) {

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Akuns").child(uid);
        Log.d("TAG","UIDNYA: " + uid);
        Query a= databaseReference.orderByChild("manag");
        Log.d("TAG","onSuccess: " + a);
        a.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                    Akunfirebase af= snapshot.getValue(Akunfirebase.class);
                    if (af.getManag().equals("0")){
                        Toast.makeText(LoginAuth.this,"Admin", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(getApplicationContext(),DashAdmin.class));

                    }else if (af.getManag().equals("1")){
                        Toast.makeText(LoginAuth.this,"User", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(getApplicationContext(),DashUser.class));

                    }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        if (a == "0"){
//            String b= "ini admin";
//            Log.d("TAG","onSuccess: " + b);
//            Toast.makeText(LoginAuth.this,"Admin", Toast.LENGTH_SHORT).show();
//
//            startActivity(new Intent(getApplicationContext(),DashAdmin.class));
//            finish();
//
//        }else if (a == "1")
//        {
//            String b= "ini user";
//            Log.d("TAG","onSuccess: " + b);
//
//            Toast.makeText(LoginAuth.this,"User", Toast.LENGTH_SHORT).show();
//
//            startActivity(new Intent(getApplicationContext(), DashUser.class));
//            finish();
//        }


//        databaseReference.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
//            @Override
//            public void onSuccess(DataSnapshot dataSnapshot) {
//                Log.d("TAG","onSuccess: " + dataSnapshot.getValue());

//                String a= dataSnapshot.getValue(Akunfirebase.class).getManag();
//                Log.d("TAG","onSuccess: " + a);
//
//                if (a == "0"){
//                    String b= "ini admin";
//                    Log.d("TAG","onSuccess: " + b);
//                    Toast.makeText(LoginAuth.this,"Admin", Toast.LENGTH_SHORT).show();
//
//                    startActivity(new Intent(getApplicationContext(),DashAdmin.class));
//                    finish();
//
//                }else if (a == "1")
//                {
//                    String b= "ini user";
//                    Log.d("TAG","onSuccess: " + b);
//
//                    Toast.makeText(LoginAuth.this,"User", Toast.LENGTH_SHORT).show();
//
//                    startActivity(new Intent(getApplicationContext(), DashUser.class));
//                    finish();
//                }


//                Query query= databaseReference.orderByChild("manag").equalTo("0");

//                startActivity(new Intent(getApplicationContext(), DashAdmin.class));
//                finish();
//
//            }
//        });

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

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (FirebaseAuth.getInstance().getCurrentUser() != null){
//            startActivity(new Intent(getApplicationContext(), DashUser.class));
//            finish();
//        }
//    }
}