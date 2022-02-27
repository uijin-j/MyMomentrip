package com.uijin.momentrip.ui.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.uijin.momentrip.R;

import java.util.ArrayList;

//filterButton 누르면 아래에서 튀어나오는 화면
public class StyleBottomSheetDialog extends BottomSheetDialogFragment implements View.OnClickListener {
    private ViewGroup rootView;
    private Context context;

    private ArrayList<CheckBox> styles = new ArrayList<>();
    private ArrayList<String> checkedStyles;

   private onCompleteListener callback;

    public StyleBottomSheetDialog(ArrayList<String> checkedStyles, onCompleteListener callback) {
        this.checkedStyles=(ArrayList<String>) checkedStyles.clone();
        this.callback=callback;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //모서리 배경 투명
        setStyle(STYLE_NORMAL, R.style.TransparentBottomSheetDialogFragment);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.bottom_sheet_style, container, false);

        styles.add(rootView.findViewById(R.id.style1));
        styles.add(rootView.findViewById(R.id.style2));
        styles.add(rootView.findViewById(R.id.style3));
        styles.add(rootView.findViewById(R.id.style4));
        styles.add(rootView.findViewById(R.id.style5));
        styles.add(rootView.findViewById(R.id.style6));
        styles.add(rootView.findViewById(R.id.style7));
        styles.add(rootView.findViewById(R.id.style8));
        styles.add(rootView.findViewById(R.id.style9));

        for(int i=0; i<styles.size(); ++i) {
            styles.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox button = (CheckBox) v;
                    button.setSelected(button.isSelected());
                    button.setChecked(button.isChecked());
                }
            });
        }

        for(int i=0; i<styles.size(); ++i) {
            for(int j=0; j<checkedStyles.size(); ++j) {
                if(styles.get(i).getText().equals(checkedStyles.get(j))){
                    styles.get(i).setChecked(true);
                    styles.get(i).setSelected(true);
                } else {
                    styles.get(i).setSelected(false);
                    styles.get(i).setSelected(false);
                }
            }
        }

        //선택 완료 버튼
        AppCompatButton completeButton = rootView.findViewById(R.id.completeButton);
        completeButton.setOnClickListener(this);

        //리셋 버튼
        TextView resetButton = rootView.findViewById(R.id.resetButton);
        resetButton.setOnClickListener(this);

        return rootView;
    }


    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.completeButton:
                checkedStyles.clear();
                for(int i=0; i < styles.size(); ++i) {
                    if(styles.get(i).isChecked()) {
                        checkedStyles.add(styles.get(i).getText().toString());
                    }
                }

                callback.onComplete(checkedStyles);

                dismiss();

            case R.id.resetButton:
                checkedStyles.clear();
                for(int i=1; i<styles.size(); ++i) {
                    styles.get(i).setChecked(false);
                }
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        this.context = context;
    }

    //이 밑으로는 BottomSheetDialog 크기 변경에 관한 코드
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        dialog.setOnShowListener(dialogInterface -> {
            BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialogInterface;
            setupRatio(bottomSheetDialog);
        });
        return dialog;
    }

    private void setupRatio(BottomSheetDialog bottomSheetDialog) {
        //id = com.google.android.material.R.id.design_bottom_sheet for Material Components
        //id = android.support.design.R.id.design_bottom_sheet for support librares
        FrameLayout bottomSheet = (FrameLayout) bottomSheetDialog.findViewById(R.id.design_bottom_sheet);
        BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
        ViewGroup.LayoutParams layoutParams = bottomSheet.getLayoutParams();
        layoutParams.height = getBottomSheetDialogDefaultHeight();
        bottomSheet.setLayoutParams(layoutParams);
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private int getBottomSheetDialogDefaultHeight() {
        //BottomSheetDialog 의 사이즈 조절. 현재는 전체 화면의 85퍼
        return getWindowHeight() * 50 / 100;
    }

    private int getWindowHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }


    /** 응답 콜백 */
    public interface onCompleteListener {
        public void onComplete(ArrayList<String> checkedStyles);
    }
}
