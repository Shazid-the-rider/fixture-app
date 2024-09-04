package com.example.fixtureapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class stadium extends AppCompatActivity {

    RecyclerView recycleview;
    ArrayList<stadium_class> list;
    ArrayList<stadium_class> originalList; // To store the original list
    SearchView searchview;
    DatabaseReference databaseReference;
    stadiumadapter sadapter;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stadium);
        databaseReference = FirebaseDatabase.getInstance().getReference("stadium");

        recycleview = findViewById(R.id.recycle);
        recycleview.setHasFixedSize(true);
        recycleview.setLayoutManager(new GridLayoutManager(this, 2));

        searchview = findViewById(R.id.searchView);
        searchview.clearFocus();
        searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.trim().isEmpty()) {
                    // When search is cleared, show the full list
                    list.clear();
                    list.addAll(originalList);
                } else {
                    filterlist(newText);
                }
                sadapter.notifyDataSetChanged(); // Notify adapter of changes
                return true;
            }
        });

        list = new ArrayList<>();
        originalList = new ArrayList<>(); // Initialize originalList
        sadapter = new stadiumadapter(this, list);
        recycleview.setAdapter(sadapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                originalList.clear(); // Clear originalList
                for (DataSnapshot ds : snapshot.getChildren()) {
                    stadium_class mf = ds.getValue(stadium_class.class);
                    if (mf != null) {
                        list.add(mf);
                        originalList.add(mf); // Add to originalList
                    }
                }
                sadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        img=findViewById(R.id.imageView5);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(stadium.this,menu.class));
                finish();
            }
        });
    }

    private void filterlist(String text) {
        ArrayList<stadium_class> filteredList = new ArrayList<>();
        for (stadium_class use : originalList) {
            if (use.gett().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(use);
            }
        }

        list.clear();
        list.addAll(filteredList);
    }
}