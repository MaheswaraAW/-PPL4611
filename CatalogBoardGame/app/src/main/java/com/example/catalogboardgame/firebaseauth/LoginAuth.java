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
import com.example.catalogboardgame.user.DashUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginAuth extends AppCompatActivity {

    EditText email,password;
    Button login,signup;
    boolean valid=true;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference1;
            //= FirebaseDatabase.getInstance().getReference();
    Akunfirebase akunfirebase;

    ArrayAdapter arrayAdapter;
    ArrayList<String> arrayTampil=new ArrayList<String>();

    String UId,nM;
//    ArrayList<String> arrayEdit=new ArrayList<>();
//    ArrayList<String> arrayHapus=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_auth);


        email=findViewById(R.id.emailuserss);
        password=findViewById(R.id.passworduserss);
        login=findViewById(R.id.loginsss);
        signup=findViewById(R.id.signupsss);


        firebaseAuth=FirebaseAuth.getInstance();




        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkField(email);
                checkField(password);

                if (valid) {
                    firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    Toast.makeText(LoginAuth.this, "Berhasil Masuk", Toast.LENGTH_SHORT).show();

//                                    checkuserAccesLevel(authResult.getUser().getUid());
                                    UId=authResult.getUser().getUid();
                                    Log.d("TAG", "onCreateaaaa: "+UId);
                                    DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
                                    DatabaseReference reference=databaseReference.child("Akuns").child(UId).child("manag");
                                    String a=reference.getDatabase().toString();
                                    String as=reference.toString();
                                    Log.d("TAG", "onSuccess: "+a);
                                    Query akunfirebase= reference.orderByChild(UId);
                                    Log.d("TAG", "checkuserAccesLevel: "+reference);
                                    databaseReference1=FirebaseDatabase.getInstance().getReference().child("Akuns").child(UId);
                                    databaseReference1.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            String manag= snapshot.toString();

                                            String s="sadsad";
                                            String sa;
                                            arrayTampil.add((String) snapshot.child("manag").getValue());


                                            Log.d("TAG", "onDataChange: "+manag);
                                            Log.d("TAG", "onDataChange: "+arrayTampil);
                                            String d= arrayTampil.toString();

                                            Log.d("TAG", "onDataChange: "+d);
                                            StringBuffer stringBuilder=new StringBuffer();

                                            for (String k: arrayTampil)
                                            {
                                                stringBuilder.append(k);
                                                stringBuilder.append("");
                                            }
                                            String l=stringBuilder.toString();
                                            Log.d("TAG", "onDataChange: "+l);


                                            if (l.equals("1"))
                                            {
                                                Toast.makeText(LoginAuth.this, "User", Toast.LENGTH_LONG).show();

                                                Intent intent = new Intent(LoginAuth.this, DashUser.class);
                                                startActivity(intent);

                                            }
                                            else if (l.equals("0")){
                                                Toast.makeText(LoginAuth.this, "ADM", Toast.LENGTH_LONG).show();

                                                Intent intents = new Intent(LoginAuth.this, DashAdmin.class);
                                                startActivity(intents);

                                            }

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

//                                    akunfirebase.addListenerForSingleValueEvent(new ValueEventListener() {
//                                        @Override
//                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                            if (snapshot.exists())
//                                            {
//                                                for (DataSnapshot user : snapshot.getChildren()) {
//                                                    Akunfirebase Akn = user.getValue(Akunfirebase.class);
//                                                        if (Akn.getUIDa().equals(UId)) {
//                                                            Log.d("TAG", "UIDNsYA: " + Akn.getUIDa());
//
//                                                            if (Akn.getManaga().equals("1")) {
//                                                                Toast.makeText(LoginAuth.this, "User", Toast.LENGTH_LONG).show();
//
//                                                                Intent intent = new Intent(LoginAuth.this, DashUser.class);
//                                                                startActivity(intent);
//                                                            } else if (Akn.getManaga().equals("0")) {
//                                                                Toast.makeText(LoginAuth.this, "ADM", Toast.LENGTH_LONG).show();
//
//                                                                Intent intents = new Intent(LoginAuth.this, DashAdmin.class);
//                                                                startActivity(intents);
//                                                            }
//                                                        } else {
//
//                                                        }
//
//                                                }
//                                            }
//                                        }
//
//                                        @Override
//                                        public void onCancelled(@NonNull DatabaseError error) {
//
//                                        }
//                                    });



//                                    Intent intent = new Intent(LoginAuth.this, DashUser.class);
//                                    startActivity(intent);


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginAuth.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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


//    private void checkuserAccesLevel(final String uid) {
//
//        Log.d("TAG","UIDNYA: " + uid);
//        String as=uid;
////        Query query= databaseReference1.orderByChild("uid");
////        Log.d("TAG","UIDNYA: " + query);
//        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
//        DatabaseReference reference=databaseReference.child("Akuns").child(uid);
//        Log.d("TAG", "checkuserAccesLevel: "+reference);
////        reference.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                Akunfirebase akunfirebasea=snapshot.getValue(Akunfirebase.class);
////                Akunfirebase akunfirebase1  =new Akunfirebase();
////                String nama=akunfirebasea.getNamaa();
////                String email=akunfirebasea.getEmaila();
////                String pass=akunfirebasea.getPassworda();
////                String uid=akunfirebasea.getUIDa();
////                String id=akunfirebasea.getIDa();
////
////                akunfirebase1.setNamaa(nama);
////                akunfirebase1.setUIDa(uid);
////                akunfirebase1.setIDa(id);
////                akunfirebase1.setPassworda(pass);
////                akunfirebase1.setEmaila(email);
////
////                arrayTampil.add(akunfirebase1);
////
////
////                Log.d("TAG", "onDataChange: "+arrayTampil);
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////                Log.d("TAG", "onCancelled: "+error.getCode());
////
////            }
////        });
//
//
//
//
//        //        ValueEventListener eventListener= new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                for (DataSnapshot dataSnapshot: snapshot.getChildren())
////                {
////                    for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
////                    {
////
////                        Akunfirebase akunfirebase=dataSnapshot1.getValue(Akunfirebase.class);
////                        Log.d("TAG", "for 2: "+akunfirebase.getNamaa());
////                    }
////                }
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////
////            }
////        };
////        reference.addListenerForSingleValueEvent(eventListener);
//
//
////        query.addListenerForSingleValueEvent(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                if (snapshot.exists()) {
////
////                    for (DataSnapshot user : snapshot.getChildren()) {
////                        Akunfirebase Akn = user.getValue(Akunfirebase.class);
////                        if (Akn.getUIDa().equals(uid)) {
////                            Log.d("TAG", "UIDNsYA: " + Akn.getUIDa());
////
////                            if (Akn.getManaga().equals("1")) {
////                                Toast.makeText(LoginAuth.this, "User", Toast.LENGTH_LONG).show();
////
////                                Intent intent = new Intent(LoginAuth.this, DashUser.class);
////                                startActivity(intent);
//////                            } else if (Akn.getManag().equals("0")) {
//////                                Toast.makeText(LoginAuth.this, "ADM", Toast.LENGTH_LONG).show();
//////
//////                                Intent intents = new Intent(LoginAuth.this, DashAdmin.class);
//////                                startActivity(intents);
////                            }
////                        } else {
////
////                        }
////                    }
////                }
////            }
////
////            @Override
////            public void onCancelled(@NonNull DatabaseError error) {
////
////            }
////        });
//
//
//
//
////        databaseReference = FirebaseDatabase.getInstance().getReference().child("Akuns");
//////        arrayAdapter= new ArrayAdapter()
//
////        databaseReference.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
////            @Override
////            public void onSuccess(DataSnapshot dataSnapshot) {
////                Log.d("TAG", "onSuccess: "+dataSnapshot.getValue());
////
////                Query query=dataSnapshot.
////
////            }
////        });
//
//
//      /*  Query a= databaseReference.orderByChild("manag");
//        Log.d("TAG","onSuccess: " + a);
//        a.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//
//                    Akunfirebase af= snapshot.getValue(Akunfirebase.class);
//                    if (af.getManag().equals("0")){
//                        Toast.makeText(LoginAuth.this,"Admin", Toast.LENGTH_SHORT).show();
//
//                        startActivity(new Intent(getApplicationContext(),DashAdmin.class));
//
//                    }else if (af.getManag().equals("1")){
//                        Toast.makeText(LoginAuth.this,"User", Toast.LENGTH_SHORT).show();
//
//                        startActivity(new Intent(getApplicationContext(),DashUser.class));
//
//                    }
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//*/
////        if (a == "0"){
////            String b= "ini admin";
////            Log.d("TAG","onSuccess: " + b);
////            Toast.makeText(LoginAuth.this,"Admin", Toast.LENGTH_SHORT).show();
////
////            startActivity(new Intent(getApplicationContext(),DashAdmin.class));
////            finish();
////
////        }else if (a == "1")
////        {
////            String b= "ini user";
////            Log.d("TAG","onSuccess: " + b);
////
////            Toast.makeText(LoginAuth.this,"User", Toast.LENGTH_SHORT).show();
////
////            startActivity(new Intent(getApplicationContext(), DashUser.class));
////            finish();
////        }
//
//
////        databaseReference.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
////            @Override
////            public void onSuccess(DataSnapshot dataSnapshot) {
////                Log.d("TAG","onSuccess: " + dataSnapshot.getValue());
//
////                String a= dataSnapshot.getValue(Akunfirebase.class).getManag();
////                Log.d("TAG","onSuccess: " + a);
////
////                if (a == "0"){
////                    String b= "ini admin";
////                    Log.d("TAG","onSuccess: " + b);
////                    Toast.makeText(LoginAuth.this,"Admin", Toast.LENGTH_SHORT).show();
////
////                    startActivity(new Intent(getApplicationContext(),DashAdmin.class));
////                    finish();
////
////                }else if (a == "1")
////                {
////                    String b= "ini user";
////                    Log.d("TAG","onSuccess: " + b);
////
////                    Toast.makeText(LoginAuth.this,"User", Toast.LENGTH_SHORT).show();
////
////                    startActivity(new Intent(getApplicationContext(), DashUser.class));
////                    finish();
////                }
//
//
////                Query query= databaseReference.orderByChild("manag").equalTo("0");
//
////                startActivity(new Intent(getApplicationContext(), DashAdmin.class));
////                finish();
////
////            }
////        });
//
//    }
//

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
//        if (FirebaseAuth.getInstance().getCurrentUser() == null){
//            startActivity(new Intent(getApplicationContext(), DashUser.class));
//            finish();
//        }
//    }
}