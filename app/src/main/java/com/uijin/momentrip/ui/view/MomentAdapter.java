package com.uijin.momentrip.ui.view;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uijin.momentrip.R;
import com.uijin.momentrip.data.model.Moment;

import java.util.ArrayList;

// 모멘트 리싸이클러뷰를 위한 어댑터
public class MomentAdapter extends RecyclerView.Adapter<MomentAdapter.ViewHolder> {
    ArrayList<Moment> items = new ArrayList<Moment>();

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // 뷰홀더 객체가 만들어질 때
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item_moment2, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        // 뷰홀더 객체가 바인딩될 때
        Moment item = items.get(position);
        viewHolder.setItem(item);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Moment item) {
        items.add(item);
    }

    public void setItems(ArrayList<Moment> items) {
        this.items = items;
    }

    public Moment getItem(int position) {
        return items.get(position);
    }

    // 뷰홀더 정의
    static class ViewHolder extends RecyclerView.ViewHolder {
        FrameLayout frameLayout;

        FrameLayout moment_front;
        ImageView imageView;
        TextView title;

        FrameLayout moment_back;
        TextView content;
        TextView hashtag;

        public ViewHolder(View itemView) {
            super(itemView);

            frameLayout = itemView.findViewById(R.id.frameLayout);

            moment_front = itemView.findViewById(R.id.moment_front);
            imageView = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);

            moment_back = itemView.findViewById(R.id.moment_back);
            content = itemView.findViewById(R.id.content);
            hashtag = itemView.findViewById(R.id.hashtag);

        }

        public void setItem(Moment item) {
            // set image
            String imgPath = item.getMomentImg();
            Log.d("Moment Adapter", "imagePath -> " + imgPath);

            if (imgPath != null && !imgPath.equals("")) {
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageURI(Uri.parse("file://" + imgPath));

            } else {
//                imageView.setImageResource(R.drawable.ic_sample);
            }

            // set title
            title.setText(item.getMomentTitle());

            // set content
            content.setText(item.getMomentContent());
        }
    }
}
