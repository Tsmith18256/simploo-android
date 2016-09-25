package com.simploo.simplooapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.simploo.simplooapp.DataModel.Washroom;


/**
 * A simple {@link Fragment} subclass.
 */
public class WashroomDetailsFragment extends Fragment {

    private Washroom mWashroom;

    private TextView washroomNameTextView;
    private TextView washroomRatingTextView;

    public WashroomDetailsFragment() {
        // Required empty public constructor
    }

    public static WashroomDetailsFragment newInstance(Washroom washroom) {
        WashroomDetailsFragment fragment = new WashroomDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("WASHROOM_BUNDLE", washroom);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_washroom_details, container, false);

        mWashroom = (Washroom) getArguments().getSerializable("WASHROOM_BUNDLE");

        washroomNameTextView = (TextView) v.findViewById(R.id.washroom_details_name);
        washroomNameTextView.setText(mWashroom.getName());

        washroomRatingTextView = (TextView) v.findViewById(R.id.washroom_details_rating_value);
        washroomRatingTextView.setText("" + mWashroom.getOverallRating());

        return v;
    }

    public Washroom getWashroom() {
        return mWashroom;
    }

}
