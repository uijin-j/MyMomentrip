package com.uijin.momentrip.ui.view;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.uijin.momentrip.R;
import com.uijin.momentrip.data.model.CreateMomentResponse;
import com.uijin.momentrip.data.repository.remote.RemoteDataSource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MakeMomentActivity extends AppCompatActivity {
    RemoteDataSource repository; // 네트워크 요청을 위한 객체

    ViewPager pager;
    MyPagerAdapter adapter;
    MomentActionBar momentActionBar;

    TextView selectedAlbum;
    String[] categorys = {"여행집","제주도", "강릉", "나홀로 여행", "효도여행", "당일치기"};

    TextView moment_number;
    ImageView miniImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_moment);

        repository = new RemoteDataSource();

        moment_number = findViewById(R.id.moment_number);
        miniImage = findViewById(R.id.miniImage);

        setActionBar();

        pager = findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);

        adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addItem(new MomentFragment());

        pager.setAdapter(adapter);
        //페이저의 페이지 전환 리스너 설정
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                moment_number.setText(String.format("%d/%d", position+1, adapter.getCount()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //추가 메뉴 버튼(in상단바) 클릭 리스너
        ImageButton moreMenuButton = momentActionBar.getMoreMenuButton();
        moreMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "추가 메뉴 버튼 클릭", Toast.LENGTH_SHORT).show();
            }
        });

        //모멘트 추가 버튼(in상단바) 클릭 리스너
        ImageButton addMomentButton = momentActionBar.getAddMomentButton();
        addMomentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addItem(new MomentFragment());
                pager.setCurrentItem(adapter.getCount() - 1);
                moment_number.setText(String.format("%d/%d", adapter.getCount(), adapter.getCount()));
            }
        });

        //앨범 설정하기
        selectedAlbum = momentActionBar.getSelectedAlbum();
        selectedAlbum.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v) {
                showAlbumPickerDialog();
            }
        });

        //저장 버튼(in상단바) 클릭 리스너
        TextView saveButton = momentActionBar.getSaveButton();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "저장 버튼 클릭", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "모멘트 수" + adapter.getCount(), Toast.LENGTH_SHORT).show();
                MomentFragment moment = (MomentFragment) adapter.getItem(0);

                File file = new File(moment.GetImageUri());

                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part uploadFile = MultipartBody.Part.createFormData("momentImg", file.getName(), requestFile);

                Map<String, RequestBody> request = new HashMap<>();
                RequestBody momentTitle = RequestBody.create(MediaType.parse("text/plain"), moment.getMomentTitle().toString());
                RequestBody momentContent = RequestBody.create(MediaType.parse("text/plain"), moment.getMomentContent().toString());
                RequestBody momentPublic = RequestBody.create(MediaType.parse("text/plain"),moment.getMomentPublic().toString());
                RequestBody UserId = RequestBody.create(MediaType.parse("text/plain"), "2"); // 임시
                RequestBody BookId = RequestBody.create(MediaType.parse("text/plain"), "2"); // 임시

                request.put("momentTitle", momentTitle);
                request.put("momentContent", momentContent);
                request.put("momentPublic", momentPublic);
                request.put("UserId", UserId);
                request.put("BookId", BookId);

                repository.createMoment(uploadFile, request, new RemoteDataSource.GetDataCallback<CreateMomentResponse>() {
                    @Override
                    public void onSuccess(CreateMomentResponse data) {
                        Toast.makeText(getApplicationContext(),"모멘트 만들기 성공!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        Toast.makeText(getApplicationContext(),"모멘트 만들기 실패ㅠ" + throwable, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //공개범위 버튼 클릭 리스너
        ImageView momentPublicButton = findViewById(R.id.momentPublicButton);
        momentPublicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MomentFragment fragment = (MomentFragment) adapter.instantiateItem(pager, pager.getCurrentItem());
                if(fragment.getMomentPublic()) {
                    fragment.setMomentPublic(false);
                    Toast.makeText(getApplicationContext(),"비공개 되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    fragment.setMomentPublic(true);
                    Toast.makeText(getApplicationContext(),"전체공개 되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //turn버튼을 누르면 모멘트 회전
        ImageButton turnButton = (ImageButton) findViewById(R.id.turnButton);
        turnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MomentFragment fragment = (MomentFragment) adapter.instantiateItem(pager, pager.getCurrentItem()); //현재 화면에 있는 프래그먼트 가져오기
                fragment.flipOver();
            }
        });

        //프래그먼트 삭제
//        detector = new GestureDetector(this, new OnSwipeListener() {
//            @Override
//            public boolean onSwipe(Direction direction) {
//                if (direction==Direction.up){
//
//                }
//
//                if (direction==Direction.down){
//
//                }
//                return true;
//            }
//        });
//
//        pager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {

//                return detector.onTouchEvent(event);
//            }
//        });

//        Button tempBut = findViewById(R.id.tempBut);
//        tempBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MomentFragment curFragment = (MomentFragment) adapter.instantiateItem(pager, pager.getCurrentItem());
//                adapter.destroyItem(pager, pager.getCurrentItem(), curFragment);
//                pager.setCurrentItem(adapter.getCount() - 1);
//            }
//        });
    }

    // 미니 이미지 설정
    public void setMiniImage(int resId) {
        miniImage.setImageResource(resId);
    }

    private void setActionBar() {
        Log.d("mylog","getSupportActionBar() : " + getSupportActionBar());
        getSupportActionBar().setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요(이거 없어서 오류난거다. 화난다ㅠ)
        momentActionBar = new MomentActionBar(this, getSupportActionBar());

        momentActionBar.setActionBar();
        //momentActionBar.setSpinner(albums);
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>(); //프래그먼트를 담아둘 객체

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item) {
            //프래그먼트 추가하기
            items.add(item);
            notifyDataSetChanged(); //어댑터에게 데이터가 변경되었다고 알려주기!
        }

        @Override
        public Fragment getItem(int position) {
            //프래그먼트 가져가기
            return items.get(position);
        }

        @Override
        public int getCount() {
            //프래그먼트 갯수 확인
            return items.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //타이틀스크립 설정
            return "페이지 " + position;
        }

        @Override public void destroyItem(View pager, int position, Object view) {
            //뷰 객체 삭제
            ((ViewPager)pager).removeView((View)view);
            items.remove(position);
            view = null;
            notifyDataSetChanged(); //어댑터에게 데이터가 변경되었다고 알려주기!
        }
    }

    // 호출할 다이얼로그 함수를 정의한다.
    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void showAlbumPickerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View view = this.getLayoutInflater().inflate(R.layout.dialog_album_picker, null);
        builder.setView(view);
        //builder.setTitle();
        final NumberPicker picker = (NumberPicker) view.findViewById(R.id.picker);
        picker.setMinValue(0);
        picker.setMaxValue(categorys.length-1);
        picker.setDisplayedValues(categorys);
        picker.setWrapSelectorWheel(false); //순환하지 않게

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                selectedAlbum.setText(categorys[picker.getValue()]);
            }
        })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //negative button action
                    }
                });
        builder.create().show();
    }
}
