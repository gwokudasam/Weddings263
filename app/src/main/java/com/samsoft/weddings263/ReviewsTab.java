package com.samsoft.weddings263;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mister on 10-Sep-2015.
 */
public class ReviewsTab extends Fragment {

    private FloatingActionButton fab;
    private TextView tvCommenterName;
    private TextView tvCommentTitle;
    private TextView tvCommentBlock;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_reviews,container,false);

        tvCommenterName = (TextView) v.findViewById(R.id.tvCommenterName);
        tvCommentTitle = (TextView) v.findViewById(R.id.tvCommentTitle);
        tvCommentBlock = (TextView) v.findViewById(R.id.tvCommentBlock);


        fab = (FloatingActionButton) v.findViewById(R.id.fab_normal);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Snackbar.make(v, "Thanks for clicking", Snackbar.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), AddReviewActivity.class);
                startActivity(intent);
            }
        });


        return v;
    }


}





