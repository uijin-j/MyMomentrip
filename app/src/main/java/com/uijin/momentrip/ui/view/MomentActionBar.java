package com.uijin.momentrip.ui.view;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;

import com.uijin.momentrip.R;

public class MomentActionBar {
    private Activity activity;
    private ActionBar actionBar;
    private View momentAppBarView;

    public ImageButton moreMenuButton;
    public ImageButton addMomentButton;
    public TextView saveButton;
    public TextView selectedAlbum;

    public MomentActionBar(Activity activity, androidx.appcompat.app.ActionBar actionBar) {
        this.activity = activity;
        this.actionBar = actionBar;
    }

    public void setActionBar() {
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);

        actionBar.setBackgroundDrawable(new ColorDrawable(activity.getResources().getColor(R.color.white)));

        momentAppBarView = LayoutInflater.from(activity).inflate(R.layout.action_bar_moment, null);

        Log.d("mylog","momentAppBarView : " + momentAppBarView );

        actionBar.setCustomView(momentAppBarView);

        moreMenuButton = momentAppBarView.findViewById(R.id.moreMenuButton);
        addMomentButton = momentAppBarView.findViewById(R.id.addMomentButton);
        saveButton = momentAppBarView.findViewById(R.id.saveButton);
        selectedAlbum = momentAppBarView.findViewById(R.id.selectedAlbum);

        Log.d("mylog","moreMenuButton : " + moreMenuButton );
    }

    public ImageButton getMoreMenuButton() {
        return moreMenuButton;
    }

    public ImageButton getAddMomentButton() {
        return addMomentButton;
    }

    public TextView getSaveButton() {
        return saveButton;
    }

    public TextView getSelectedAlbum() {
        return selectedAlbum;
    }
}
