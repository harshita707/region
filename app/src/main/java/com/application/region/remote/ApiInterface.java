package com.application.region.remote;

import com.application.region.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("asia")
    Call<List<Country>> getRegionCounty(
    );
}
