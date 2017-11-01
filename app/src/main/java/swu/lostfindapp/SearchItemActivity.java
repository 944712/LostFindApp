package swu.lostfindapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Date;

public class SearchItemActivity extends AppCompatActivity {

    private String search_title;
    private String search_place;
    private Date search_date;
    private String search_img;

    public SearchItemActivity(String search_title, String search_place, Date search_date, String search_img) {
        this.search_title = search_title;
        this.search_place = search_place;
        this.search_date = search_date;
        this.search_img = search_img;
    }

    public String getSearch_title() {
        return search_title;
    }

    public void setSearch_title(String search_title) {
        this.search_title = search_title;
    }

    public String getSearch_place() {
        return search_place;
    }

    public void setSearch_place(String search_place) {
        this.search_place = search_place;
    }

    public Date getSearch_date() {
        return search_date;
    }

    public void setSearch_date(Date search_date) {
        this.search_date = search_date;
    }

    public String getSearch_img() {
        return search_img;
    }

    public void setSearch_img(String search_img) {
        this.search_img = search_img;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_item);
    }
}

