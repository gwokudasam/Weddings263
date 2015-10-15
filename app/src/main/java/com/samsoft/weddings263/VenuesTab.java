package com.samsoft.weddings263;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.samsoft.weddings263.data.VenueItem;

import java.util.ArrayList;

/**
 * Created by mister on 10-Sep-2015.
 */
public class VenuesTab extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_venues, container, false);

        ArrayList image_details = getListData();

        final ListView lv1 = (ListView) v.findViewById(R.id.custom_list);

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


        String alibee = "ALIBEE GARDEN - MOUNTAIN TOP WEDDING!\n" +
                "Alibee garden is a unique venue it has giant stone features, 30 meter water feature, spectacular mountain view. we have the most affordable prices for unbeatable quality decor. \n" +
                "\n" +
                "DIRECTIONS IF DRIVING: take enterprise road, after chisipite count 5 roads to your right. turn into the 5th road. there is an Alibee Garden sign.\n" +
                "\n" +
                "DIRECTIONS USING PUBLIC TRANSPORT: board a glen lorne commuter omnibus by 4th street terminus.Ask for the commuters that pass by Alibee garden.";
        String blissGardens = "Welcome to Bliss Gardens the venue ideal for weddings, corporate events, conferences, team building and parties Bliss Garden and Hiring Services is a functions venue and events equipment hiring company. From tents, tables and chairs to cutlery, crockery and a very wide range of table cloths and linen (available in an assortment of colours) we offer event design and décor services for any event you can imagine.\n" +
                "\n" +
                "To complement our venue is a 350 seater breath taking glass auditorium we like to call “The Glass Effect” fully equipped with a bar, food prep area, buffet area and world class toilet facilities";

        String buxtonfarm = "welcome to Buxton Farm Functions";


        String dombombira = "DOMBOMBIRA: for spectacular weddings amongst the stunning rocks " +
                "at Mandalay Park, Ruwa. Unique wedding site on a flat rock backed by a waterfall." +
                " A beautifully decorated pergola is available for the ceremony on this rock." +
                "Every wedding is individually planned and supervised to ensure you have a wonderful memory." +
                " A large open sided venue, ensuring comfort for your guests is decorated according to your colour scheme." +
                " There are attachable sides. Fully equipped and licenced for catering and liquor." +
                " Amazing photography areas allow timeless memories. Speakers, microphone and back up generators." +
                "Please email dombombira@gmail.com or phone, sms or whatsapp 0772 466 683 of 0772 868 577 for " +
                "appointment so that we can discuss your requirements for a wonderful, affordable wedding.";

        String enchanted = "Enchanted Garden is a venue found in the plots of Ruwa with the backdrop of " +
                "Chishawasha Hills in the horizon. We pride ourselves in offering a venue that caters to a garden " +
                "wedding and countryside theme. We have beautiful grounds landscaped to perfection with green lawns " +
                "all year round, stunning flowers and beautiful trees surrounding the property.";

        VenueItem venue1 = new VenueItem("Alibee Gardens", "bena@alibee-events.co.zw", "+263772664459", "303 Gleytwn Road, The Grange, Harare", "67 reviews", alibee, R.drawable.alibeegardens, -17.767574, 31.148912);
        VenueItem venue2 = new VenueItem("Bliss Gardens", "info@blissgarden.co.zw", "+263774230106", "Corner Pringle & Huyton Roads , Mandara, Harare, Zimbabwe", "17 reviews", blissGardens, R.drawable.blissgardens, 190.89, 189.00);
        VenueItem venue3 = new VenueItem("Buxton Farm", "buxtonfarm@gmail.com", "0772357049", "24 Barrington Road, Crest Breeders, Hopley, Harare", "4 reviews", buxtonfarm, R.drawable.buxtonfam, 278, 90);
        VenueItem venue4 = new VenueItem("Dombombira (Ruwa)", "dombombira@gmail.com", "+263772868577", "14 Mandalay Park, Ruwa, Zimbabwe", "17 reviews", dombombira, R.drawable.dombomira, 190.89, 189.00);
        VenueItem venue5 = new VenueItem("Enchanted Garden", "glkanengoni@gmail.com", "+263772305277", "4 Fairmile Close, Ruwa, Harare, Zimbabwe", "4 reviews", enchanted, R.drawable.nchantedgarden, 278, 90);

        results.add(venue1);
        results.add(venue2);
        results.add(venue3);
        results.add(venue4);
        results.add(venue5);

        return results;
    }
}


