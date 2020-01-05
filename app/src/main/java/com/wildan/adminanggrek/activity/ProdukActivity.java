package com.wildan.adminanggrek.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wildan.adminanggrek.R;
import com.wildan.adminanggrek.crud.Anggrek;
import com.wildan.adminanggrek.crud.DBHandler;

import java.util.ArrayList;
import java.util.List;

public class ProdukActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<Anggrek> list = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private DBHandler dbHandler;
    private TextView txt_resultadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk);

        dbHandler = new DBHandler(this);
        recyclerView = (RecyclerView)findViewById(R.id.produk);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        /*initComponents();
        initRecyclerView();
        cekDataRecyclerView();*/
    }

    @Override
    protected void onResume(){
        super.onResume();
        list = dbHandler.getSemuaAnggrek();
        adapter = new ListAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder>{
        Context context;
        List<Anggrek> list;

        public ListAdapter(Context context, List<Anggrek> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
            return new ListHolder(v);
        }

        @Override
        public void onBindViewHolder(ListHolder holder, int position) {
            final Anggrek model = list.get(position);
            holder.nama.setText(model.getNama());
            holder.harga.setText(model.getHarga());
            holder.update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ProdukActivity.this, EditProduk.class);
                    intent.putExtra("ID", model.getId());
                    startActivity(intent);
                }
            });
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Yakin Menghapus Data?");
                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dbHandler.hapusDataAnggrek(model.getId());
                            dialogInterface.dismiss();
                            notifyDataSetChanged();
                            Intent intent = getIntent();
                            startActivity(intent);
                            finish();
                        }
                    });
                    builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ListHolder extends RecyclerView.ViewHolder{
            TextView nama, harga;
            Button update, delete;
            public ListHolder(View itemView) {
                super(itemView);
                nama = (TextView)itemView.findViewById(R.id.nama);
                harga = (TextView)itemView.findViewById(R.id.harga);
                update = (Button)itemView.findViewById(R.id.btn_edit);
                delete = (Button)itemView.findViewById(R.id.btn_hapus);
            }
        }
    }

    /*
    // FUNGSI INI UNTUK MENG-INIT RECYLERVIEW BESERTA ADAPTERNYA
    private void initRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.produk);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dbHandler = new DBHandler(ProdukActivity.this);
        list = dbHandler.getSemuaAnggrek();
        adapter = new AnggrekAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initComponents(){
        txt_resultadapter = (TextView) findViewById(R.id.txt_resultadapter);
    }

    // FUNGSI INI UNTUK MENGECEK APAKAH ADA DATA DI DALEM RECYCLERVIEW ATAU TIDAK
    private void cekDataRecyclerView(){
        if (adapter.getItemCount() == 0){
            txt_resultadapter.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            txt_resultadapter.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
*/
}
