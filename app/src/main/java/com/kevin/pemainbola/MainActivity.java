package com.kevin.pemainbola;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kevin.pemainbola.Adapter.CardViewPemainAdapter;
import com.kevin.pemainbola.Adapter.ListPemainAdapter;
import com.kevin.pemainbola.Model.PemainData;
import com.kevin.pemainbola.Model.PemainModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String title = "Pemain Sepak Bola";
    private RecyclerView rvPemain;
    private ArrayList<PemainModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPemain = findViewById(R.id.rv_pemain);
        rvPemain.setHasFixedSize(true);

        list.addAll(PemainData.getListData());
        showRecyclerList();
    }

    private void showRecyclerList(){
        rvPemain.setLayoutManager(new LinearLayoutManager(this));
        ListPemainAdapter listPemainAdapter = new ListPemainAdapter(list,this);
        rvPemain.setAdapter(listPemainAdapter);

        listPemainAdapter.setOnItemClickCallback(new ListPemainAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(PemainModel data) {
                showSelectedpemain(data);
            }
        });
    }
    private void showRecyclerCardView(){
        rvPemain.setLayoutManager(new LinearLayoutManager(this));
        CardViewPemainAdapter cardViewPemainAdapter = new CardViewPemainAdapter(list, this);
        rvPemain.setAdapter(cardViewPemainAdapter);

        cardViewPemainAdapter.setOnItemClickCallback(new CardViewPemainAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(PemainModel data) {
                showSelectedpemain(data);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public void setMode(int selectedMode) {
        switch (selectedMode) {
            case R.id.action_list:
                showRecyclerList();
                break;

            case R.id.action_cardview:
                showRecyclerCardView();
                break;
            case R.id.about:
                title = "Pemain Sepak Bola ";
                Intent moveAbout = new Intent(this, AboutActivity.class);
                startActivity(moveAbout);
        }
    }
    private void showSelectedpemain(PemainModel pemain) {
        Toast.makeText(this, "Anda memilih " + pemain.getName(), Toast.LENGTH_SHORT).show();
    }
}
