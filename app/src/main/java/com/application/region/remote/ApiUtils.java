package com.application.region.remote;

public class ApiUtils {

    public static final String BASE_URL = "https://restcountries.eu/rest/v2/region/";

    public static ApiInterface getApiService() {
        return RetrofitClient.getClient(BASE_URL).create(ApiInterface.class);
    }
}
