package com.example.viewpagertabex;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


public class Fragment1 extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    ImageView fivRotation;
    final int ROTATION_ANGLE = 15;
    int rot = 0;

    public Fragment1() {
        // Required empty public constructor
    }


    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);

        fivRotation = (ImageView)view.findViewById(R.id.fivRotation);
        fivRotation.setImageResource(R.drawable.dog);


        fivRotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rot += ROTATION_ANGLE;
                fivRotation.setRotation(rot);
            }
        });

        Log.v("Test","Param1 : "+mParam1+" Param2 : "+mParam2);
        return view;
    }


}
