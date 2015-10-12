package com.samsoft.weddings263;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mister on 09-Sep-2015.
 */
public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private static MyClickListener myClickListener;
    private ArrayList<DataObject> mDataset;

    public MyRecyclerViewAdapter(ArrayList<DataObject> myDataset) {
        mDataset = myDataset;
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        MyRecyclerViewAdapter.myClickListener = myClickListener;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        holder.VenueName.setText(mDataset.get(position).getVenueName());
        //VenueName, VenueAddress, Rating, Email, TelephoneNumber, Description;
        //TODO

    }

    public void addItem(DataObject dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        void onItemClick(int position, View v);
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView VenueName, VenueAddress, Rating, Email, TelephoneNumber, Description;


        public DataObjectHolder(View itemView) {
            super(itemView);

            VenueName = (TextView) itemView.findViewById(R.id.tvVenueName);
            VenueAddress = (TextView) itemView.findViewById(R.id.tvAddress);
            Rating = (TextView) itemView.findViewById(R.id.tvRating);
            Email = (TextView) itemView.findViewById(R.id.tvEmail);
            TelephoneNumber = (TextView) itemView.findViewById(R.id.tvPhone);
            Description = (TextView) itemView.findViewById(R.id.tvLongDescription);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
}