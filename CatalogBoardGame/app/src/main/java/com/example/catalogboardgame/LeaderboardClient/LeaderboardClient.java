package com.example.catalogboardgame.LeaderboardClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.text.AllCapsTransformationMethod;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.catalogboardgame.BacaGameClient.BacaGameClients;
import com.example.catalogboardgame.LeaderboardClient.Adapter.LeaderboardClientAdapter;
import com.example.catalogboardgame.R;
import com.example.catalogboardgame.model.History;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class LeaderboardClient extends AppCompatActivity {
    ArrayList<History> ALHistory = new ArrayList<History>();
    RecyclerView RVLeaderboardClient;
    LeaderboardClientAdapter leaderboardClientAdapter;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    ImageView IVKembali;
    Button BDay, BMonth, BYear;
    TextView TVNamaGame;

    Context context;

    ArrayList<String> ALTanggal = new ArrayList<String>();
    ArrayList<Integer> ALDay = new ArrayList<Integer>();
    //    ArrayList<Integer> ALMonth = new ArrayList<Integer>();
    ArrayList<String> ALNamaD = new ArrayList<String>();
    ArrayList<String> ALSNama = new ArrayList<String>();
    ArrayList<String> ALNamaM = new ArrayList<String>();
//    ArrayList<String> ALNamaGame = new ArrayList<String>();

    History history= new History();

    String SNamaGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard_client);

        RVLeaderboardClient = findViewById(R.id.idRVLeaderboardClient);
        IVKembali = findViewById(R.id.idIVKembali);
        BDay = findViewById(R.id.idBDay);
        BMonth = findViewById(R.id.idBMonth);
        BYear = findViewById(R.id.idBYear);
        TVNamaGame = findViewById(R.id.idTVNamaGame);

        Intent intent = getIntent();
        SNamaGame = intent.getStringExtra("NamaGame");
        TVNamaGame.setText(SNamaGame);

//        Toast.makeText(this, "hari:"+SHariIni(), Toast.LENGTH_SHORT).show();

        IVKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        databaseReference.child("History").orderByChild("minDay").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ALHistory.clear();

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    History history = dataSnapshot.getValue(History.class);
                    String Tanggal = dataSnapshot.child("tanggal").getValue(String.class);
//                    Integer Day = dataSnapshot.child("day").getValue(Integer.class);
                    String NamaGame = dataSnapshot.child("namaGame").getValue(String.class);

//                    Toast.makeText(LeaderboardClient.this, "Nama:"+Nama, Toast.LENGTH_SHORT).show();
//                    if(Tanggal.equals(SHariIni())){
                    if(Tanggal.equals(SHariIni())&&NamaGame.equals(SNamaGame)){
//                        ALIDay.add(Day);
//                        ALNama.add(Nama);
//                        Collections.sort(ALIDay);//ascending value array
//                        Collections.reverse(ALIDay);
//                        Collections.sort(ALNama);
//                        ALTanggal.add(Tanggal);
//                        ALNamaGame.add(NamaGame);
                        ALHistory.add(history);
                    }
//                    Toast.makeText(LeaderboardClient.this, "sort:"+ALIDay, Toast.LENGTH_SHORT).show();
                }

//                Collections.sort(ALIDay);//ascending value array
//                Collections.reverse(ALIDay);//descending array bukan value array
//                Toast.makeText(LeaderboardClient.this, "sort:"+ALNama, Toast.LENGTH_SHORT).show();
//                Toast.makeText(LeaderboardClient.this, "sort:"+ALIDay, Toast.LENGTH_SHORT).show();
//                leaderboardClientAdapter = new LeaderboardClientAdapter(context, ALHistory);
//                leaderboardClientAdapter = new LeaderboardClientAdapter(context, ALHistory, ALIDay);
                leaderboardClientAdapter = new LeaderboardClientAdapter(context, ALHistory);
                RVLeaderboardClient.setAdapter(leaderboardClientAdapter);

                leaderboardClientAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        BDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LeaderboardClient.this, "This Day", Toast.LENGTH_SHORT).show();
                databaseReference.child("History").orderByChild("minDay").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ALHistory.clear();
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            History history = dataSnapshot.getValue(History.class);
                            String Tanggal = dataSnapshot.child("tanggal").getValue(String.class);
                            String NamaGame = dataSnapshot.child("namaGame").getValue(String.class);

                            if(Tanggal.equals(SHariIni())&&NamaGame.equals(SNamaGame)){
                                ALHistory.add(history);
                            }
                        }
                        leaderboardClientAdapter = new LeaderboardClientAdapter(context, ALHistory);
                        RVLeaderboardClient.setAdapter(leaderboardClientAdapter);

                        leaderboardClientAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        BMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LeaderboardClient.this, "This Month", Toast.LENGTH_SHORT).show();
                databaseReference.child("History").orderByChild("minMonth").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ALHistory.clear();
                        ALDay.clear();
                        ALNamaD.clear();
                        ALSNama.clear();
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            History history = dataSnapshot.getValue(History.class);
                            String Tanggal = dataSnapshot.child("tanggal").getValue(String.class);
                            Integer day = dataSnapshot.child("day").getValue(Integer.class);
                            String nama = dataSnapshot.child("name").getValue(String.class);
                            String Bulan = Tanggal.substring(3, 5);
                            String Bulanini = SHariIni().substring(3, 5);
                            String NamaGame = dataSnapshot.child("namaGame").getValue(String.class);
