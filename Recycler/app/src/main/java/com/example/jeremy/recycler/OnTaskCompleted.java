package com.example.jeremy.recycler;

import com.example.jeremy.recycler.model.NewsModel;

import java.util.List;

/**
 * Created by Jeremy on 04/02/16.
 */
public interface OnTaskCompleted {
    void onTaskCompleted(List<NewsModel> data);
}
