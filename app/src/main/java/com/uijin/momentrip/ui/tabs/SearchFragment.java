package com.uijin.momentrip.ui.tabs;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import com.uijin.momentrip.R;
import com.uijin.momentrip.ui.view.MainActivity;
import com.uijin.momentrip.ui.view.MyBottomSheetDialog;
import com.uijin.momentrip.ui.view.SearchRecommendFragment;

import java.util.ArrayList;

// 두번째 탭(검색)을 위한 프래그먼트
public class SearchFragment extends Fragment {
    MainActivity activity;
    SearchRecommendFragment recommendFragment;

    TextView textView, textView2, textView3;

    private String orderFilter = null;
    private String areaFilter = null;
    private ArrayList<String> styleFilter = new ArrayList<>();

    //액티비티 참조가 필요하면 사용
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        activity = null; //액티비티가 더 이상 참조가 안되니까..
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_search, container, false);

        recommendFragment = new SearchRecommendFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.container, recommendFragment).commit(); // 처음에는 추천 검색어가 나오도록

        Log.d("시작", "start!!!!!!!!!!!_");
        //필터 버튼 클릭 시 아래에서 위로 올라오는 화면
        AppCompatButton filterButton = (AppCompatButton) rootView.findViewById(R.id.filterButton);
        filterButton.setOnClickListener(v -> {
            MyBottomSheetDialog bottomSheetDialog = new MyBottomSheetDialog(orderFilter, areaFilter, styleFilter, new MyBottomSheetDialog.onCompleteListener() {
                @Override
                public void onComplete(String checkedOrder, String checkedArea, ArrayList<String> checkedStyles) {
                    orderFilter = checkedOrder;
                    areaFilter = checkedArea;
                    styleFilter = checkedStyles;

                    if(orderFilter!=null) {
                        textView.setText(orderFilter);
                        textView.setBackgroundResource(R.drawable.selected_sort);
                    } else {
                        textView.setText("정렬");
                        textView.setBackgroundResource(R.drawable.selected_sort_gray);
                    }

                    if(areaFilter!=null) {
                        textView2.setText(areaFilter);
                        textView2.setBackgroundResource(R.drawable.selected_sort);
                    } else {
                        textView2.setText("여행지역");
                        textView2.setBackgroundResource(R.drawable.selected_sort_gray);
                    }

                    if(styleFilter.size()!=0) {
                        textView3.setText("스타일 "+String.valueOf(styleFilter.size()));
                        textView3.setBackgroundResource(R.drawable.selected_sort);
                    } else {
                        textView3.setText("여행스타일");
                        textView3.setBackgroundResource(R.drawable.selected_sort_gray);
                    }
                }
            });
            bottomSheetDialog.show(activity.getSupportFragmentManager(), "myBottomSheetDialog");
        });

        textView = rootView.findViewById(R.id.orderFilter);
        textView2 = rootView.findViewById(R.id.areaFilter);
        textView3 = rootView.findViewById(R.id.styleFilter);

        return rootView;
    }
}
