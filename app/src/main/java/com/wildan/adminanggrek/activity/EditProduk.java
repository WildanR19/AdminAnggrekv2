package com.wildan.adminanggrek.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.wildan.adminanggrek.R;
import com.wildan.adminanggrek.crud.Anggrek;
import com.wildan.adminanggrek.crud.DBHandler;

public class EditProduk extends AppCompatActivity {

    TextView nama,harga;
    Button btn_simpan;
    EditText editnama, editharga;
    DBHandler dbhelp;
    Anggrek anggrek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_produk);

        getSupportActionBar().setTitle("Edit Data");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nama =  (TextView) findViewById(R.id.nama_bunga);
        harga = (TextView) findViewById(R.id.harga_bunga);
        btn_simpan = (Button) findViewById(R.id.simpan);
        editnama = (EditText) findViewById(R.id.edit_nama);
        editharga = (EditText) findViewById(R.id.edit_harga);

        dbhelp = new DBHandler(this);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            anggrek = dbhelp.getAnggrek(bundle.getInt("ID"));
            editnama.setText(anggrek.getNama());
            editharga.setText(anggrek.getHarga());
            nama.setText(anggrek.getNama());
            harga.setText(anggrek.getHarga());
        }
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbhelp.updateDataAnggrek(anggrek.getId(), editnama.getText().toString(), Double.parseDouble(editharga.getText().toString()));
                Intent i = new Intent(EditProduk.this,ProdukActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
