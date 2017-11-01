package swu.lostfindapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class NoticeActivity extends AppCompatActivity {

    ListView listView;
    NoticeAdapter noticeAdapter;
    ArrayList<NoticeItemActivity> noticeItemActivityArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView =(ListView)findViewById(R.id.noticeListView);
        noticeItemActivityArrayList = new ArrayList<NoticeItemActivity>();
        noticeItemActivityArrayList.add(new NoticeItemActivity("공지사항이다"));

        noticeAdapter = new NoticeAdapter(NoticeActivity.this, noticeItemActivityArrayList);
        listView.setAdapter(noticeAdapter);

        Button writeBtu = (Button)findViewById(R.id.noticeWriteBtu);
        writeBtu.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(NoticeActivity.this, NoticeWriteActivity.class);
                startActivity(intent);
            }
        });


    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
