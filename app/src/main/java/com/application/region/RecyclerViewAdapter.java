package com.application.region;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.region.Database.CountryDBitem;
import com.application.region.model.Language;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<CountryDBitem> countryDBitemList= new ArrayList<>();

    private static final String TAG = "recycler view";

    public RecyclerViewAdapter(Context context, List<CountryDBitem> countryDBitemList) {
        this.context = context;
        this.countryDBitemList = countryDBitemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Picasso.get().load(countryDBitemList.get(position).getFlag()).into(holder.flag);

        String name = "Name: " + countryDBitemList.get(position).getName();
        holder.name.setText(name);

        String capital = "Capital: " + countryDBitemList.get(position).getCapital();
        holder.capital.setText(capital);

        String region = "Region: " + countryDBitemList.get(position).getRegion();
        holder.region.setText(region);

        String subregion = "Subregion: " + countryDBitemList.get(position).getSubregion();
        holder.subregion.setText(subregion);

        String population = "Population: " + countryDBitemList.get(position).getPopulation();
        holder.population.setText(population);

        String languages = "Languages: " + countryDBitemList.get(position).getLanguages();
        holder.languages.setText(languages);

        String borders = "Population: " + countryDBitemList.get(position).getBorders();
        holder.borders.setText(borders);

    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "" + countryDBitemList.size());
        return countryDBitemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView flag;
        TextView name, capital, region, subregion, population, borders, languages;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            flag = itemView.findViewById(R.id.country_flag);
            name = itemView.findViewById(R.id.country_name);
            capital = itemView.findViewById(R.id.country_capital);
            region = itemView.findViewById(R.id.country_region);
            subregion = itemView.findViewById(R.id.country_subregion);
            population = itemView.findViewById(R.id.country_population);
            languages = itemView.findViewById(R.id.country_languages);
            borders = itemView.findViewById(R.id.country_borders);


        }
    }
}
