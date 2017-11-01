package swu.lostfindapp;

/**
 * Created by 8308-04 on 2017-08-01.
 */

import android.graphics.drawable.Drawable;

public class FragmentItem {
    private String dateStr ;

    public void setTitle(String date) {
        dateStr = date ;
    }

    public String getTitle() {
        return this.dateStr;
    }
}
