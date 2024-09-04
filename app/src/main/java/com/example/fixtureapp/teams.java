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

public class teams extends AppCompatActivity {
    RecyclerView recycleview;
    ArrayList<team_class> list;
    ArrayList<team_class> originalList; // To store the original list
    SearchView searchview;
    DatabaseReference databaseReference;
    teamadapter tadapter;
    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        recycleview = findViewById(R.id.recycle);
        recycleview.setHasFixedSize(true);
        recycleview.setLayoutManager(new GridLayoutManager(this, 2));

        img = findViewById(R.id.imageView5);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(teams.this, menu.class));
                finish();
            }
        });

        list = new ArrayList<>();
        originalList = new ArrayList<>(); // Initialize originalList

        // Initialize database:
        databaseReference = FirebaseDatabase.getInstance().getReference("teams");

        // Connect RecyclerView with items:
        tadapter = new teamadapter(this, list);
        recycleview.setAdapter(tadapter);

        // Fetch data:
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                originalList.clear(); // Clear originalList
                for (DataSnapshot ds : snapshot.getChildren()) {
                    team_class mf = ds.getValue(team_class.class);
                    if (mf != null) {
                        list.add(mf);
                        originalList.add(mf); // Add to originalList
                    }
                }
                tadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
                tadapter.notifyDataSetChanged(); // Notify adapter of changes
                return true;
            }
        });
    }

    private void filterlist(String text) {
        ArrayList<team_class> filteredList = new ArrayList<>();
        for (team_class use : originalList) {
            if (use.getname().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(use);
            }
        }

        list.clear();
        list.addAll(filteredList);
    }
}
