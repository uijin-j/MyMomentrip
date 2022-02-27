package com.uijin.momentrip.ui.tabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uijin.momentrip.R;
import com.uijin.momentrip.data.model.Book;
import com.uijin.momentrip.data.model.GetBookListResponse;
import com.uijin.momentrip.data.model.GetMomentListResponse;
import com.uijin.momentrip.data.model.Moment;
import com.uijin.momentrip.data.model.SignupResponse;
import com.uijin.momentrip.data.repository.remote.RemoteDataSource;
import com.uijin.momentrip.ui.view.AlbumAdapter;
import com.uijin.momentrip.ui.view.FriendsActivity;
import com.uijin.momentrip.ui.view.MainActivity;
import com.uijin.momentrip.ui.view.MomentAdapter;
import com.uijin.momentrip.ui.view.OnAlbumItemClickListener;
import com.uijin.momentrip.ui.view.SettingBottomSheetDialog;
import com.uijin.momentrip.ui.view.Tab3Activity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

// 다섯번째 탭(마이페이지)를 위한 프래그먼트
public class UserFragment extends Fragment {
    MainActivity activity;
    RemoteDataSource repository; // 네트워크 요청을 위한 객체
    private SharedPreferences preferences; // 유저 정보를 위한 SharedPreferences

    // View
    ImageView settingsButton;

    ImageView backgrountView;
    CircleImageView profileImageView;
    TextView snsIdView;
    TextView stateMsgView;

    TextView followingsView;
    TextView followersView;

    RecyclerView albumRecyclerView;
    AlbumAdapter albumAdapter;

    RecyclerView momentRecyclerView;
    MomentAdapter momentAdapter;

