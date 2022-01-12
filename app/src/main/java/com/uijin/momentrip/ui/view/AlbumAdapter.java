package com.uijin.momentrip.ui.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.uijin.momentrip.R;
import com.uijin.momentrip.data.model.Book;

import java.util.ArrayList;

// 앨범 리싸이클러뷰를 위한 어댑터
public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder>
                        implements OnAlbumItemClickListener {
    ArrayList<Book> items = new ArrayList<Book>();
    OnAlbumItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // 뷰홀더 객체가 만들어질 때
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item_book, viewGroup, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        // 뷰홀더 객체가 바인딩될 때
        Book item = items.get(position);
        viewHolder.setItem(item);

    }

    @Override
    // 어댑터에서 관리하는 아이템 갯수 return
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Book item) {
        items.add(item);
    }

    public void setItems(ArrayList<Book> items) {
        this.items = items;
    }

    public Book getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, Book item) {
        items.set(position, item);
    }

    public void setOnItemClickListener(OnAlbumItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if(listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    // 뷰홀더 정의
    public static class ViewHolder extends RecyclerView.ViewHolder {
        FrameLayout layout;
        ImageView imageView;

        public ViewHolder(View itemView, final OnAlbumItemClickListener listener) {
            super(itemView);

            layout = itemView.findViewById(R.id.momentbook);
            imageView = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if(listener != null) {
                        listener.onItemClick(ViewHolder.this, v, position);
                    }
                }
            });
        }

        public void setItem(Book item) {
//            // set image
//            String imgPath = item.getBook_img();
//            Log.d("Album Adapter", "imagePath -> " + imgPath);
//
//            if (imgPath != null && !imgPath.equals("")) {
//                imageView.setVisibility(View.VISIBLE);
//                imageView.setImageURI(Uri.parse("file://" + imgPath));
//            }

            // Glide로 이미지 표시하기
            String imageUrl = "https://pbs.twimg.com/media/EIxe6IAVAAAKLu-.jpg";
            Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
        }
    }
}
