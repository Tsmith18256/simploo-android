package com.simploo.simplooapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.simploo.simplooapp.DataModel.Washroom;


/**
 * A simple {@link Fragment} subclass.
 */
public class WashroomDetailsFragment extends Fragment {


    private static float MAX_RATING = 5.0f;
    private Washroom mWashroom;

    private TextView washroomNameTextView;
    private TextView washroomRatingTextView;

    private FrameLayout washroomRatingBg;
    private FrameLayout washroomRatingBar;

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
        washroomRatingTextView.setText("" + Math.round(mWashroom.getRating() * 10) / 10.0);

        washroomRatingBg = (FrameLayout) v.findViewById(R.id.washroom_details_rating_bar_bg);
        washroomRatingBar = (FrameLayout) v.findViewById(R.id.washroom_details_rating_bar);

        ViewTreeObserver vto = washroomRatingBg.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                washroomRatingBg.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int maxWidth = washroomRatingBg.getMeasuredWidth();
                ViewGroup.LayoutParams lp = washroomRatingBar.getLayoutParams();
                lp.width = Math.round(mWashroom.getRating() / MAX_RATING * maxWidth);
                washroomRatingBar.setLayoutParams(lp);
            }
        });

        return v;
    }

    public Washroom getWashroom() {
        return mWashroom;
    }

}