    // data
    String userId;
    String token;
    String profileImg;
    String backgroundImg;
    String snsId;
    String stateMsg;
    String following_num;
    String follower_num;
    ArrayList<Book> myBooks = null;
    ArrayList<Moment> myMoments = null;

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
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_user, container, false);
        repository = new RemoteDataSource();
        preferences = activity.getSharedPreferences("UserInfo", activity.MODE_PRIVATE);

        initData();
        initUI(rootView);

        return rootView;
    }

    private void initData() {
        /** userId / token */
        userId = preferences.getString("id", null);
        token = preferences.getString("token", null);

        /** profileImg / snsId / stateMsg */
        repository.getUserById(userId, new RemoteDataSource.GetDataCallback<SignupResponse>() {
            @Override
            public void onSuccess(SignupResponse data) {
                profileImg = data.getUser().getProfileImg();
                /** TODO: 배경 이미지와 상태 메시지, 프로필이미지를 유저? or 프로필 테이블? */
                // snsId = data.getUser().getSnsId();
                // stateMsg = data.getUser().getStateMsg();
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(activity,"유저 정보 조회 실패" + throwable.toString(), Toast.LENGTH_LONG).show();
            }
        });

        /** following_num / follower_num */
        /** TODO: 팔로워, 팔로잉 Count 확인하는 API (유저에 넣을 것인지?)*/
        // repository.getFollowings(userId, token, new ... );

        /** myBooks */
        repository.getBooksByUser(userId, new RemoteDataSource.GetDataCallback<GetBookListResponse>() {
            @Override
            public void onSuccess(GetBookListResponse data) {
                List<Book> books = data.getBooks();
                myBooks = new ArrayList<Book>(books);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(activity,"유저 앨범 조회 실패" + throwable.toString(), Toast.LENGTH_LONG).show();
            }
        });

        /** myMoments */
        /** TODO: getMomentByUser를 하면 그냥 앨범에 안 들어간 모멘트만 리스트로! */
         repository.getMomentsByUser(userId, new RemoteDataSource.GetDataCallback<GetMomentListResponse>() {
             @Override
             public void onSuccess(GetMomentListResponse data) {
                 List<Moment> moments = data.getMoments();
                 myMoments = new ArrayList<Moment>(moments);
             }

             @Override
             public void onFailure(Throwable throwable) {
                 Toast.makeText(activity,"유저 모멘트 조회 실패" + throwable.toString(), Toast.LENGTH_LONG).show();
                 myMoments = new ArrayList<Moment>();
             }
         });


        /** 임시 데이터 */
        backgroundImg = "https://i.pinimg.com/originals/f7/e7/07/f7e707388a6fe9e1b0ee4f4ef87948fb.jpg";
        snsId = "uijin_j";
        stateMsg = "순간을 추억으로";
        following_num = "5";
        follower_num = "5";
    }

    private void initUI(ViewGroup rootView) {
        // 인플레이션 후 XML 레이아웃 안에 들어 있는 위젯이나 레이아웃을 찾아 변수에 할당하는 함수
        backgrountView = rootView.findViewById(R.id.backgroung_img);
        backgroundImg = (backgroundImg==null) ? "https://pbs.twimg.com/media/EIxe6IAVAAAKLu-.jpg" : backgroundImg;
        Glide.with(backgrountView.getContext()).load(backgroundImg).into(backgrountView);

        profileImageView = rootView.findViewById(R.id.profile_image);
        profileImg = (profileImg==null) ? "https://pbs.twimg.com/media/EIxe6IAVAAAKLu-.jpg" : profileImg;
        Glide.with(profileImageView.getContext()).load(profileImg).into(profileImageView);

        snsIdView = rootView.findViewById(R.id.sns_id);
        snsIdView.setText(snsId);

        stateMsgView = rootView.findViewById(R.id.state_msg);
        stateMsgView.setText(stateMsg);

        followingsView = rootView.findViewById(R.id.following_num);
        followingsView.setText(following_num);

        followersView = rootView.findViewById(R.id.follower_num);
        followersView.setText(follower_num);

        // 팔로잉 & 팔로워 눌렀을 때
        followersView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FriendsActivity.class));
            }
        });

        followingsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), FriendsActivity.class));
            }
        });

        // 내 앨범 설정
        albumRecyclerView = rootView.findViewById(R.id.album_list_view);
        albumRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        albumAdapter = new AlbumAdapter();

        albumAdapter.addItem(new Book(0, null, "친구 앨범1", true, 0,0));
        albumAdapter.addItem(new Book(1, null, "친구 앨범2", true, 0,0));
        albumAdapter.addItem(new Book(2, null, "친구 앨범3", true, 0,2));

        albumRecyclerView.setAdapter(albumAdapter);

        albumAdapter.setOnItemClickListener(new OnAlbumItemClickListener() {
            @Override
            public void onItemClick(AlbumAdapter.ViewHolder holder, View view, int position) {
                Book item = albumAdapter.getItem(position);
                Toast.makeText(getContext(), "아이템 선택됨: "+ item.getBook_title(), Toast.LENGTH_SHORT).show();
            }
        });

        // 내 모멘트 설정
        momentRecyclerView = rootView.findViewById(R.id.moment_list_view);
        momentRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        momentAdapter = new MomentAdapter();

        momentAdapter.addItem(new Moment(0, "모멘트 1", "모멘트 1번 입니당:)", null, true,0, 0));
        momentAdapter.addItem(new Moment(0, "모멘트 2", "모멘트 2번 입니당:)", null, true,0, 0));
        momentAdapter.addItem(new Moment(0, "모멘트 3", "모멘트 3번 입니당:)", null, true,0, 0));
        momentAdapter.addItem(new Moment(0, "모멘트 4", "모멘트 4번 입니당:)", null, true,0, 0));
        momentAdapter.addItem(new Moment(0, "모멘트 5", "모멘트 5번 입니당:)", null, true,0, 0));
        momentAdapter.addItem(new Moment(0, "모멘트 6", "모멘트 6번 입니당:)", null, true,0, 0));

        momentRecyclerView.setAdapter(momentAdapter);

        settingsButton = rootView.findViewById(R.id.hamburger_btn);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingBottomSheetDialog dialog = new SettingBottomSheetDialog();
                dialog.show(activity.getSupportFragmentManager(), "BottomSheetDialogForSetting");
            }
        });
    }
}
