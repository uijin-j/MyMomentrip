package com.uijin.momentrip.ui.view;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import com.uijin.momentrip.R;

import java.io.InputStream;

public class MomentFragment extends Fragment {
    private static final int OPEN_GALLERY = 1;
    private Context context;

    TextView textView;

    FrameLayout fragmentContainer;
    FrameLayout momentFront;
    FrameLayout momentBack;

    ImageView imageView;
    private Uri imageUrl;

    EditText title;
    EditText content;
    EditText hashtag;

    boolean moment_public = true;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_moment, container, false);
        checkSelfPermission();

        fragmentContainer = rootView.findViewById(R.id.container);
        momentFront = rootView.findViewById(R.id.moment_front);
        momentBack = rootView.findViewById(R.id.moment_back);
        textView = rootView.findViewById(R.id.textView);
        title = rootView.findViewById(R.id.moment_title);
        content = rootView.findViewById(R.id.moment_content);
        hashtag = rootView.findViewById(R.id.moment_hashtag);

        imageView = rootView.findViewById(R.id.image);
        //이미지 설정
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(intent, OPEN_GALLERY);
//
//                momentFront.removeView(textView);

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);

                //다중 이미지를 가져올 수 있도록 세팅
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, OPEN_GALLERY);

                momentFront.removeView(textView);
            }
        });

        return rootView;
    }

    //모멘트 뒤집기
    public void flipOver() {
        if (momentBack.getVisibility() == View.INVISIBLE) {
            //뒷면을 보고 싶으면
            momentFront.setVisibility(View.INVISIBLE);
            momentBack.setVisibility(View.VISIBLE);
        } else {
            //앞면을 보고 싶으면
            momentFront.setVisibility(View.VISIBLE);
            momentBack.setVisibility(View.INVISIBLE);
        }
    }

    //공개범위 설정
    public Boolean getMomentPublic() { return moment_public; }
    public void setMomentPublic(boolean moment_public) {
        this.moment_public = moment_public;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OPEN_GALLERY) {
            if(resultCode == RESULT_OK)
            {
                imageUrl = data.getData();
                Toast.makeText(context, "path : " + getPathFromUri(imageUrl), Toast.LENGTH_LONG).show();
                try{
                    InputStream in = getActivity().getContentResolver().openInputStream(data.getData());

                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();

                    imageView.setImageBitmap(img);
                }catch(Exception e)
                {

                }
            }
            else if(resultCode == RESULT_CANCELED)
            {
                Toast.makeText(context, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }

    public String GetImageUri() {
        return getPathFromUri(imageUrl);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context; //프래그먼트는 getApplicationContext()를 못 하니까
    }

    //절대경로를 구한다.
    public String getPathFromUri(Uri uri){
        if (uri == null) { return ""; }

        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null );

        cursor.moveToNext();

        String path = cursor.getString( cursor.getColumnIndex( "_data" ) );

        cursor.close();

        return path;
    }

    //권한에 대한 응답이 있을때 작동하는 함수
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //권한을 허용 했을 경우
        if (requestCode == 1) {
            int length = permissions.length;
            for (int i = 0; i < length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    // 동의
                    Log.d("MainActivity", "권한 허용 : " + permissions[i]);
                }
            }
        }
    }

    public void checkSelfPermission() {
        String temp = "";
        //파일 읽기 권한 확인
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.READ_EXTERNAL_STORAGE + " ";
        }
        //파일 쓰기 권한 확인
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            temp += Manifest.permission.WRITE_EXTERNAL_STORAGE + " ";
        }
        if (TextUtils.isEmpty(temp) == false) {
            // 권한 요청
            ActivityCompat.requestPermissions(getActivity(), temp.trim().split(" "), 1);
        } else { // 모두 허용 상태
            Toast.makeText(context, "권한을 모두 허용", Toast.LENGTH_SHORT).show();
        }
    }

    public String getMomentTitle() {
        return title.getText().toString();
    }

    public String getMomentContent() {
        return content.getText().toString();
    }

    public String getMomentHashtag() {
        return hashtag.getText().toString();
    }

}
