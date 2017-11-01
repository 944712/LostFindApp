package swu.lostfindapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 8308-04 on 2017-08-01.
 */

public class SearchAdapter extends BaseAdapter {
    Context context;
    ArrayList<SearchItemActivity> search_itemArrayList;
    ViewHolder viewholder;

    class ViewHolder {
        TextView search_title;
        TextView search_place;
        TextView search_date;
        ImageView search_img;
    }
    public SearchAdapter(Context context, ArrayList<SearchItemActivity> search_itemArrayList) {
        this.context = context;
        this.search_itemArrayList = search_itemArrayList;
    }

    @Override
    public int getCount() {
        return this.search_itemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return search_itemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_search_item,null);
            viewholder = new ViewHolder();

            viewholder.search_title = (TextView)convertView.findViewById(R.id.search_title);
            viewholder.search_place =(TextView)convertView.findViewById(R.id.search_place);
            viewholder.search_date =(TextView)convertView.findViewById(R.id.search_date);
            viewholder.search_img=(ImageView)convertView.findViewById(R.id.search_img);

            viewholder.search_title.setText(search_itemArrayList.get(position).getSearch_title());
            viewholder.search_place.setText(search_itemArrayList.get(position).getSearch_place());
            viewholder.search_date.setText(search_itemArrayList.get(position).getSearch_date().toString());
        }
        return convertView;
    }
}
