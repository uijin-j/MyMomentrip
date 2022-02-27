package com.uijin.momentrip.ui.view;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uijin.momentrip.R;
import com.uijin.momentrip.data.model.Moment;
import com.uijin.momentrip.data.model.User;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FollowerAdapter extends RecyclerView.Adapter<FollowerAdapter.ViewHolder>{
    ArrayList<User> items = new ArrayList<User>();

    @Override
    public FollowerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // 뷰홀더 객체가 만들어질 때
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.item_friend, viewGroup, false);

        return new FollowerAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowerAdapter.ViewHolder viewHolder, int position) {
        // 뷰홀더 객체가 바인딩될 때
        User item = items.get(position);
        viewHolder.setItem(item);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(User item) {
        items.add(item);
    }

    public void setItems(ArrayList<User> items) {
        this.items = items;
    }

    public User getItem(int position) {
        return items.get(position);
    }

    // 뷰홀더 정의
    static class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView profile_image;
        TextView sns_id;
        TextView name;
        Button button;

        public ViewHolder(View itemView) {
            super(itemView);

            profile_image = itemView.findViewById(R.id.profile_image);
            sns_id = itemView.findViewById(R.id.sns_id);
            name = itemView.findViewById(R.id.name);
            button = itemView.findViewById(R.id.button);

        }

        public void setItem(User item) {
            // set profile
            String imgPath = item.getProfileImg();
            Log.d("Moment Adapter", "imagePath -> " + imgPath);

            if (imgPath != null && !imgPath.equals("")) {
                profile_image.setVisibility(View.VISIBLE);
                profile_image.setImageURI(Uri.parse("file://" + imgPath));

            } else {
//                imageView.setImageResource(R.drawable.ic_sample);
            }

            // set sns_id
            sns_id.setText(item.getSnsId());

            // set name
            name.setText(item.getName());

            // set button
            button.setText("REMOVE");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /** TODO: 차단 */
                }
            });
        }
    }

}
