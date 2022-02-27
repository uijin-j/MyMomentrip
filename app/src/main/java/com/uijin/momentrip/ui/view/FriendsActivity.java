package com.uijin.momentrip.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.uijin.momentrip.R;

public class FriendsActivity extends AppCompatActivity {
    TextView followerBtn;
    TextView followingBtn;
    FrameLayout container;

    FollowerFragment followerFragment;
    FollowingFragment followingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        followerBtn = findViewById(R.id.followers_button);
        followingBtn = findViewById(R.id.following_button);
        container = findViewById(R.id.container);

        followerFragment = new FollowerFragment();
        followingFragment = new FollowingFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, followerFragment).commit();

        followingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                followerBtn.setBackgroundResource(R.drawable.border_bottom);
                followingBtn.setBackgroundResource(R.drawable.border_bottom_black);

                getSupportFragmentManager().beginTransaction().replace(R.id.container, followingFragment).commit();
            }
        });

        followerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                followerBtn.setBackgroundResource(R.drawable.border_bottom_black);
                followingBtn.setBackgroundResource(R.drawable.border_bottom);

                getSupportFragmentManager().beginTransaction().replace(R.id.container, followerFragment).commit();
            }
        });
    }
}
