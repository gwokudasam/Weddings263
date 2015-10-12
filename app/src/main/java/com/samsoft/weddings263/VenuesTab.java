package com.samsoft.weddings263;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by mister on 10-Sep-2015.
 */
public class VenuesTab extends Fragment {
    private RatingBar ratingBar;
    private ImageView venueImageThumbnail;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_venues, container, false);

        ArrayList image_details = getListData();

        final ListView lv1 = (ListView) v.findViewById(R.id.custom_list);
        //final ListView lv1 = (ListView) rootView.findViewById(R.id.custom_list);

        lv1.setAdapter(new CustomListAdapter(getActivity(), image_details));
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                VenueItem newsData = (VenueItem) o;
                Toast.makeText(getActivity().getBaseContext(), "Selected :" + " " + newsData, Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }


    private ArrayList getListData() {
        ArrayList<VenueItem> results = new ArrayList<VenueItem>();


        String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
                " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip" +
                " ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit" +
                " esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, " +
                "sunt in culpa qui officia deserunt mollit anim id est laborum";

        VenueItem venue1 = new VenueItem("Kutsvene Gardens", "inquiries@kutsvene.com", "+26377345678900", "31 Samora Machel Ave", "67 reviews", loremIpsum, R.drawable.unifest);
        VenueItem venue2 = new VenueItem("Tynwald Gardens", "bookings@tynwald.com", "+26342890234", "34 Samora Machel Ave", "17 reviews", loremIpsum, R.drawable.keysfinal);
        VenueItem venue3 = new VenueItem("Borrowdale Country Manor", "inquiries@bcm.co.zw", "+263772345122", "14 Nelson Mandela Ave", "4 reviews", loremIpsum, R.drawable.bridesbelles);

        results.add(venue1);
        results.add(venue2);
        results.add(venue3);

        // Add some more dummy data for testing
        return results;
    }


    public void callVenue(String phoneNumber) {
        Intent i = new Intent(android.content.Intent.ACTION_CALL,
                Uri.parse("tel:+" + phoneNumber));
        startActivity(i);
    }

}
