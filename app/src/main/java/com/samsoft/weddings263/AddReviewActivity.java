package com.samsoft.weddings263;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.parse.ParseObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class AddReviewActivity extends AppCompatActivity {
    private static final String COMMENT = "COMMENT";
    private static final String DATE = "DATE";
    private static final String RATING = "RATING";
    private static final String COMMENT_TITLE = "COMMENT TITLE";
    private static final String ANONYMOUS_NAME = "ANONYMOUS_NAME";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button postComment;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_addreview);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        EditText name = (EditText) findViewById(R.id.etName);
        EditText title = (EditText) findViewById(R.id.etTitle);
        RatingBar rating = (RatingBar) findViewById(R.id.ratingBar2);
        EditText comment = (EditText) findViewById(R.id.etComment);


        final ParseObject obj = new ParseObject("Reviews");
        obj.add(AddReviewActivity.COMMENT, comment.getText().toString());
        obj.add(AddReviewActivity.COMMENT_TITLE, title.getText().toString());
        obj.add(AddReviewActivity.ANONYMOUS_NAME, name.getText().toString());
        obj.add(AddReviewActivity.RATING, rating.getRating()); //float


        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        obj.add(AddReviewActivity.DATE, dateFormat.format(Calendar.getInstance().getTime()));


        postComment = (Button) findViewById(R.id.btPostComment);
        postComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {

                    obj.saveInBackground();
                } catch (Exception e) {

                } finally {
                    //// TODO: 09-Oct-2015
                }

                Snackbar.make(findViewById(R.id.btPostComment), "Your review has been added.Thanks!", Snackbar.LENGTH_LONG)

                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //TODO
                                //undo post comment

                            }
                        })
                        .show();
            }
        });


    }


}
