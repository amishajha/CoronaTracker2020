package com.example.coronavirustracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Countrymodel> {
    private Context context;
    private List<Countrymodel> countrymodelList;
    private List<Countrymodel> countrymodelFiltered;

    public CustomAdapter(@NonNull Context context, List<Countrymodel> countrymodelList) {
        super(context, R.layout.list_iem, countrymodelList);
        this.context = context;
        this.countrymodelList = countrymodelList;
        this.countrymodelFiltered = countrymodelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_iem, null, true);
        TextView tvCountryname = view.findViewById(R.id.countryname);
        ImageView imageView = view.findViewById(R.id.imageFlag);
        tvCountryname.setText(countrymodelFiltered.get(position).getCountry());
        Glide.with(context).load(countrymodelFiltered.get(position).getFlag()).into(imageView);
        return view;
    }

    @Override
    public int getCount() {
        return countrymodelFiltered.size();
    }

    @Nullable
    @Override
    public Countrymodel getItem(int position) {
        return countrymodelFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {

            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {

                    FilterResults filterResults = new FilterResults();
                    if (constraint == null || constraint.length() == 0) {
                        filterResults.count = countrymodelList.size();
                        filterResults.values = countrymodelList;

                    } else {
                        List<Countrymodel> resultsModel = new ArrayList<>();
                        String searchStr = constraint.toString().toLowerCase();

                        for (Countrymodel itemsModel : countrymodelList) {
                            if (itemsModel.getCountry().toLowerCase().contains(searchStr)) {
                                resultsModel.add(itemsModel);

                            }
                            filterResults.count = resultsModel.size();
                            filterResults.values = resultsModel;
                        }


                    }

                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                    countrymodelFiltered = (List<Countrymodel>) results.values;
                    AffectedActivity.countrymodelList = (List<Countrymodel>) results.values;
                    notifyDataSetChanged();

                }
            };
            return filter;
        }
    }
