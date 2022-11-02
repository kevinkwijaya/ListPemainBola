package com.kevin.pemainbola.Adapter;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kevin.pemainbola.DetailPemainActivity;
import com.kevin.pemainbola.Model.PemainModel;
import com.kevin.pemainbola.R;

import java.util.ArrayList;

public class ListPemainAdapter extends RecyclerView.Adapter<ListPemainAdapter.ListViewHolder> {
    Context c;

    private ArrayList<PemainModel> listPemain;

    public ListPemainAdapter(ArrayList<PemainModel> list, Context c) {
        this.listPemain = list;
        this.c = c;
    }

    private ArrayList<PemainModel> getlistPemain(){
        return listPemain;
    }

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_pemain, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {

        PemainModel pemain = listPemain.get(position);
        final String gambarpemain = pemain.getPhoto();
        final String namapemain = pemain.getName();
        final String frompemain = pemain.getFrom();
        final String despemain = pemain.getKeterangan();

        Glide.with(holder.itemView.getContext())
                .load(pemain.getPhoto())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.imgPhoto);
        holder.tvName.setText(pemain.getName());
        holder.tvKeterangan.setText(pemain.getKeterangan());
        holder.tvFrom.setText(pemain.getFrom());

        holder.imgPhoto.setImageURI(Uri.parse(gambarpemain));
        holder.tvName.setText(namapemain);
        holder.tvFrom.setText(frompemain);
        holder.tvKeterangan.setText(despemain);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listPemain.get(holder.getAdapterPosition()));
                OpenDetail(gambarpemain,namapemain,frompemain,despemain);
            }
        });
    }

    private void OpenDetail(String gambarpemain, String namapemain, String frompemain, String despemain) {
        Intent move = new Intent(c, DetailPemainActivity.class);
        move.putExtra("POTO",gambarpemain);
        move.putExtra("NAMA",namapemain);
        move.putExtra("ASAL",frompemain);
        move.putExtra("KET",despemain);
        c.startActivity(move);
    }


    @Override
    public int getItemCount() {
        return listPemain.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvFrom, tvKeterangan;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvKeterangan = itemView.findViewById(R.id.tv_item_des);
            tvFrom = itemView.findViewById(R.id.tv_item_from);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(PemainModel data);
    }

}

