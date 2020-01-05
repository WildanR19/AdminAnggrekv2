package com.wildan.adminanggrek.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wildan.adminanggrek.R;
import com.wildan.adminanggrek.crud.Anggrek;
import com.wildan.adminanggrek.crud.AnggrekAdapter;
import com.wildan.adminanggrek.crud.DBHandler;
import com.wildan.adminanggrek.crud.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ProdukActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private AnggrekAdapter adapter;
    private DBHandler dbHandler;
    private TextView txt_resultadapter;
    private List<Anggrek> AnggrekList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produk);

        initComponents();
        initRecyclerView();
        cekDataRecyclerView();
    }

    // FUNGSI INI UNTUK MENG-INIT RECYLERVIEW BESERTA ADAPTERNYA
    private void initRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.produk);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        dbHandler = new DBHandler(ProdukActivity.this);
        AnggrekList = dbHandler.getSemuaAnggrek();
        adapter = new AnggrekAdapter(AnggrekList);
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

}
