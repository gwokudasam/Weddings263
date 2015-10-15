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
//fido
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddReviewActivity extends AppCompatActivity {
    private static final String COMMENT = "COMMENT";
    private static final String DATE = "DATE";
    private static final String RATING = "RATING";
    private static final String COMMENT_TITLE = "COMMENT TITLE";
    private static final String ANONYMOUS_NAME = "ANONYMOUS_NAME";
    ParseObject obj;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button postComment;

    public static void undoPostComment(View v) {
        Snackbar.make(v, "Your comment has been removed!", Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_addreview);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText name = (EditText) findViewById(R.id.etName);
        final EditText title = (EditText) findViewById(R.id.etTitle);
        final RatingBar rating = (RatingBar) findViewById(R.id.ratingBar2);
        final EditText comment = (EditText) findViewById(R.id.etComment);

        obj = new ParseObject("Reviews");

        obj.put(AddReviewActivity.COMMENT, comment.getText().toString());
        obj.put(AddReviewActivity.COMMENT_TITLE, title.getText().toString());
        obj.put(AddReviewActivity.ANONYMOUS_NAME, name.getText().toString());
        obj.put(AddReviewActivity.RATING, rating.getRating());

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        obj.put(AddReviewActivity.DATE, dateFormat.format(Calendar.getInstance().getTime()));

        postComment = (Button) findViewById(R.id.btPostComment);
        postComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    obj.saveInBackground();

                } catch (Exception e) {

                } finally {
                    name.setText("");
                    title.setText("");
                    rating.setRating(0.0f);
                    comment.setText("");
                }
                Snackbar.make(findViewById(R.id.btPostComment), "Your review has been added.Thanks!", Snackbar.LENGTH_LONG)

                        .setAction("UNDO", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //TODO
                                //undo post comment
                                undoPostComment(v);
                            }
                        })
                        .show();
            }
        });
    }

}
