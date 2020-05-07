package com.example.viewpagertabex;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment3.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment3 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RadioGroup frdoGrpPet;
    RadioButton frdoDog,frdoCat,frdoRabbit;
    ImageView fivPet;

    public Fragment3() {
        // Required empty public constructor
    }


    public static Fragment3 newInstance(String param1, String param2) {
        Fragment3 fragment = new Fragment3();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment3,container,false);
        frdoGrpPet = (RadioGroup)view.findViewById(R.id.frdoGrpPet);
        frdoDog = (RadioButton)view.findViewById(R.id.frdoCat);
        frdoRabbit = (RadioButton)view.findViewById(R.id.frdoRabbit);
        fivPet = (ImageView)view.findViewById(R.id.fivPet);

        frdoGrpPet.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.frdoDog:
                        fivPet.setImageResource(R.drawable.dog);
                        break;
                    case R.id.frdoCat:
                        fivPet.setImageResource(R.drawable.cat);
                        break;
                    case R.id.frdoRabbit:
                        fivPet.setImageResource(R.drawable.rabbit);
                        break;
                    default:
                        Toast.makeText(getContext(), "이미지를 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }


}
