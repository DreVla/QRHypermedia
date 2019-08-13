package com.hpmtutorial.qrcodescanner.model.network;

import com.hpmtutorial.qrcodescanner.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface UsersAPI {

    @GET("{user_id}")
    Call<ResponseBody> findById(@Path("user_id")String bookId);
}
