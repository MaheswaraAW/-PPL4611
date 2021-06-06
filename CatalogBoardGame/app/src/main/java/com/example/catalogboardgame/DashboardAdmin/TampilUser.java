package com.example.catalogboardgame.DashboardAdmin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.catalogboardgame.R;
import com.example.catalogboardgame.firebaseauth.Akunfirebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TampilUser extends AppCompatActivity {
    ListView listView;
    private static final String TAG = "TampilSemuaUser";

    Button add;
    ArrayAdapter arrayAdapter;
    ArrayList<String> arrayTampil=new ArrayList<>();
    ArrayList<String> arrayEdit=new ArrayList<>();
    ArrayList<String> arrayHapus=new ArrayList<>();

    DatabaseReference dreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_user);

        listView =findViewById(R.id.listdatas);
        add=findViewById(R.id.btmAdd);
        dreference= FirebaseDatabase.getInstance().getReference().child("Akuns");
        arrayAdapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arrayTampil);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String nama = arrayTampil.get(position);
                String email =arrayTampil.get(position);
                String UID =arrayTampil.get(position);

                String keyTarget = arrayEdit.get(position).toString().trim();

            }
        });
        dreference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d(TAG, "onChildChanged:" + snapshot.getKey());
                String hasil=snapshot.getValue(Akunfirebase.class).toPrinta();
                arrayTampil.add(hasil);
//                Mahasiswa mahasiswa=snapshot.getValue(Mahasiswa.class);
//                String nim=mahasiswa.nim;
//                String nama=mahasiswa.nama;


                String key= snapshot.getKey();


                arrayEdit.add(key);
                arrayHapus.add(key);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}