package com.wildan.adminanggrek.crud;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;;
import com.google.android.material.snackbar.Snackbar;
import com.wildan.adminanggrek.R;
import com.wildan.adminanggrek.activity.EditProduk;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AnggrekAdapter extends RecyclerView.Adapter<AnggrekAdapter.AnggrekViewHolder> {
    private static List<Anggrek> AnggrekList = new ArrayList<>();
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

    public AnggrekAdapter(List<Anggrek> AnggrekList) {
        this.AnggrekList = AnggrekList;
    }


    @Override
    public AnggrekAdapter.AnggrekViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        AnggrekViewHolder AnggrekViewHolder = new AnggrekViewHolder(view);
        return AnggrekViewHolder;
    }

    @Override
    public void onBindViewHolder(AnggrekAdapter.AnggrekViewHolder holder, int position) {
        holder.txt_resultnama.setText(AnggrekList.get(position).getNama());
        holder.txt_resultharga.setText(AnggrekList.get(position).getHarga());
    }

    @Override
    public int getItemCount() {
        return AnggrekList.size();
    }

    public static class AnggrekViewHolder extends RecyclerView.ViewHolder {

        TextView txt_resultnama, txt_resultharga;
        Context konteks;
        Button btn_edit, btn_hapus;
        public String KEY_NAMA = "nama";
        public String KEY_HRG = "harga";

        public AnggrekViewHolder(View itemView) {
            super(itemView);
            btn_edit = (Button) itemView.findViewById(R.id.btn_edit);
            btn_hapus = (Button) itemView.findViewById(R.id.btn_hapus);
            txt_resultnama = (TextView) itemView.findViewById(R.id.nama);
            txt_resultharga = (TextView) itemView.findViewById(R.id.harga);
            btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    konteks = v.getContext();
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        Intent i = new Intent(konteks, EditProduk.class);
                        i.putExtra(KEY_NAMA, AnggrekList.get(position).getNama());
                        i.putExtra(KEY_HRG, AnggrekList.get(position).getHarga());
                        konteks.startActivity(i);
                    }
                }
            });
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}