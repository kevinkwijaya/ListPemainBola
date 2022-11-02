package com.kevin.pemainbola.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kevin.pemainbola.DetailPemainActivity;
import com.kevin.pemainbola.Model.PemainModel;
import com.kevin.pemainbola.R;

import java.util.ArrayList;

public class CardViewPemainAdapter extends RecyclerView.Adapter<CardViewPemainAdapter.CardViewViewHolder> {

    private Context context;

    private ArrayList<PemainModel> listPemain;
    public CardViewPemainAdapter(ArrayList<PemainModel> list, Context context) {
        this.listPemain = list;
        this.context = context;
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
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_pemain, viewGroup, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewViewHolder holder, int position) {
        PemainModel pemain = listPemain.get(position);

        final String gambarpemain = pemain.getPhoto();
        final String namapemain = pemain.getName();
        final String frompemain = pemain.getFrom();
        final String despemain = pemain.getKeterangan();

        Glide.with(holder.itemView.getContext())
                .load(pemain.getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(holder.imgPhoto);
        holder.tvName.setText(pemain.getName());
        holder.tvKeterangan.setText(pemain.getKeterangan());
        holder.tvFrom.setText(pemain.getFrom());

        holder.imgPhoto.setImageURI(Uri.parse(gambarpemain));
        holder.tvName.setText(namapemain);
        holder.tvFrom.setText(frompemain);
        holder.tvKeterangan.setText(despemain);

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDetail(gambarpemain,namapemain,frompemain,despemain);
            }

            private void OpenDetail(String gambarpemain, String namapemain, String frompemain, String despemain) {
                Intent move = new Intent(context, DetailPemainActivity.class);
                move.putExtra("POTO",gambarpemain);
                move.putExtra("NAMA",namapemain);
                move.putExtra("ASAL",frompemain);
                move.putExtra("KET",despemain);
                context.startActivity(move);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "Kamu memilih " + listPemain.get(holder.getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listPemain.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPemain.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvFrom, tvKeterangan;
        Button btnDetail, btnShare;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvKeterangan = itemView.findViewById(R.id.tv_item_des);
            tvFrom = itemView.findViewById(R.id.tv_item_from);
            btnDetail = itemView.findViewById(R.id.btn_set_detail);
        }
    }
    public interface OnItemClickCallback {
        void onItemClicked(PemainModel data);
    }
}
