package com.example.catalogboardgame.DashboardAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.catalogboardgame.R;
import com.example.catalogboardgame.firebaseauth.RgisterAuth;
import com.example.catalogboardgame.user.EditUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EditAdmin extends AppCompatActivity {
    DatabaseReference databaseReference;
    EditText nama,pass,email;
    Button edit;
    String Skey,Snama,Semail,Spass;
    boolean valid=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_admin);

        nama=findViewById(R.id.editnamaadmin);
        email=findViewById(R.id.editemailadmin);
        pass=findViewById(R.id.editpasswordadmin);
        edit=findViewById(R.id.editadm);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Akuns");

        Intent intent=getIntent();
        Skey=intent.getStringExtra("idkeys");
        Semail=intent.getStringExtra("idemails");
        Spass=intent.getStringExtra("idpasss");
        Snama=intent.getStringExtra("idnamas");

        nama.setText(Snama);
        email.setText(Semail);
        pass.setText(Spass);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: "+Semail);
                Log.d("TAG", "onClick: "+Skey);
                    UpdateData();


            }
        });



    }

    private void UpdateData() {
//        checkField(nama);
//        checkField(pass);


        AlertDialog.Builder builder = new AlertDialog.Builder(EditAdmin.this);
        builder.setTitle("Edit Akun");
        builder.setMessage("Apakah kamu yakin akan mengedit akun?");
        builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (nama.getText().toString().isEmpty()==false && pass.getText().toString().isEmpty()==false) {
                    if (pass.getText().toString().length() <7)
                        pass.setError("Password Minimal 7 Huruf");
                    else {
                        String nam = nama.getText().toString();
                        String mail = email.getText().toString();
                        String password = pass.getText().toString();
                        HashMap hashMap = new HashMap();
                        hashMap.put("nama", nam);
                        hashMap.put("email", mail);
                        hashMap.put("password", password);
                        databaseReference.child(Skey).updateChildren(hashMap);
                        Toast.makeText(EditAdmin.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (nama.getText().toString().isEmpty()==false && pass.getText().toString().isEmpty()== true){
                    pass.setError("Passsword Tidak Boleh Kosong");

                }
                else if (nama.getText().toString().isEmpty()==true && pass.getText().toString().isEmpty()== false){
                    nama.setError("Nama Tidak Boleh Kosong");

                }
                else if (nama.getText().toString().isEmpty()==true && pass.getText().toString().isEmpty()== true){
                    nama.setError("Nama Tidak Boleh Kosong");
                    pass.setError("Password Tidak Boleh Kosong");

                }

            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();



    }
//
//    private boolean checkField(EditText textField) {
//        if (textField.getText().toString().isEmpty()){
//            textField.setError("Data Tidak Boleh Kosong");
//            valid=false;
//        }
//        else{
//            valid=true;
//        }
//        return valid;
//    }
}