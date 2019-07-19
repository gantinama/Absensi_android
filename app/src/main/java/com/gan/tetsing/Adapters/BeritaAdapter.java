package com.gan.tetsing.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gan.tetsing.Models.Berita;
import com.gan.tetsing.R;

import java.util.List;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder> {
    List<Berita> listBerita;

    public class BeritaViewHolder extends RecyclerView.ViewHolder {
        public TextView listJudulBerita, listIsiBerita, listWaktuPosted;

        public BeritaViewHolder(@NonNull View itemView) {
            super(itemView);
            listJudulBerita = itemView.findViewById(R.id.listJudulBerita);
            listIsiBerita = itemView.findViewById(R.id.listIsiBerita);
            listWaktuPosted = itemView.findViewById(R.id.listWaktuPosted);
        }
    }

    public BeritaAdapter(List<Berita> listBerita) {
        this.listBerita = listBerita;
    }

    @NonNull
    @Override
    public BeritaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_berita, viewGroup,
                false);
        BeritaViewHolder beritaViewHolder = new BeritaViewHolder(mView);
        return beritaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final BeritaViewHolder viewHolder, int i) {
        viewHolder.listJudulBerita.setText(listBerita.get(i).getJudul_berita());
        viewHolder.listIsiBerita.setText(listBerita.get(i).getIsi_berita());
        viewHolder.listWaktuPosted.setText(listBerita.get(i).getWaktu_posted() + " No. " + listBerita.get(i).getId_berita());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(viewHolder.itemView.getContext(), "clicked on Row", Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBerita.size();
    }

}
