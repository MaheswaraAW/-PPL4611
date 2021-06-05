package com.example.catalogboardgame.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.catalogboardgame.DaftarGameClient.DaftarGameCLientActiviti;
import com.example.catalogboardgame.R;
import com.example.catalogboardgame.Sign.Login;
import com.example.catalogboardgame.firebaseauth.LoginAuth;
import com.google.firebase.auth.FirebaseAuth;

public class DashUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_user);
    }

    public void DAFTARGAME(View view) {
        Intent intent=new Intent(DashUser.this, DaftarGameCLientActiviti.class);
        startActivity(intent);

    }

    public void Historyuser(View view) {
    }
//    public void onBackPressed() {
//        Intent startMain = new Intent(Intent.ACTION_MAIN);
//        startMain.addCategory(Intent.CATEGORY_HOME);
//        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(startMain);
//        //klik back nanti ke home
//    }

    public void LogOut(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginAuth.class));
//        Intent intent=new Intent(DashUser.this, Login.class);
//        startActivity(intent);
        finish();

    }
}