//                            Toast.makeText(LeaderboardClient.this, "tanggal:"+Tanggal+"Bulan:"+Bulan+"Bulan ini:"+Bulanini, Toast.LENGTH_SHORT).show();
                            if(Bulan.equals(Bulanini)&&NamaGame.equals(SNamaGame)){
                                ALHistory.add(history);
                                ALDay.add(day);
                                ALSNama.add(nama);
                            }
                            if(Tanggal.equals(SHariIni())&&NamaGame.equals(SNamaGame)){
                                ALNamaD.add(nama);
                            }
                            ALSNama.removeAll(ALNamaD);
//                            if(ALNama.equals(ALSNama)){
//                                ALSNama.removeIf(namas->namas.equals(ALNama));
//                                ALSNama.removeAll(ALNama);
//                                ALNamaBulan.add(nama);
//                            }
                        }
//                        Toast.makeText(LeaderboardClient.this, "ALSNama:"+ALSNama+"ALNamaD:"+ALNamaD, Toast.LENGTH_SHORT).show();

//                        leaderboardClientAdapter = new LeaderboardClientAdapter(context, ALHistory);
//                        leaderboardClientAdapter = new LeaderboardClientAdapter(context, ALHistory, ALDay, ALSNama);
                        leaderboardClientAdapter = new LeaderboardClientAdapter(context, ALHistory, ALSNama);
                        RVLeaderboardClient.setAdapter(leaderboardClientAdapter);

                        leaderboardClientAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        BYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LeaderboardClient.this, "This Year", Toast.LENGTH_SHORT).show();
                databaseReference.child("History").orderByChild("minYear").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ALHistory.clear();
                        ALDay.clear();
                        ALNamaD.clear();
                        ALSNama.clear();
                        ALNamaM.clear();
//                        ALMonth.clear();
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            History history = dataSnapshot.getValue(History.class);
                            String Tanggal = dataSnapshot.child("tanggal").getValue(String.class);
                            Integer day = dataSnapshot.child("day").getValue(Integer.class);
//                            Integer month = dataSnapshot.child("month").getValue(Integer.class);
                            String nama = dataSnapshot.child("name").getValue(String.class);
                            String Tahun = Tanggal.substring(6);
                            String Bulan = Tanggal.substring(3, 5);
                            String Tahunini = SHariIni().substring(6);
                            String Bulanini = SHariIni().substring(3, 5);
                            String NamaGame = dataSnapshot.child("namaGame").getValue(String.class);
//                            Toast.makeText(LeaderboardClient.this, "tanggal:"+Tanggal+"Tahun:"+Tahun+"Tahun ini:"+Tahunini, Toast.LENGTH_SHORT).show();

                            if(Tahun.equals(Tahunini)&&NamaGame.equals(SNamaGame)){
                                ALHistory.add(history);
                                ALDay.add(day);
//                                ALMonth.add(month);
                                ALSNama.add(nama);
                            }
                            if(Bulan.equals(Bulanini)&&NamaGame.equals(SNamaGame)){
                                ALNamaM.add(nama);
                            }
                            if(Tanggal.equals(SHariIni())&&NamaGame.equals(SNamaGame)){
                                ALNamaD.add(nama);
                            }
                            ALSNama.removeAll(ALNamaM);
                            ALNamaM.removeAll(ALNamaD);

                        }
//                        Toast.makeText(LeaderboardClient.this, "ALSNama"+ALSNama+"ALNamaD:"+ALNamaD+"ALNamaM:"+ALNamaM, Toast.LENGTH_SHORT).show();
//                        leaderboardClientAdapter = new LeaderboardClientAdapter(context, ALHistory);
//                        leaderboardClientAdapter = new LeaderboardClientAdapter(context, ALHistory, ALDay, ALSNama, ALNamaD);
                        leaderboardClientAdapter = new LeaderboardClientAdapter(context, ALHistory, ALSNama, ALNamaD, ALNamaM);
                        RVLeaderboardClient.setAdapter(leaderboardClientAdapter);

                        leaderboardClientAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    private String SHariIni(){
        return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
    }
}