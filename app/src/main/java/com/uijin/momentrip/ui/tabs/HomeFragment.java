package com.uijin.momentrip.ui.tabs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uijin.momentrip.R;
import com.uijin.momentrip.data.model.Book;
import com.uijin.momentrip.data.model.GetBookListResponse;
import com.uijin.momentrip.data.model.Moment;
import com.uijin.momentrip.data.repository.remote.Repository;
import com.uijin.momentrip.ui.view.AlbumAdapter;
import com.uijin.momentrip.ui.view.MomentAdapter;
import com.uijin.momentrip.ui.view.OnAlbumItemClickListener;

import java.util.ArrayList;
import java.util.List;

// 첫번째 탭(홈)을 위한 프래그먼트
public class HomeFragment extends Fragment {
    Repository repository; // 네트워크 요청을 위한 객체

    RecyclerView bestAlbumRecyclerView;
    AlbumAdapter bestAlbumAdapter;

    RecyclerView feedAlbumRecyclerView;
    AlbumAdapter feedAlbumAdapter;

    RecyclerView momentRecyclerView;
    MomentAdapter momentAdapter;

    Context context;
    ArrayList<Book> bestBooks;
    ArrayList<Book> followerBooks;
    ArrayList<Moment> followerMoments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home, container, false);

        repository = new Repository();

        initUI(rootView);

        return rootView;
    }

    private void initUI(ViewGroup rootView) {
        // 인플레이션 후 XML 레이아웃 안에 들어 있는 위젯이나 레이아웃을 찾아 변수에 할당하는 함수

        // 베스트 앨범 설정
        bestAlbumRecyclerView = rootView.findViewById(R.id.best_album_view);
        bestAlbumRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        bestAlbumAdapter = new AlbumAdapter();
        repository.getAllBooks(new Repository.GetDataCallback<GetBookListResponse>() {
            @Override
            public void onSuccess(GetBookListResponse data) {
                List<Book> books = data.getBooks();
                bestBooks = new ArrayList<Book>(books);

                bestAlbumAdapter.setItems(bestBooks);

                bestAlbumRecyclerView.setAdapter(bestAlbumAdapter);

                bestAlbumAdapter.setOnItemClickListener(new OnAlbumItemClickListener() {
                    @Override
                    public void onItemClick(AlbumAdapter.ViewHolder holder, View view, int position) {
                        Book item = bestAlbumAdapter.getItem(position);
                        Toast.makeText(getContext(), "아이템 선택됨: "+ item.getBook_title(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(context, "GET Best Books 실패", Toast.LENGTH_LONG).show();
            }
        });

//        Book book1 = new Book(0, null, "베스트 앨범1", true, 0,0);
//        Book book2 = new Book(1, null, "베스트 앨범2", true, 0,0);
//        Book book3 = new Book(2, null, "베스트 앨범3", true, 0,2);
//
//        Book[] books = {book1, book2, book3};
//
//        bestBooks = new ArrayList<>(Arrays.asList(books));

        // 친구 앨범 설정
        feedAlbumRecyclerView = rootView.findViewById(R.id.feed_album_view);
        feedAlbumRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        feedAlbumAdapter = new AlbumAdapter();

        feedAlbumAdapter.addItem(new Book(0, null, "친구 앨범1", true, 0,0));
        feedAlbumAdapter.addItem(new Book(1, null, "친구 앨범2", true, 0,0));
        feedAlbumAdapter.addItem(new Book(2, null, "친구 앨범3", true, 0,2));

        feedAlbumRecyclerView.setAdapter(feedAlbumAdapter);

        feedAlbumAdapter.setOnItemClickListener(new OnAlbumItemClickListener() {
            @Override
            public void onItemClick(AlbumAdapter.ViewHolder holder, View view, int position) {
                Book item = feedAlbumAdapter.getItem(position);
                Toast.makeText(getContext(), "아이템 선택됨: "+ item.getBook_title(), Toast.LENGTH_SHORT).show();
            }
        });

        // 친구 모멘트 설정
        momentRecyclerView = rootView.findViewById(R.id.feed_view);
        momentRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        momentAdapter = new MomentAdapter();
        momentAdapter.addItem(new Moment(0, "모멘트 1", "모멘트 1번 입니당:)", null, true,0, 0));
        momentAdapter.addItem(new Moment(0, "모멘트 2", "모멘트 2번 입니당:)", null, true,0, 0));
        momentAdapter.addItem(new Moment(0, "모멘트 3", "모멘트 3번 입니당:)", null, true,0, 0));
        momentAdapter.addItem(new Moment(0, "모멘트 4", "모멘트 4번 입니당:)", null, true,0, 0));
        momentAdapter.addItem(new Moment(0, "모멘트 5", "모멘트 5번 입니당:)", null, true,0, 0));
        momentAdapter.addItem(new Moment(0, "모멘트 6", "모멘트 6번 입니당:)", null, true,0, 0));

        momentRecyclerView.setAdapter(momentAdapter);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context; //프래그먼트는 getApplicationContext()를 못 하니까
    }

}
