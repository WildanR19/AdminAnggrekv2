package com.wildan.adminanggrek.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "produk_anggrek";
    private static final String TABLE_NAME = "anggrek";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAMA = "nama";
    private static final String COLUMN_HARGA = "harga";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // FUNGSI UNTUK MEMBUAT DATABASENYA
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAMA + " TEXT,"
                + COLUMN_HARGA + " DOUBLE" + ")";
        db.execSQL(CREATE_USER_TABLE);

        db.execSQL("insert into " + TABLE_NAME + " values(1,'Anggrek Bulan',120000), (2,'Anggrek Vanda',350000),(3,'Anggrek Putih',200000),(4,'Anggrek Ungu',210000), (5,'Anggrek Dendrobium Secund',350000),(6,'Anggrek Candy Strip',150000)");
    }

    // FUNGSI UNTUK MENGECEK DATABASE ADA ATAU TIDAK.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // FUNGSI UNTUK TAMBAH DATA Anggrek
    public void tambahAnggrek(Anggrek Anggrek){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA, Anggrek.getNama());
        values.put(COLUMN_HARGA, Anggrek.getHarga());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // FUNGSI UNTUK AMBIL 1 DATA Anggrek
    public Anggrek getAnggrek(int id_Anggrek){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_NAMA, COLUMN_HARGA},
                COLUMN_ID + "=?", new String[]{String.valueOf(id_Anggrek)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Anggrek Anggrek = new Anggrek(cursor.getString(1), cursor.getString(2));
        return Anggrek;
    }

    public Anggrek getAnggrekName(String nama_Anggrek){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_NAMA, COLUMN_HARGA},
                COLUMN_NAMA + "=?", new String[]{String.valueOf(nama_Anggrek)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Anggrek Anggrek = new Anggrek(cursor.getString(1), cursor.getString(2));
        return Anggrek;
    }

    // FUNGSI UNTUK AMBIL SEMUA DATA Anggrek
    public List<Anggrek> getSemuaAnggrek(){
        List<Anggrek> AnggrekList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                Anggrek Anggrek = new Anggrek(cursor.getString(1), cursor.getString(2));
                AnggrekList.add(Anggrek);
            } while (cursor.moveToNext());
        }
        return AnggrekList;
    }

    // FUNGSI MENGHITUNG ADA BEBERAPA DATA
    public int getAnggrekCount(){
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

    // FUNGSI UPDATE DATA Anggrek
    public int updateDataAnggrek(Anggrek Anggrek) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAMA, Anggrek.getNama());
        values.put(COLUMN_HARGA, Anggrek.getHarga());
        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(Anggrek.getId())});
    }

    // FUNGSI HAPUS DATA 1 Anggrek
    public void hapusDataAnggrek(Anggrek Anggrek) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[]{String.valueOf(Anggrek.getId())});
        db.close();
    }

    // FUNGSI UNTUK MENGHAPUS SEMUA DATA Anggrek
    public void hapusSemuaDataAnggrek(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}