package com.example.m3.mapapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sabrinapalmer on 4/20/18.
 */

public class BulletinAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<Bulletin> mBulletinList;
    private LayoutInflater mInflater;


    public BulletinAdapter(Context mContext, ArrayList<Bulletin> mBulletinList){

        this.mContext = mContext;
        this.mBulletinList = mBulletinList;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount(){
        return mBulletinList.size();
    }

    @Override
    public Object getItem(int position){
        return mBulletinList.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null){
            // inflate
            convertView = mInflater.inflate(R.layout.bulletin_board_layout, parent, false);

            holder = new ViewHolder();

            holder.listTitle = convertView.findViewById(R.id.postTitleTextView);
            holder.listContentPreview = convertView.findViewById(R.id.postContentTextView);
            holder.listThumbnail = convertView.findViewById(R.id.postThumbnailImageView);

            convertView.setTag(holder);
        }
        else{
            // get the view holder from convert view
            holder = (ViewHolder)convertView.getTag();
        }

        TextView postTitleTextView = holder.listTitle;
        TextView postContentTextView = holder.listContentPreview;
        ImageView thumbnailImageView = holder.listThumbnail;

        Bulletin bulletin = (Bulletin)getItem(position);

        Log.d("DEBUG 1:", bulletin.title);
        postTitleTextView.setText(bulletin.title);
        postContentTextView.setText(bulletin.description);
        thumbnailImageView.setImageResource(R.drawable.testimg);

        return convertView;
    }





    private static class ViewHolder{
        public TextView listTitle;
        public TextView listContentPreview;
        public ImageView listThumbnail;
    }

}
