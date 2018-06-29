package com.twobro.tvelections.network.retrofit;

import com.twobro.tvelections.models.ImageUrl;
import com.twobro.tvelections.models.InfoUsers;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServerApi {

  @GET("select.php?table=info_users")
  Call<InfoUsers> getInfoUsers();

  @GET("select.php?table=images&")
  Call<List<ImageUrl>> getUrlImageList(@Query("idSpeaker") int idSpeaker);
}