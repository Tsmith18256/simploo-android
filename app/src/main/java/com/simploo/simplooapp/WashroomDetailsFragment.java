package com.simploo.simplooapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simploo.simplooapp.DataModel.Washroom;


/**
 * A simple {@link Fragment} subclass.
 */
public class WashroomDetailsFragment extends Fragment {

    public WashroomDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_washroom_details, container, false);
    }

}
