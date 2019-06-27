package com.efsalokyay.sozlukuygulama;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private Toolbar toolbar;
    private RecyclerView recyclerView;

    private ArrayList<Kelimeler> kelimelerArrayList;
    private KelimeAdapter adapter;

    private Veritabani vt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycler_view);

        toolbar.setTitle("Sözlük");
        setSupportActionBar(toolbar);

        vt = new Veritabani(this);

        veritabaniKopyala();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        kelimelerArrayList = new KelimelerDao().tumKelimeler(vt);

        adapter = new KelimeAdapter(this, kelimelerArrayList);
        recyclerView.setAdapter(adapter);

    }

    public void aramaYap(String aramaKelime) {
        kelimelerArrayList = new KelimelerDao().kelimeAra(vt, aramaKelime);

        adapter = new KelimeAdapter(this, kelimelerArrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_ara);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        Log.e("onQueryTextSubmit", s);
        aramaYap(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        Log.e("onQueryTextChange", s);
        aramaYap(s);
        return false;
    }

    public void veritabaniKopyala() {
        DatabaseCopyHelper databaseCopyHelper = new DatabaseCopyHelper(this);
        try {
            databaseCopyHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        databaseCopyHelper.openDataBase();
    }
}
