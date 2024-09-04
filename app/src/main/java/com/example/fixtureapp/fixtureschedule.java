package com.example.fixtureapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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

public class fixtureschedule extends AppCompatActivity {
    RecyclerView recycleview;
    SearchView searchview;
    ArrayList<matchinfo> list;

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixtureschedule);

        // RecyclerView initialize:
        recycleview = findViewById(R.id.recycle);
        recycleview.setHasFixedSize(true);
        recycleview.setLayoutManager(new LinearLayoutManager(this));

        // List initialize:
        list = new ArrayList<>();
        originalList = new ArrayList<>(); // Initialize originalList

        // SearchView setup:
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
                fixadapter.notifyDataSetChanged(); // Notify adapter of changes
                return true;
            }
        });

        img = findViewById(R.id.imageView5);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(fixtureschedule.this, menu.class));
            }
        });

        // Initialize database:
        databaseReference = FirebaseDatabase.getInstance().getReference("fixture");

        // Set up the adapter:
        fixadapterfy = new fixtureadapter(this, list);
        recycleview.setAdapter(fixadapterfy);

        // Fetching data:
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                originalList.clear(); // Clear originalList
                for (DataSnapshot ds : snapshot.getChildren()) {
                    matchinfo mf = ds.getValue(matchinfo.class);
                    if (mf != null) {
                        list.add(mf);
                        originalList.add(mf); // Add to originalList
                    }
                }
                fixadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database errors
            }
        });
    }

    private void filterlist(String text) {
        ArrayList<matchinfo> filteredList = new ArrayList<>();

        for (matchinfo use : originalList) {
            if (use != null) {
                String team1 = use.gett1();
                String team2 = use.gett2();

                if ((team1 != null && team1.toLowerCase().contains(text.toLowerCase())) ||
                        (team2 != null && team2.toLowerCase().contains(text.toLowerCase()))) {
                    filteredList.add(use);
                }
            }
        }

        list.clear();
        list.addAll(filteredList);
    }
}
