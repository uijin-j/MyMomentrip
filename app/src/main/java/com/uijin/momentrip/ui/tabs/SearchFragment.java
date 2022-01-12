package com.uijin.momentrip.ui.tabs;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    ArrayList<String> selectedCondt = new ArrayList<>();
    TextView textView, textView2, textView3;



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
            MyBottomSheetDialog bottomSheetDialog = new MyBottomSheetDialog();
            bottomSheetDialog.show(activity.getSupportFragmentManager(), "myBottomSheetDialog");

            getChildFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
                @Override
                public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                    //String 말고 ArrayList로 변환

                    selectedCondt = bundle.getStringArrayList("bundleKey");
                    Log.d("search", selectedCondt.toString());


                }
            });

            if (bottomSheetDialog.isDetached()) {
                //종료될 때만 값을 받아오는게 실행되게 콜백함수를 넣을 수 있을까
            }
        });

        Log.d("22222222222222", "22222222222222start!!!!!!!!!!!_");

        textView = rootView.findViewById(R.id.searchFilter);
        textView2 = rootView.findViewById(R.id.searchFilter2);
        textView3 = rootView.findViewById(R.id.searchFilter3);

        if (!selectedCondt.isEmpty()) {

            textView.setText(selectedCondt.get(0));
            textView.setBackgroundResource(R.drawable.selected_sort);

            textView2.setText(selectedCondt.get(1));
            textView2.setBackgroundResource(R.drawable.selected_sort);

            textView3.setText(selectedCondt.get(2));
            textView3.setBackgroundResource(R.drawable.selected_sort);
        }
        return rootView;
    }
}
