
package com.hpmtutorial.qrcodescanner.view;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.hpmtutorial.qrcodescanner.R;
import com.hpmtutorial.qrcodescanner.databinding.ActivityProfileBinding;
import com.hpmtutorial.qrcodescanner.model.User;
import com.hpmtutorial.qrcodescanner.viewmodel.MainViewModel;
import com.hpmtutorial.qrcodescanner.viewmodel.ProfileViewModel;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    private ProfileViewModel profileViewModel;
    private TextView idTextView;

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

        idTextView = findViewById(R.id.user_id);
        loadingOverlay = findViewById(R.id.overlay_loading_view);
        String idReceived = getIntent().getStringExtra("user_id");
        profileViewModel.sendGet(idReceived);

        observeProfile();
        observeUIChange();
    }

    private void observeProfile() {
        profileViewModel.userMutableLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                fillProfileData(user);
            }
        });
    }

    private void fillProfileData(User user) {
        idTextView.setText(user.getId().toString());
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
                        break;
                    case FAIL:
                        endAnimation();
                        break;
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
