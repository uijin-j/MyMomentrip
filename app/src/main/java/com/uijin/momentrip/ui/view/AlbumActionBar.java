package com.uijin.momentrip.ui.view;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.ActionBar;

import com.uijin.momentrip.R;

public class AlbumActionBar {
    private Activity activity;
    private ActionBar actionBar;
    private View albumAppBarView;

    public AlbumActionBar(Activity activity, androidx.appcompat.app.ActionBar actionBar) {
        this.activity = activity;
        this.actionBar = actionBar;
    }

    public void setActionBar() {
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        actionBar.setBackgroundDrawable(new ColorDrawable(activity.getResources().getColor(R.color.white)));

        albumAppBarView = LayoutInflater.from(activity).inflate(R.layout.action_bar_album, null);

        actionBar.setCustomView(albumAppBarView);
    }
}
