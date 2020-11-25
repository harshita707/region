package com.application.region;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.application.region.Database.AppDataBase;
import com.application.region.Database.CountryDBitem;
import com.application.region.model.Country;
import com.application.region.model.Language;
import com.application.region.remote.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static AppDataBase appDataBase;
    private ProgressDialog dialog;
    Button deletebtn;
    RecyclerViewAdapter adapter;
    RecyclerView recyclerView;

    private static final String TAG = "MyActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "countrydb").allowMainThreadQueries().build();

        deletebtn = findViewById(R.id.delete_btn);
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appDataBase.dao().deleteTable();
                adapter.notifyDataSetChanged();

            }
        });

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);

        if(firstStart){
            apicall();
        }

        display();
    }

    private void display() {
        List<CountryDBitem> countries = appDataBase.dao().getCountries();
        Log.i(TAG, "" + countries.size());

         recyclerView = findViewById(R.id.recyclerView);
         adapter = new RecyclerViewAdapter(this, countries);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));
    }

    public void apicall() {
        dialog = new ProgressDialog(this);
        dialog.setTitle("Please wait...");
        dialog.show();

        Call<List<Country>> call = ApiUtils.getApiService().getRegionCounty();

        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if (!response.isSuccessful()) {
                    Log.e("Server error", "" + response.code());
                    Toast.makeText(getApplicationContext(), "Error code: " + response.code(), Toast.LENGTH_SHORT).show();
                } else {
                    List<Country> result = response.body();
                    Log.i(TAG, "" + result.size());
                    for(int i = 0; i < result.size(); i++){
                        Country currentCountry = result.get(i);

                        CountryDBitem countryDBitem = new CountryDBitem();

                        countryDBitem.setId(i);
                        countryDBitem.setName(currentCountry.getName());
                        countryDBitem.setCapital(currentCountry.getCapital());
                        countryDBitem.setFlag(currentCountry.getFlag());
                        countryDBitem.setRegion(currentCountry.getRegion());
                        countryDBitem.setSubregion(currentCountry.getSubregion());
                        countryDBitem.setPopulation(currentCountry.getPopulation());

                        List<Language> language = currentCountry.getLanguages();
                        String lang = "";
                        for (int j =0; j < language.size(); j++){
                            if ( j == language.size() -1){
                                lang = lang.concat(language.get(j).getName());
                            } else {
                                lang = lang.concat(language.get(j).getName() + ", ");
                            }
                        }
                        countryDBitem.setLanguages(lang);

                        List<String> border = currentCountry.getBorders();
                        String borders = "";
                        for (int j =0; j < border.size(); j++){
                            if ( j == border.size() -1){
                                borders = borders.concat(border.get(j));
                            } else {
                                borders = borders.concat(border.get(j) + ", ");
                            }
                        }
                        countryDBitem.setBorders(borders);
                        Log.i(TAG, "added" );

                        appDataBase.dao().addCountry(countryDBitem);

                    }

                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Log.e("Callback failure", t.getMessage());
                Toast.makeText(MainActivity.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        });

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }
}