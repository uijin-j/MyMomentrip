package com.uijin.momentrip.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uijin.momentrip.R;
import com.uijin.momentrip.data.model.User;

public class FollowerFragment extends Fragment {
    RecyclerView following_list;
    FollowingAdapter adapter;

    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_friends, container, false);
        initUI(rootView);

        return rootView;
    }

    private void initUI(ViewGroup rootView) {
        // 인플레이션 후 XML 레이아웃 안에 들어 있는 위젯이나 레이아웃을 찾아 변수에 할당하는 함수
        following_list = rootView.findViewById(R.id.friend_list);
        following_list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        adapter = new FollowingAdapter();
        /** TODO: 팔로잉 리스트 가져와서 설정 */
        adapter.addItem(new User(1, "test", "test1", "test", "test",null));
        adapter.addItem(new User(1, "test", "test2", "test", "test",null));
        adapter.addItem(new User(1, "test", "test3", "test", "test",null));

        following_list.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context; //프래그먼트는 getApplicationContext()를 못 하니까
    }
}
