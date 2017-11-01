package swu.lostfindapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class DisuseActivity extends AppCompatActivity {
    ListView listView;
    MyListAdapter myListAdapter;
    ArrayList<list_item> list_itemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disuse);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView = (ListView)findViewById(R.id.my_listView);

        list_itemArrayList = new ArrayList<list_item>();
        list_itemArrayList.add(
                new list_item("경로","제목1","습득물종류","습득장소","보관장소",new Date(System.currentTimeMillis()),new Time(System.currentTimeMillis()),"내용1"));
        list_itemArrayList.add(
                new list_item("경로","제목2","습득물종류","습득장소","보관장소",new Date(System.currentTimeMillis()),new Time(System.currentTimeMillis()),"내용2"));
        list_itemArrayList.add(
                new list_item("경로","제목3","습득물종류","습득장소","보관장소",new Date(System.currentTimeMillis()),new Time(System.currentTimeMillis()),"내용3"));
        list_itemArrayList.add(
                new list_item("경로","제목4","습득물종류","습득장소","보관장소",new Date(System.currentTimeMillis()),new Time(System.currentTimeMillis()),"내용4"));
        list_itemArrayList.add(
                new list_item("경로","제목5","습득물종류","습득장소","보관장소",new Date(System.currentTimeMillis()),new Time(System.currentTimeMillis()),"내용5"));
        myListAdapter = new MyListAdapter(DisuseActivity.this,list_itemArrayList);
        listView.setAdapter(myListAdapter);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


}