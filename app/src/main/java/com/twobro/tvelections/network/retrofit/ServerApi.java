package com.twobro.tvelections.network.retrofit;

import com.twobro.tvelections.models.InfoUsers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServerApi {

    @GET("select.php?table=info_users")
    Call<InfoUsers> getInfoUsers();

}