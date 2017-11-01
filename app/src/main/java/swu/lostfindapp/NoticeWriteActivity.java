package swu.lostfindapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NoticeWriteActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_write);

        Button can = (Button)findViewById(R.id.noticeCancel);
         can.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                finish(); // 액티비티를 종료합니다.
            }

        });

    }
}
