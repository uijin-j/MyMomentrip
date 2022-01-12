package com.uijin.momentrip.ui.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

    private RadioGroup radioGroupOrder, radioGroupArea1, radioGroupArea2, radioGroupStyle1, radioGroupStyle2, radioGroupStyle3;

    private final ArrayList<String> checkedResult = new ArrayList<>();
    ;

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
        radioGroupOrder.clearCheck();
        radioGroupArea1 = (RadioGroup) rootView.findViewById(R.id.radioGroupArea1);
        radioGroupArea1.clearCheck();
        radioGroupArea1.setOnCheckedChangeListener(listener1);
        radioGroupArea2 = (RadioGroup) rootView.findViewById(R.id.radioGroupArea2);
        radioGroupArea2.clearCheck();
        radioGroupArea2.setOnCheckedChangeListener(listener2);

        radioGroupStyle1 = (RadioGroup) rootView.findViewById(R.id.radioGroupStyle1);
        radioGroupStyle1.clearCheck();
        radioGroupStyle1.setOnCheckedChangeListener(listener3);
        radioGroupStyle2 = (RadioGroup) rootView.findViewById(R.id.radioGroupStyle2);
        radioGroupStyle2.clearCheck();
        radioGroupStyle2.setOnCheckedChangeListener(listener4);
        radioGroupStyle3 = (RadioGroup) rootView.findViewById(R.id.radioGroupStyle3);
        radioGroupStyle3.clearCheck();
        radioGroupStyle3.setOnCheckedChangeListener(listener5);

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

    private RadioGroup.OnCheckedChangeListener listener3 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                radioGroupStyle2.setOnCheckedChangeListener(null);
                radioGroupStyle2.clearCheck();
                radioGroupStyle3.setOnCheckedChangeListener(null);
                radioGroupStyle3.clearCheck();
                radioGroupStyle2.setOnCheckedChangeListener(listener4);
                radioGroupStyle3.setOnCheckedChangeListener(listener5);
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener4 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                radioGroupStyle1.setOnCheckedChangeListener(null);
                radioGroupStyle3.setOnCheckedChangeListener(null);
                radioGroupStyle1.clearCheck();
                radioGroupStyle3.clearCheck();
                radioGroupStyle1.setOnCheckedChangeListener(listener3);
                radioGroupStyle3.setOnCheckedChangeListener(listener5);
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener5 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                radioGroupStyle1.setOnCheckedChangeListener(null);
                radioGroupStyle2.setOnCheckedChangeListener(null);
                radioGroupStyle1.clearCheck();
                radioGroupStyle2.clearCheck();
                radioGroupStyle1.setOnCheckedChangeListener(listener3);
                radioGroupStyle2.setOnCheckedChangeListener(listener4);
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
                    checkedResult.add((String) btn.getText());
                }

                if (radioGroupArea1.getCheckedRadioButtonId() > 0) {
                    View radioButton = radioGroupArea1.findViewById(radioGroupArea1.getCheckedRadioButtonId());
                    int radioId = radioGroupArea1.indexOfChild(radioButton);
                    RadioButton btn = (RadioButton) radioGroupArea1.getChildAt(radioId);
                    checkedResult.add((String) btn.getText());
                } else if (radioGroupArea2.getCheckedRadioButtonId() > 0) {
                    View radioButton = radioGroupArea2.findViewById(radioGroupArea2.getCheckedRadioButtonId());
                    int radioId = radioGroupArea2.indexOfChild(radioButton);
                    RadioButton btn = (RadioButton) radioGroupArea2.getChildAt(radioId);
                    checkedResult.add((String) btn.getText());
                }

                if (radioGroupStyle1.getCheckedRadioButtonId() > 0) {
                    View radioButton = radioGroupStyle1.findViewById(radioGroupStyle1.getCheckedRadioButtonId());
                    int radioId = radioGroupStyle1.indexOfChild(radioButton);
                    RadioButton btn = (RadioButton) radioGroupStyle1.getChildAt(radioId);
                    checkedResult.add((String) btn.getText());
                } else if (radioGroupStyle2.getCheckedRadioButtonId() > 0) {
                    View radioButton = radioGroupStyle2.findViewById(radioGroupStyle2.getCheckedRadioButtonId());
                    int radioId = radioGroupStyle2.indexOfChild(radioButton);
                    RadioButton btn = (RadioButton) radioGroupStyle2.getChildAt(radioId);
                    checkedResult.add((String) btn.getText());
                } else if (radioGroupStyle3.getCheckedRadioButtonId() > 0) {
                    View radioButton = radioGroupStyle3.findViewById(radioGroupStyle3.getCheckedRadioButtonId());
                    int radioId = radioGroupStyle3.indexOfChild(radioButton);
                    RadioButton btn = (RadioButton) radioGroupStyle3.getChildAt(radioId);
                    checkedResult.add((String) btn.getText());
                }
                Log.d("aaaaaaaaaaa", checkedResult.toString());

                Bundle result = new Bundle();
                result.putStringArrayList("bundleKey", checkedResult);

                getParentFragmentManager().setFragmentResult("requestKey", result);
                dismiss();

            case R.id.resetButton:
                if (checkedResult.size() > 0)
                    checkedResult.clear();
                radioGroupOrder.clearCheck();
                radioGroupArea1.clearCheck();
                radioGroupArea2.clearCheck();
                radioGroupStyle1.clearCheck();
                radioGroupStyle2.clearCheck();
                radioGroupStyle3.clearCheck();
        }
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
}
