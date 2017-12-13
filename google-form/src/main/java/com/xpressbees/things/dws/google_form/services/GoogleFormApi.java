package com.xpressbees.things.dws.google_form.services;

import com.xpressbees.things.dws.google_form.MainActivity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ajinkyabadve on 12/12/17.
 */

public interface GoogleFormApi {

    @GET(MainActivity.GOOGLE_FORM_URL)
    Call<ResponseBody> getGoogleForm();
}
