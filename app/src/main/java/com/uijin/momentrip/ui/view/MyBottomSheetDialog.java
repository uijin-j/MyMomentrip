package com.uijin.momentrip.ui.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
public class MyBottomSheetDialog extends BottomSheetDialogFragment implements View.OnClickListener {
    private ViewGroup rootView;
    private Context context;

    private RadioGroup radioGroupOrder, radioGroupArea1, radioGroupArea2;

    private ArrayList<RadioButton> orders = new ArrayList<>();
    private ArrayList<RadioButton> areas = new ArrayList<>();
    private ArrayList<CheckBox> styles = new ArrayList<>();

    private String checkedOrder;
    private String checkedArea;
    private ArrayList<String> checkedStyles;

   private onCompleteListener callback;

    public MyBottomSheetDialog(String checkedOrder, String checkedArea, ArrayList<String> checkedStyles, onCompleteListener callback) {
        this.checkedOrder = checkedOrder;
        this.checkedArea = checkedArea;
        this.checkedStyles = checkedStyles;
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
        rootView = (ViewGroup) inflater.inflate(R.layout.search_bottom_sheet, container, false);

        radioGroupOrder = rootView.findViewById(R.id.radioGroupOrder);
        radioGroupArea1 = (RadioGroup) rootView.findViewById(R.id.radioGroupArea1);
        radioGroupArea1.setOnCheckedChangeListener(listener1);
        radioGroupArea2 = (RadioGroup) rootView.findViewById(R.id.radioGroupArea2);
        radioGroupArea2.setOnCheckedChangeListener(listener2);

        orders.add(rootView.findViewById(R.id.popularity_order_btn));
        orders.add(rootView.findViewById(R.id.latest_order_btn));
        orders.add(rootView.findViewById(R.id.nearest_order_btn));
        for(int i=0; i<orders.size(); ++i) {
            if(orders.get(i).getText() == checkedOrder){
                radioGroupOrder.check(i);
                break;
            }
        }

        areas.add(rootView.findViewById(R.id.area1));
        areas.add(rootView.findViewById(R.id.area2));
        areas.add(rootView.findViewById(R.id.area3));
        areas.add(rootView.findViewById(R.id.area4));
        areas.add(rootView.findViewById(R.id.area5));
        areas.add(rootView.findViewById(R.id.area6));
        areas.add(rootView.findViewById(R.id.area7));
        for(int i=0; i<areas.size(); ++i) {
            if(areas.get(i).getText() == checkedArea){
                if(i < 4) {
                    radioGroupArea1.check(i);
                } else {
                    radioGroupArea2.check(i-4);
                }
                break;
            }
        }

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
            for(int j=0; i<checkedStyles.size(); ++i) {
                if(styles.get(i).getText() == checkedStyles.get(j)){
                    styles.get(i).setChecked(true);
                } else {
                    styles.get(i).setSelected(false);
                }
            }
        }

        //선택 완료 버튼
        AppCompatButton completeButton = rootView.findViewById(R.id.completeButton);
        completeButton.setOnClickListener(this);

        //리셋 버튼
        AppCompatButton resetButton = rootView.findViewById(R.id.resetButton);
        resetButton.setOnClickListener(this);

        return rootView;
    }

    //라디오버튼 두 줄에서 하나만 선택할 수 있게 설정
    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                radioGroupArea2.setOnCheckedChangeListener(null);
                radioGroupArea2.clearCheck();
                radioGroupArea2.setOnCheckedChangeListener(listener2);
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                radioGroupArea1.setOnCheckedChangeListener(null);
                radioGroupArea1.clearCheck();
                radioGroupArea1.setOnCheckedChangeListener(listener1);
            }
        }
    };

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.completeButton:
                if (radioGroupOrder.getCheckedRadioButtonId() > 0) {
                    View radioButton = radioGroupOrder.findViewById(radioGroupOrder.getCheckedRadioButtonId());
                    int radioId = radioGroupOrder.indexOfChild(radioButton);
                    RadioButton btn = (RadioButton) radioGroupOrder.getChildAt(radioId);
                    checkedOrder = btn.getText().toString();
                }

                if (radioGroupArea1.getCheckedRadioButtonId() > 0) {
                    View radioButton = radioGroupArea1.findViewById(radioGroupArea1.getCheckedRadioButtonId());
                    int radioId = radioGroupArea1.indexOfChild(radioButton);
                    RadioButton btn = (RadioButton) radioGroupArea1.getChildAt(radioId);
                    checkedArea = btn.getText().toString();
                } else if (radioGroupArea2.getCheckedRadioButtonId() > 0) {
                    View radioButton = radioGroupArea2.findViewById(radioGroupArea2.getCheckedRadioButtonId());
                    int radioId = radioGroupArea2.indexOfChild(radioButton);
                    RadioButton btn = (RadioButton) radioGroupArea2.getChildAt(radioId);
                    checkedArea = btn.getText().toString();
                }

                for(int i=1; i < styles.size(); ++i) {
                    if(styles.get(i).isChecked()) {
                        checkedStyles.add(styles.get(i).getText().toString());
                    }
                }

                callback.onComplete(checkedOrder, checkedArea, checkedStyles);

                dismiss();

            case R.id.resetButton:
                checkedArea = null;
                checkedOrder = null;
                checkedStyles.clear();
                radioGroupOrder.clearCheck();
                radioGroupArea1.clearCheck();
                radioGroupArea2.clearCheck();
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
        return getWindowHeight() * 95 / 100;
    }

    private int getWindowHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }



    /** 응답 콜백 */
    public interface onCompleteListener {
        public void onComplete(String checkedOrder, String checkedArea, ArrayList<String> checkedStyles);
    }
}
