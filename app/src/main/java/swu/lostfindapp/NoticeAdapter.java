package swu.lostfindapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 8308-04 on 2017-07-31.
 */

public class NoticeAdapter extends BaseAdapter{
    Context context;
    ArrayList<NoticeItemActivity> NoticeItem;

    TextView NoticeTitle;

    public NoticeAdapter(Context context, ArrayList<NoticeItemActivity> list_itemArrayList) {
        this.context = context;
        this.NoticeItem = list_itemArrayList;
    }


    @Override
    public int getCount() {
        return this.NoticeItem.size();
    }

    @Override
    public Object getItem(int i) {
        return this.NoticeItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            view= LayoutInflater.from(context).inflate(R.layout.activity_notice_item,null);
            NoticeTitle=(TextView)view.findViewById(R.id.viewNoticeTitle);
        }
        NoticeTitle.setText(NoticeItem.get(i).getNoticeTitle());
        return view;
    }
}
