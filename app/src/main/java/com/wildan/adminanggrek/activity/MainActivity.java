package com.wildan.adminanggrek.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.wildan.adminanggrek.R;

public class MainActivity extends AppCompatActivity {

    RelativeLayout tambahpd, listpd, jual, logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tambahpd = (RelativeLayout) findViewById(R.id.produk);
        listpd = (RelativeLayout) findViewById(R.id.listproduk);
        jual = (RelativeLayout) findViewById(R.id.penjualan);
        logout = (RelativeLayout) findViewById(R.id.logout);

        tambahpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TambahProdukActivity.class);
                startActivity(i);
            }
        });

        listpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ProdukActivity.class);
                startActivity(i);
            }
        });

        jual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent i = new Intent(MainActivity.this, PenjualanActivity.class);
                startActivity();*/
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
