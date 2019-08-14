
package com.hpmtutorial.qrcodescanner.view;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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
                        status.setTextColor(Color.RED);
                        break;
                    case GREEN:
                        status.setTextColor(Color.GREEN);
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
                        break;
                    case FAIL:
                        endAnimation();
                        break;
                    case BACK:
                        Intent scannerActivity = new Intent(getApplicationContext(),ScannerActivity.class);
                        startActivity(scannerActivity);
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
}
