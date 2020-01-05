package com.wildan.adminanggrek.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wildan.adminanggrek.R;

public class EditProduk extends AppCompatActivity {

    TextView nama,harga;
    Button btn;
    EditText editnama, editharga;
    public String KEY_NAMA = "nama";
    public String KEY_HRG = "harga";

    private String nam,hrg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_produk);

        getSupportActionBar().setTitle("Edit Data");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nama =  (TextView) findViewById(R.id.nama_bunga);
        harga = (TextView) findViewById(R.id.harga_bunga);
        btn = (Button) findViewById(R.id.simpan);
        editnama = (EditText) findViewById(R.id.edit_nama);
        editharga = (EditText) findViewById(R.id.edit_harga);

        nam = getIntent().getStringExtra(KEY_NAMA);
        hrg = getIntent().getStringExtra(KEY_HRG);

        nama.setText(nam);
        harga.setText(hrg);
        editnama.setText(nam);
        editharga.setText(hrg);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditProduk.this,ProdukActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
