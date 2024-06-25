package com.example.project1.User;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.project1.R;
import com.example.project1.admin.Adapters.Images;
import com.example.project1.admin.Adapters.ImageAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class cse extends Fragment {

    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;
    private List<Images> imageList;
    private DatabaseReference databaseReference;
    private String selectedCategory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cse, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_cse);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        imageList = new ArrayList<>();
        imageAdapter=new ImageAdapter(getContext(),imageList);
        recyclerView.setAdapter(imageAdapter);
        databaseReference = FirebaseDatabase.getInstance().getReference("cs");
       // selectedCategory = getArguments().getString("Images");

        retrieveImagesFromFirebase();

        return view;
    }

    private void retrieveImagesFromFirebase() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                imageList.clear();
                for (DataSnapshot categorySnapshot : dataSnapshot.getChildren()) {
//                    String categoryKey = categorySnapshot.getKey();
//                    for (DataSnapshot imageSnapshot : categorySnapshot.getChildren()) {
//                        String imageKey = imageSnapshot.getKey();
//                        String caption = imageSnapshot.child("caption").getValue(String.class);
//                        String imageURL = imageSnapshot.child("imageURL").getValue(String.class);
//                        String dateTime = imageSnapshot.child("dateTime").getValue(String.class);
//                        Images image = new Images(caption, imageURL, dateTime);
//                        imageList.add(image);
//                    }
                    Images images=categorySnapshot.getValue(Images.class);
                    imageList.add(images);
                }
                imageAdapter.notifyDataSetChanged();
                Toast.makeText(getContext(), String.valueOf(imageList.size()), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
