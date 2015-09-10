package com.samsoft.weddings263;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

/**
 * Created by mister on 10-Sep-2015.
 */
public class VenuesTab extends Fragment {
    private RatingBar ratingBar;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v =inflater.inflate(R.layout.tab_venues,container,false);
        return v;
    }
}
