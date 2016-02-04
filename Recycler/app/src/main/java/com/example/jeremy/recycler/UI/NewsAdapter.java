package com.example.jeremy.recycler.UI;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jeremy.recycler.model.NewsModel;
import com.example.jeremy.recycler.R;

import java.util.List;

/**
 * Created by Jeremy on 03/02/16.
 */


public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private List<NewsModel> newsModels;
    private Context context;

    public NewsAdapter(List<NewsModel> newsModels, Context context) {
        this.newsModels = newsModels;
        this.context = context;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);

        return new NewsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder newsViewHolder, int position) {

        NewsModel info = newsModels.get(position);

        newsViewHolder.setData(info);
    }

    @Override
    public int getItemCount() {
        return newsModels.size();
    }



}



