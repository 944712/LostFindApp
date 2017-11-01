package swu.lostfindapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class SearchListActivity extends AppCompatActivity {

    ListView listView;
    SearchAdapter searchAdapter;
    ArrayList<SearchItemActivity> search_itemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView = (ListView)findViewById(R.id.search_listView);


        search_itemArrayList = new ArrayList<SearchItemActivity>();

        search_itemArrayList.add(new SearchItemActivity("제목","분실장소",new Date(System.currentTimeMillis()),""));

        searchAdapter = new SearchAdapter(SearchListActivity.this,search_itemArrayList);
        listView.setAdapter(searchAdapter);
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


}
