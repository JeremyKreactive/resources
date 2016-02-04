package com.example.jeremy.recycler.UI;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jeremy.recycler.model.NewsModel;
import com.example.jeremy.recycler.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Jeremy on 04/02/16.
 */
public class NewsViewHolder extends RecyclerView.ViewHolder {

    private ImageView image;
    private TextView text;
    private TextView date;



    public NewsViewHolder(View v) {
        super(v);

        image = (ImageView) v.findViewById(R.id.image);
        text = (TextView) v.findViewById(R.id.text);
        date = (TextView) v.findViewById(R.id.date);

    }

    public void setData(NewsModel info) {

        Picasso.with(image.getContext()).load(info.getImageUrl()).into(image);

        text.setText(info.getText());
        date.setText(info.getDate());
    }
}