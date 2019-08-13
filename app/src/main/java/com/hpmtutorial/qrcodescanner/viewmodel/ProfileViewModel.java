package com.hpmtutorial.qrcodescanner.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.hpmtutorial.qrcodescanner.model.User;
import com.hpmtutorial.qrcodescanner.model.network.RetrofitClient;
import com.hpmtutorial.qrcodescanner.model.network.UsersAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends ViewModel {

    public enum statusValidate{
        GREEN,
        RED
    }
    private UsersAPI usersAPI;
    public MutableLiveData<MainViewModel.UIChanger> uiChangerMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<User> userProfile = new MutableLiveData<>();
    public MutableLiveData<String> photoUrl = new MutableLiveData<>();
    public MutableLiveData<statusValidate> statusColor = new MutableLiveData<>();
    public User user = new User();

    public void sendGet(final String user_id){
        uiChangerMutableLiveData.setValue(MainViewModel.UIChanger.LOADING);
        usersAPI = RetrofitClient.getRetrofitInstance().create(UsersAPI.class);
        usersAPI.findById(user_id).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Gson gson = new Gson();
                    try {
                        user = gson.fromJson(response.body().string(),User.class);
                        userProfile.setValue(user);
                        photoUrl.setValue(user.getPhotoUrl());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(user.getMembership().getStatus().equals("Paid")){
                        statusColor.setValue(statusValidate.GREEN);
                    } else if(user.getMembership().getStatus().equals("Unpaid")){
                        statusColor.setValue(statusValidate.RED);
                    }
                    uiChangerMutableLiveData.setValue(MainViewModel.UIChanger.DONE);
                } else {
                    uiChangerMutableLiveData.setValue(MainViewModel.UIChanger.FAIL);
                    try {
                        Log.d("GetProfile", "onResponse: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                uiChangerMutableLiveData.setValue(MainViewModel.UIChanger.FAIL);
                Log.d("Fail", "onFailure: Something wrong");
            }
        });
    }
}
