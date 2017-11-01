package swu.lostfindapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by 8308-04 on 2017-07-28.
 */

public class MyListAdapter extends BaseAdapter{
    Context context;
    ArrayList<list_item> list_itemArrayList;
    ViewHolder viewholder;

    class ViewHolder{
        TextView title_textView;
        TextView date_textView;
        TextView content_textView;
        ImageView profile_imageView;
    }

    public MyListAdapter(Context context, ArrayList<list_item> list_itemArrayList) {
        this.context = context;
        this.list_itemArrayList = list_itemArrayList;
    }


    @Override
    public int getCount() {
        return this.list_itemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list_itemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

   // Glide.with(context).load(list_itemArrayList.get(position).getProfile_image()//파일경로).into(viewholder.profile_imageView//이미지뷰);
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item,null);
            viewholder = new ViewHolder();
            viewholder.title_textView = (TextView)convertView.findViewById(R.id.title_textview);
            viewholder.date_textView = (TextView)convertView.findViewById(R.id.date_textview);
            viewholder.content_textView = (TextView)convertView.findViewById(R.id.content_textview);
            viewholder.profile_imageView = (ImageView)convertView.findViewById(R.id.profile_imageview);
            convertView.setTag(viewholder);

        }else{
            viewholder = (ViewHolder)convertView.getTag();
        }


        viewholder.title_textView.setText(list_itemArrayList.get(position).getTitle());
        viewholder.content_textView.setText(list_itemArrayList.get(position).getContent());
        viewholder.date_textView.setText(list_itemArrayList.get(position).getWrite_date().toString());
        //viewholder.profile_imageView.setImageResource(list_itemArrayList.get(position).getProfile_image());


        return convertView;
    }

}
