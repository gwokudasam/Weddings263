package com.samsoft.weddings263;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
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

import java.util.ArrayList;

/**
 * Created by Samuel Gwokuda on 02-Oct-2015.
 */
public class CustomListAdapter extends BaseAdapter {
    private ArrayList<VenueItem> listData;
    private LayoutInflater layoutInflater;
    private Context context;


    public CustomListAdapter(Context aContext, ArrayList<VenueItem> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
        context = aContext;
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
                context.startActivity(i);

            }
        });


        holder.btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLocationEnabled(context) == true) {

                    LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    double currentLongitude = location.getLongitude();
                    double currentLattitude = location.getLatitude();

                    //double targetLat= -17.730775;
                    //double targetLang = 31.167449;

                    double targetLat = -17.707012;
                    double targetLang = 31.139737;


                    String url = "http://maps.google.com/maps?saddr=" + currentLattitude + "," + currentLongitude + "&daddr=" + targetLat + "," + targetLang + "&mode=driving";
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

    static class ViewHolder {

        TextView VenueNameView;
        TextView VenueAddressView;
        //TextView VenueLocationView;
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




