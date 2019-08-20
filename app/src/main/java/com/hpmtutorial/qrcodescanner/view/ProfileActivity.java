
package com.hpmtutorial.qrcodescanner.view;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.hpmtutorial.qrcodescanner.R;
import com.hpmtutorial.qrcodescanner.databinding.ActivityProfileBinding;
import com.hpmtutorial.qrcodescanner.model.User;
import com.hpmtutorial.qrcodescanner.viewmodel.MainViewModel;
import com.hpmtutorial.qrcodescanner.viewmodel.ProfileViewModel;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    private ProfileViewModel profileViewModel;
    private ImageView profilePhoto;
    private TextView status;
    private RelativeLayout container;

    private AlphaAnimation inAnimation;
    private AlphaAnimation outAnimation;
    private FrameLayout loadingOverlay;

    private boolean paid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (isTablet()) {
            // stop screen rotation on phones because <explain>
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setProfileViewModel(profileViewModel);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.dashboard_profile);

        status = findViewById(R.id.profile_view_membership_status);
        profilePhoto = findViewById(R.id.profile_view_profile_image);
        container = findViewById(R.id.container_layout);
        loadingOverlay = findViewById(R.id.overlay_loading_view);
        String idReceived = getIntent().getStringExtra("user_id");
        profileViewModel.sendGet(idReceived);

        setPhoto();
        observeProfile();
        observeUIChange();
        observeStatus();
        observeButtons();

//        finish activity after 10 seconds
        int finishTime = 10; //10 secs
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
            }
        }, finishTime * 1000);
    }

    private void observeButtons() {
        profileViewModel.buttonChange.observe(this, new Observer<ProfileViewModel.buttonStatus>() {
            @Override
            public void onChanged(ProfileViewModel.buttonStatus buttonStatus) {
                switch (buttonStatus){
                    case CONFIRM:
                        Toast.makeText(ProfileActivity.this, "CONFIRM CLICKED", Toast.LENGTH_SHORT).show();
                        break;
                    case CANCEL:
                        Toast.makeText(ProfileActivity.this, "CANCEL CLICKED", Toast.LENGTH_SHORT).show();
                        break;
                        default:
                }
            }
        });
    }

    private void observeStatus() {
        profileViewModel.statusColor.observe(this, new Observer<ProfileViewModel.statusValidate>() {
            @Override
            public void onChanged(ProfileViewModel.statusValidate statusValidate) {
                switch (statusValidate){
                    case RED:
                        status.setTextColor(getResources().getColor(R.color.bad_red));
                        paid = false;
                        break;
                    case GREEN:
                        status.setTextColor(getResources().getColor(R.color.good_green));
                        paid = true;
                        break;
                        default:
                }
            }
        });
    }

    private void observeProfile() {
        profileViewModel.userProfile.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                binding.setUser(user);
            }
        });
    }

    private void setPhoto(){
        profileViewModel.photoUrl.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Picasso.get()
                        .load(s)
                        .placeholder(R.drawable.ic_person_black_24dp)
                        .into(profilePhoto);
            }
        });
    }

    private void observeUIChange() {
        profileViewModel.uiChangerMutableLiveData.observe(this, new Observer<MainViewModel.UIChanger>() {
            @Override
            public void onChanged(MainViewModel.UIChanger uiChanger) {
                switch (uiChanger){
                    case LOADING:
                        startAnimation();
                        break;
                    case DONE:
                        endAnimation();
                        container.setVisibility(View.VISIBLE);
                        if(paid) {
                            Dialog settingsDialog = new Dialog(ProfileActivity.this);
                            settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                            settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.valid_popup_layout, null));
                            settingsDialog.show();
                        }else {
                            Dialog settingsDialog = new Dialog(ProfileActivity.this);
                            settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                            settingsDialog.setContentView(getLayoutInflater().inflate(R.layout.invalid_popup_layout, null));
                            settingsDialog.show();
                        }
                        break;
                    case FAIL:
                        endAnimation();
                        break;
                    case BACK:
                        finish();
                    default:

                }
            }
        });
    }


    public void startAnimation(){
        inAnimation = new AlphaAnimation(0f, 1f);
        inAnimation.setDuration(200);
        loadingOverlay.setAnimation(inAnimation);
        loadingOverlay.setVisibility(View.VISIBLE);
    }

    public void endAnimation(){
        outAnimation = new AlphaAnimation(1f, 0f);
        outAnimation.setDuration(200);
        loadingOverlay.setAnimation(outAnimation);
        loadingOverlay.setVisibility(View.GONE);
    }

    private boolean isTablet() {
        return (this.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent mainActivity = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(mainActivity);
        finish();
    }
}
