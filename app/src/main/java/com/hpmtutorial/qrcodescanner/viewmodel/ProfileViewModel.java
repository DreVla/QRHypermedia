package com.hpmtutorial.qrcodescanner.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hpmtutorial.qrcodescanner.model.User;
import com.hpmtutorial.qrcodescanner.model.network.RetrofitClient;
import com.hpmtutorial.qrcodescanner.model.network.UsersAPI;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends ViewModel {

    private UsersAPI usersAPI;
    public MutableLiveData<MainViewModel.UIChanger> uiChangerMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();

    public void sendGet(final String user_id){
        uiChangerMutableLiveData.setValue(MainViewModel.UIChanger.LOADING);
        usersAPI = RetrofitClient.getRetrofitInstance().create(UsersAPI.class);
        usersAPI.findById(user_id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    uiChangerMutableLiveData.setValue(MainViewModel.UIChanger.DONE);
                    userMutableLiveData.setValue(response.body());
                    Log.d("GetProfile", "onResponse: User found");
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
            public void onFailure(Call<User> call, Throwable t) {
                uiChangerMutableLiveData.setValue(MainViewModel.UIChanger.FAIL);
                Log.d("Fail", "onFailure: Something wrong");
            }
        });
    }
}
