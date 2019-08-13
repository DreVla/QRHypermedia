package com.hpmtutorial.qrcodescanner.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.hpmtutorial.qrcodescanner.R;
import com.hpmtutorial.qrcodescanner.databinding.ActivityMainBinding;
import com.hpmtutorial.qrcodescanner.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setMainViewModel(mainViewModel);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.dashboard_title);

        observeUI();
    }

    private void observeUI() {
        mainViewModel.uiChangerMutableLiveData.observe(this, new Observer<MainViewModel.UIChanger>() {
            @Override
            public void onChanged(MainViewModel.UIChanger uiChanger) {
                switch (uiChanger){
                    case CAMERA:
                        Intent scannerIntent = new Intent(getApplicationContext(), ScannerActivity.class);
                        startActivity(scannerIntent);
                        break;
                    case LOADING:

                        break;
                    case FAIL:

                        break;
                    default:
                }
            }
        });
    }


}
