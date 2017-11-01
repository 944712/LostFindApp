package swu.lostfindapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class NoticeItemActivity extends AppCompatActivity {

    private String NoticeTitle;

    public NoticeItemActivity(String noticeTitle) {
        NoticeTitle = noticeTitle;
    }

    public String getNoticeTitle() {
        return NoticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        NoticeTitle = noticeTitle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_item);
    }
}
