package com.example.project1.admin;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.project1.admin.UploadNotices.upload_details;
import com.example.project1.R;

public class ad_upload_notices extends Fragment {

    private CardView cardView1;
    private CardView cardView2;
    private CardView cardView3;
    private CardView cardView4;
    private CardView cardView0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload_notices, container, false);
        cardView0 = view.findViewById(R.id.cs_card);
        cardView1 = view.findViewById(R.id.isds_card);
        cardView2 = view.findViewById(R.id.aiml_card);
        cardView3 = view.findViewById(R.id.mech_card);
        cardView4 = view.findViewById(R.id.ec_card);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity("isds");
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity("aiml");
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity("mech");
            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity("ec");
            }
        });
        cardView0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity("cs");
            }
        });

        return view;
    }

    private void openActivity(String branch) {
        Intent intent = new Intent(getActivity(), upload_details.class);
        intent.putExtra("branch",branch);
        startActivity(intent);
    }


}