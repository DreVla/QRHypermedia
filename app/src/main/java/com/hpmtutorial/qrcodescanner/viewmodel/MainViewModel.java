package com.hpmtutorial.qrcodescanner.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    public enum UIChanger{
        LOADING,
        CAMERA,
        FAIL,
        DONE
    }
    public MutableLiveData<UIChanger> uiChangerMutableLiveData = new MutableLiveData<>();



    public void onScanClick(){
        uiChangerMutableLiveData.setValue(UIChanger.CAMERA);
    }
}
