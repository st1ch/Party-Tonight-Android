package app.media.opp.partytonight.presentation.adapters;

/**
 * Created by piekie (Artem Vasylenko)
 * on 11/9/16
 */

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;

import app.media.opp.partytonight.presentation.utils.MapUtils;
import app.media.opp.partytonight.presentation.utils.StringUtils;

public class PlacesAutoCompleteAdapter extends ArrayAdapter<String> implements
        Filterable {

    private ArrayList<String> resultList;
    private double longitude = -1, latitude = -1;
    private Context mContext;

    public PlacesAutoCompleteAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);

        this.mContext = context;
    }

    public PlacesAutoCompleteAdapter(Context context, int textViewResourceId,
                                     double latitude, double longitude) {
        super(context, textViewResourceId);

        this.mContext = context;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public int getCount() {
        return resultList != null ? resultList.size() : 0;
    }

    @Override
    public String getItem(int index) {
        return resultList.get(index);
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    // Retrieve the autocomplete results.

                    if (latitude != -1 && longitude != -1) {
                        resultList = MapUtils.autocomplete(constraint
                                        .toString(), Double.toString(latitude), Double.toString(longitude),
                                MapUtils.getCountryCode(mContext, latitude, longitude));

                        if (StringUtils.isVowel(constraint.charAt(constraint.length() - 1))) {
                            resultList.addAll(MapUtils.autocomplete(constraint
                                            .toString() + "c", Double.toString(latitude), Double.toString(longitude),
                                    MapUtils.getCountryCode(mContext, latitude, longitude)));

                            resultList.addAll(MapUtils.autocomplete(constraint
                                            .toString() + "d", Double.toString(latitude), Double.toString(longitude),
                                    MapUtils.getCountryCode(mContext, latitude, longitude)));


                            resultList.addAll(MapUtils.autocomplete(constraint
                                            .toString() + "t", Double.toString(latitude), Double.toString(longitude),
                                    MapUtils.getCountryCode(mContext, latitude, longitude)));
                        } else {
                            resultList.addAll(MapUtils.autocomplete(constraint
                                            .toString() + "a", Double.toString(latitude), Double.toString(longitude),
                                    MapUtils.getCountryCode(mContext, latitude, longitude)));

                            resultList.addAll(MapUtils.autocomplete(constraint
                                            .toString() + "o", Double.toString(latitude), Double.toString(longitude),
                                    MapUtils.getCountryCode(mContext, latitude, longitude)));


                            resultList.addAll(MapUtils.autocomplete(constraint
                                            .toString() + "e", Double.toString(latitude), Double.toString(longitude),
                                    MapUtils.getCountryCode(mContext, latitude, longitude)));
                        }

                        resultList = StringUtils.removeTheSameStrings(resultList);

                    } else {
                        resultList = MapUtils.autocomplete(constraint
                                .toString());
                    }

                    filterResults.values = resultList;
                    filterResults.count = resultList.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
        return filter;
    }
}