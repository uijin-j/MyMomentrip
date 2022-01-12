package com.uijin.momentrip.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uijin.momentrip.R;

public class Tab3Activity extends AppCompatActivity implements View.OnClickListener {
    Animation fab_open, fab_close;
    Boolean isFabOpen = false;

    Button albumButton;
    Button momentButton;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_tab3);

        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);

        albumButton = findViewById(R.id.button_make_album);
        momentButton = findViewById(R.id.button_make_moment);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(this);
        albumButton.setOnClickListener(this);
        momentButton.setOnClickListener(this);

        anim();
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab:
                anim();
                finish();
                break;
            case R.id.button_make_album:
                anim();
                Toast.makeText(this, "여행집 클릭!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MakeBookActivity.class));
                finish();
                break;
            case R.id.button_make_moment:
                anim();
                Toast.makeText(this, "모멘트 클릭!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MakeMomentActivity.class));
                finish();
                break;
        }
    }

    public void anim() {

        if (isFabOpen) {
            albumButton.startAnimation(fab_close);
            momentButton.startAnimation(fab_close);
            albumButton.setClickable(false);
            momentButton.setClickable(false);
            fab.setRotation(-45);
            isFabOpen = false;
        } else {
            albumButton.startAnimation(fab_open);
            momentButton.startAnimation(fab_open);
            albumButton.setClickable(true);
            momentButton.setClickable(true);
            fab.setRotation(45);
            isFabOpen = true;
        }
    }
}
