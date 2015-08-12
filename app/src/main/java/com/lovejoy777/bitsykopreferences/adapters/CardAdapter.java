package com.lovejoy777.bitsykopreferences.adapters;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lovejoy777.bitsykopreferences.R;
import com.lovejoy777.bitsykopreferences.activities.Apps;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{

    List<Apps> mItems;

    public CardAdapter() {
        super();
        mItems = new ArrayList<Apps>();
        Apps apps = new Apps();
        apps.setName("Layers Manager");
        apps.setThumbnail(R.drawable.ribbons);
        mItems.add(apps);

        apps = new Apps();
        apps.setName("Layers Showcase");
        apps.setThumbnail(R.drawable.abstracts);
        mItems.add(apps);

        apps = new Apps();
        apps.setName("RomMate");
        apps.setThumbnail(R.drawable.greenlight);
        mItems.add(apps);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_card_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Apps apps = mItems.get(position);
        viewHolder.tvApps.setText(apps.getName());
        viewHolder.imgThumbnail.setImageResource(apps.getThumbnail());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgThumbnail;
        public TextView tvApps;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            tvApps = (TextView) itemView.findViewById(R.id.tv_apps);
        }
    }

}