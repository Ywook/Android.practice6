package com.examples.androidpractice5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Mac on 2017. 4. 13..
 */

public class resAdapter extends BaseAdapter {
    ArrayList<restaurant> datalist;
    ArrayList<Integer> deletelist = new ArrayList<>();
    Context context;

    final static int NAME_ASC = 0;
    final static int TYPE_ASC = 1;

    CheckBox checkBox;

    Comparator<restaurant> nameAsc = new Comparator<restaurant>() {
        @Override
        public int compare(restaurant restaurant, restaurant t1) {
            return restaurant.getName().compareTo(t1.getName());
        }
    };

    Comparator<restaurant> typeAsc = new Comparator<restaurant>() {
        @Override
        public int compare(restaurant restaurant, restaurant t1) {
            return (restaurant.getType()+"").compareTo(t1.getType()+"");
        }
    };

    public resAdapter(ArrayList<restaurant> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int i) {
        return datalist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.list_item,null);
        }
        TextView txt1 = (TextView)view.findViewById(R.id.textName);
        TextView txt2 = (TextView)view.findViewById(R.id.textTel);
        ImageView img = (ImageView)view.findViewById(R.id.imageview);

        final int temp = i;

        if(datalist.get(i).getType() == 1) img.setImageResource(R.drawable.chicken);
        else if(datalist.get(i).getType() == 2) img.setImageResource(R.drawable.hamburger);
        else img.setImageResource(R.drawable.pizza);


        txt1.setText(datalist.get(i).getName());
        txt2.setText(datalist.get(i).getTell());

        return view;
    }

    public void setSort(int sortType){
        if(sortType == NAME_ASC){
            Collections.sort(datalist, nameAsc);
            this.notifyDataSetChanged();
        }else{
            Collections.sort(datalist, typeAsc);
            this.notifyDataSetChanged();
        }
    }


    public void delete(){

    }
}
