package com.example.catalogboardgame.user;

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

import com.example.catalogboardgame.DashboardAdmin.EditAdmin;
import com.example.catalogboardgame.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class EditUser extends AppCompatActivity {

    DatabaseReference databaseReference;
    EditText nama,pass,email;
    Button edit;
    String Skey,Snama,Semail,Spass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        nama=findViewById(R.id.editnamausers);
        email=findViewById(R.id.editemailusers);
        pass=findViewById(R.id.editpasswordusers);
        edit=findViewById(R.id.edit);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Akuns");

        Intent intent=getIntent();
        Skey=intent.getStringExtra("idkey");
        Semail=intent.getStringExtra("idemail");
        Spass=intent.getStringExtra("idpass");
        Snama=intent.getStringExtra("idnama");

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

        AlertDialog.Builder builder = new AlertDialog.Builder(EditUser.this);
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
                        Toast.makeText(EditUser.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
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
}