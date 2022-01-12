package com.uijin.momentrip.ui.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.uijin.momentrip.R;

// 두번째 탭(검색)을 위한 프래그먼트
public class SearchFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_search, container, false);
        initUI(rootView);

        return rootView;
    }

    private void initUI(ViewGroup rootView) {
        // 인플레이션 후 XML 레이아웃 안에 들어 있는 위젯이나 레이아웃을 찾아 변수에 할당하는 함수
    }
}
