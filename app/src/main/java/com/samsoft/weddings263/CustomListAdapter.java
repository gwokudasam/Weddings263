package com.samsoft.weddings263;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.samsoft.weddings263.data.VenueItem;

import java.util.ArrayList;

/**
 * Created by Samuel Gwokuda on 02-Oct-2015.
 */
public class CustomListAdapter extends BaseAdapter implements LocationListener {
    double currentLongitude;
    double currentLatitude;
    private ArrayList<VenueItem> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    private LocationManager locationManager;
    private Location location;
    private String provider;

    public CustomListAdapter(Context aContext, ArrayList<VenueItem> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
        this.context = aContext;

        // Getting LocationManager object
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        // Creating an empty criteria object
        Criteria criteria = new Criteria();

        // Getting the name of the provider that meets the criteria
        provider = locationManager.getBestProvider(criteria, false);

        if (provider != null && !provider.equals("")) {
            // Get the location from the given provider
            location = locationManager.getLastKnownLocation(provider);

            locationManager.requestLocationUpdates(provider, 20000, 1, this);

            if (location != null)
                onLocationChanged(location);
            else
                Toast.makeText(context, "Location can't be retrieved", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(context, "No Provider Found", Toast.LENGTH_SHORT).show();
        }

    }

    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_row_layout, null);
            holder = new ViewHolder();
            holder.VenueNameView = (TextView) convertView.findViewById(R.id.tvVenueName);
            holder.VenueAddressView = (TextView) convertView.findViewById(R.id.tvAddress);
            holder.EmailView = (TextView) convertView.findViewById(R.id.tvEmail);
            holder.RatingsReviewsView = (TextView) convertView.findViewById(R.id.tvReviewsNumber);
            holder.TelephoneNumberView = (TextView) convertView.findViewById(R.id.tvPhone);
            holder.DescriptionView = (TextView) convertView.findViewById(R.id.tvLongDescription);
            holder.venuePicture = (ImageButton) convertView.findViewById(R.id.imgVenueThumbnail);

            holder.btnNavigate = (ImageButton) convertView.findViewById(R.id.btnNavigate);
            holder.btnCall = (ImageButton) convertView.findViewById(R.id.btnCall);
            holder.btnAddReviews = (Button) convertView.findViewById(R.id.btnAddReviews);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.VenueNameView.setText(listData.get(position).getVenueName());
        holder.VenueAddressView.setText(listData.get(position).getVenueAddress());
        holder.EmailView.setText(listData.get(position).getEmail());
        holder.TelephoneNumberView.setText(listData.get(position).getPhone());
        holder.RatingsReviewsView.setText(listData.get(position).getReviews());
        holder.DescriptionView.setText(listData.get(position).getDescription());

        holder.btnAddReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, AddReviewActivity.class));
            }
        });


        holder.venuePicture.setImageResource(listData.get(position).getVenuePic());
        holder.venuePicture.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //TODO
                Toast.makeText(context, "This is the listener: " + position + " running", Toast.LENGTH_LONG).show();
            }
        });

        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(android.content.Intent.ACTION_CALL,
                        Uri.parse("tel:+" + listData.get(position).getPhone()));
                context.startActivity(i);//assume the app has been granted permissions

            }
        });

        holder.btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isLocationEnabled(context)) {
                    if (location != null) {
                        currentLongitude = location.getLongitude();
                        currentLatitude = location.getLatitude();
                    }

                    double targetLat = listData.get(position).getLatitude();
                    double targetLang = listData.get(position).getLongitude();

                    String url = "http://maps.google.com/maps?saddr=" + currentLatitude + "," + currentLongitude + "&daddr=" + targetLat + "," + targetLang + "&mode=driving";
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url));
                    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                    context.startActivity(intent);
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                    context.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                                    dialog.cancel();
                                }
                            });
                }
            }
        });
        return convertView;
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLongitude = location.getLongitude();
        currentLatitude = location.getLatitude();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    static class ViewHolder {

        TextView VenueNameView;
        TextView VenueAddressView;
        TextView RatingsReviewsView;
        TextView EmailView;
        TextView TelephoneNumberView;
        TextView DescriptionView;
        ImageButton venuePicture;
        Button btnAddReviews;
        ImageButton btnNavigate;
        ImageButton btnCall;
    }

}