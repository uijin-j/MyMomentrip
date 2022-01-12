package com.uijin.momentrip.ui.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.uijin.momentrip.R;

public class SignupActivity3 extends AppCompatActivity {
    private SharedPreferences preferences; // 유저 정보를 위한 SharedPreferences

    TextView celebMsg; // 가입 축하 메세지
    Button finishButton; // 완료 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup3);

        preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);

        celebMsg = findViewById(R.id.celeb_message);

        // 사용자 이름 + 환영 메시지
        celebMsg.setText(preferences.getString("nick","회원") + "님, 가입을 환영합니다!");

        /** TODO: 이미지 처리 / 상태 메시지 처리(프로필 객체?) */

        // 완료 버튼 클릭
        finishButton = findViewById(R.id.finishButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** TODO: 유저 정보 업데이트 */
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

}
