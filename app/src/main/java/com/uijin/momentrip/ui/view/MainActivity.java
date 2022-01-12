package com.uijin.momentrip.ui.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uijin.momentrip.R;
import com.uijin.momentrip.ui.tabs.AlbumFragment;
import com.uijin.momentrip.ui.tabs.HomeFragment;
import com.uijin.momentrip.ui.tabs.SearchFragment;
import com.uijin.momentrip.ui.tabs.UserFragment;

public class MainActivity extends AppCompatActivity implements OnTabItemSelectedListener {
    private static final String TAG = "MainActivity";

    HomeFragment homeFragment;
    SearchFragment searchFragment;
    AlbumFragment albumFragment;
    UserFragment userFragment;

    FloatingActionButton fab;
    BottomNavigationView bottomNavigation; // 하단바

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //프래그먼트
        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        albumFragment = new AlbumFragment();
        userFragment = new UserFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit(); // 첫 화면은 홈이 나오도록

        // 하단탭
        bottomNavigation = findViewById(R.id.bottom_navigation);
        initBottomNavigation(); // 하단탭 설정 초기화, 클릭 리스너

        //플로팅 버튼(앨범, 모멘트 만들기)
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Tab3Activity.class));
            }
        });



    }

    //------------------인터페이스 구현---------------------
    public void onTabSelected(int position) {
        if(position == 0) {
            bottomNavigation.setSelectedItemId(R.id.tab1);
        } else if (position == 1) {
            bottomNavigation.setSelectedItemId(R.id.tab2);
        } else if (position == 3) {
            bottomNavigation.setSelectedItemId(R.id.tab4);
        } else if (position == 4) {
            bottomNavigation.setSelectedItemId(R.id.tab4);
        }
    }

    // 하단탭 설정 함수
    private void initBottomNavigation() {
        bottomNavigation.setBackground(null);
        bottomNavigation.setItemIconTintList(null);
        bottomNavigation.getMenu().getItem(2).setEnabled(false); // 가운데 버튼은 선택 못하게

        // 탭바 클릭 리스너
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab1:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        break;

                    case R.id.tab2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, searchFragment).commit();
                        break;

                    case R.id.tab4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, albumFragment).commit();
                        break;

                    case R.id.tab5:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, userFragment).commit();
                        break;
                }
                return true;
            }
        });
    }
}