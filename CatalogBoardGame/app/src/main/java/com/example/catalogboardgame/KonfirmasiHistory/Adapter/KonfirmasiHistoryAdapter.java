package com.example.catalogboardgame.KonfirmasiHistory.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.catalogboardgame.KonfirmasiHistory.KonfirmasiHistory;
import com.example.catalogboardgame.LeaderboardClient.Adapter.LeaderboardClientAdapter;
import com.example.catalogboardgame.R;
import com.example.catalogboardgame.TambahHistoryClient.TambahHistoryClient;
import com.example.catalogboardgame.model.History;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;


public class KonfirmasiHistoryAdapter extends RecyclerView.Adapter<KonfirmasiHistoryAdapter.KonfirmasiHistoryViewHolder>{
    private Context context;
    private ArrayList<History> ALHistory;
//    private ArrayList<String> ALKey;
    private ArrayList<String> ALGambarGame;

//    Integer no=1;

//    public KonfirmasiHistoryAdapter(Context context, ArrayList<History> ALHistory, ArrayList<String> ALKey, ArrayList<String> ALGambarGame){
    public KonfirmasiHistoryAdapter(Context context, ArrayList<History> ALHistory, ArrayList<String> ALGambarGame){
        this.context=context;
        this.ALHistory=ALHistory;
//        this.ALKey=ALKey;
        this.ALGambarGame=ALGambarGame;
    }

    public class KonfirmasiHistoryViewHolder extends RecyclerView.ViewHolder {
        TextView TVNo,TVName, TVWin, TVLose;
        ImageView IVGambarGame, IVDelete;

        public KonfirmasiHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            TVNo = itemView.findViewById(R.id.idTVNo);
            TVName = itemView.findViewById(R.id.idTVName);
            TVWin = itemView.findViewById(R.id.idTVWin);
            TVLose = itemView.findViewById(R.id.idTVLose);
            IVGambarGame = itemView.findViewById(R.id.idIVGambarGame);
            IVDelete = itemView.findViewById(R.id.idIVDelete);
        }
    }

    @NonNull
    @Override
    public KonfirmasiHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View VItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_konfirmasi_history, parent, false);
        return new KonfirmasiHistoryAdapter.KonfirmasiHistoryViewHolder(VItem);
    }

    @Override
    public void onBindViewHolder(@NonNull KonfirmasiHistoryViewHolder holder, int position) {
//        holder.TVNo.setText(Integer.toString(no));
        holder.TVName.setText(ALHistory.get(position).getName());
        holder.TVWin.setText(Integer.toString(ALHistory.get(position).getWin()));
        holder.TVLose.setText(Integer.toString(ALHistory.get(position).getLose()));
        Glide.with(context).load(ALHistory.get(position).getGambarGame()).into(holder.IVGambarGame);
//        no++;
        final String gambargamep = ALGambarGame.get(position);
        holder.IVDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Hapus History");
                builder.setMessage("Apakah kamu yakin hapus history?");
                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(context, "2:"+gambargamep, Toast.LENGTH_SHORT).show();
                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("History");
                        final StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(gambargamep);
                        storageReference.delete();
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                if(snapshot.getKey().equals(ALKey)) {
                                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                    String gambarGame = dataSnapshot.getValue(History.class).getGambarGame();
//                                    String key = dataSnapshot.getKey();

//                                    if(gambarGame.equals(gambargamep)&&key.equals(ALKey)){
                                    if(gambarGame.equals(gambargamep)){
                                     dataSnapshot.getRef().removeValue();
                                    }
//                                Toast.makeText(context, "key:"+key+"gambar:"+gambarGame, Toast.LENGTH_SHORT).show();
                                }
                            }


                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
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
        });
    }

    @Override
    public int getItemCount() {
        return ALHistory.size();
    }


}
