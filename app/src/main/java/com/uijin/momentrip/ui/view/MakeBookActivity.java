package com.uijin.momentrip.ui.view;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.uijin.momentrip.R;
import com.uijin.momentrip.data.repository.remote.Repository;

import java.io.InputStream;
import java.time.LocalDate;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MakeBookActivity extends AppCompatActivity {
    private static final int OPEN_GALLERY = 1;
    Repository repository; // 네트워크 요청을 위한 객체

    String[] categorys = {"여행집","제주도", "강릉", "나홀로 여행", "효도여행", "당일치기"};

    ImageView mainTmage;
    TextView categoryInput;
    TextView startDateInput;
    TextView endDateInput;

    ImageView imageView;
    private Uri imageUrl;

    LocalDate now = LocalDate.now();
    int[] startDate = new int[3];
    int[] endDate = new int[3];

    AlbumActionBar albumActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_album);

        mainTmage = findViewById(R.id.main_image);
        categoryInput = findViewById(R.id.categoryInput);
        startDateInput = findViewById(R.id.startDateInput);
        endDateInput = findViewById(R.id.endDateInput);

        setActionBar();

        mainTmage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);

                //다중 이미지를 가져올 수 있도록 세팅
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, OPEN_GALLERY);
            }
        });

        categoryInput.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v) {
                showAlbumPickerDialog();
            }
        });

        startDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(0);
            }
        });

        endDateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(1);
            }
        });
    }

    private void setActionBar() {
        Log.d("mylog","getSupportActionBar() : " + getSupportActionBar());
        getSupportActionBar().setDisplayShowCustomEnabled(true); //커스터마이징 하기 위해 필요(이거 없어서 오류난거다. 화난다ㅠ)
        albumActionBar = new AlbumActionBar(this, getSupportActionBar());

        albumActionBar.setActionBar();
        //momentActionBar.setSpinner(albums);
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
                categoryInput.setText(categorys[picker.getValue()]);
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

    private void showDatePickerDialog(int flag) {
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if(flag==0) { // startDate
                    startDate[0] = year; startDate[1] = month; startDate[2] = dayOfMonth;
                    startDateInput.setText(year + "년" + month + "월" + dayOfMonth + "일");
                } else {
                    endDate[0] = year; endDate[1] = month; endDate[2] = dayOfMonth;
                    endDateInput.setText(year + "년" + month + "월" + dayOfMonth + "일");
                }
            }
        }, now.getYear(), now.getMonthValue(), now.getDayOfMonth());

        dialog.show();
    }

    //절대경로를 구한다.
    public String getPathFromUri(Uri uri){
        if (uri == null) { return ""; }

        Cursor cursor = this.getContentResolver().query(uri, null, null, null, null );

        cursor.moveToNext();

        String path = cursor.getString( cursor.getColumnIndex( "_data" ) );

        cursor.close();

        return path;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == OPEN_GALLERY) {
            if(resultCode == RESULT_OK)
            {
                imageUrl = data.getData();
                Toast.makeText(this, "path : " + getPathFromUri(imageUrl), Toast.LENGTH_LONG).show();
                try{
                    InputStream in = this.getContentResolver().openInputStream(data.getData());

                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();

                    mainTmage.setImageBitmap(img);
                }catch(Exception e)
                {

                }
            }
            else if(resultCode == RESULT_CANCELED)
            {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }
}
