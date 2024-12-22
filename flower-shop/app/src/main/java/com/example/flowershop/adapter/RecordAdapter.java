package com.example.flowershop.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.flowershop.R;
import com.example.flowershop.sqlite.DBStuff;
import com.example.flowershop.entity.Record;
import com.example.flowershop.entity.Stuff;


import java.util.ArrayList;
import java.util.List;

public class RecordAdapter extends ArrayAdapter<Record> {
    public RecordAdapter(@NonNull Context context, int resource, @NonNull List<Record> objects) {
        super(context, resource, objects);
    }
    //每个子项被滚动到屏幕内的时候会被调用
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Record r=getItem(position);//得到当前项的 Fruit 实例

        View view= LayoutInflater.from(getContext()).inflate(R.layout.item_record,parent,false);

        String id=r.getId();
        Stuff stuff=DBStuff.getById(Integer.valueOf(id));

        TextView title =view.findViewById(R.id.tv_title);
        TextView address =view.findViewById(R.id.et_address);
        ImageView pic=view.findViewById(R.id.iv_pic);
        TextView price =view.findViewById(R.id.tv_price);

        price.setText("¥"+r.getPrice());
        title.setText(stuff.getTitle());
        address.setText("收货地址："+r.getAddress());

        Glide.with(pic).load(stuff.getPic()).into(pic);

        return view;
    }

}
