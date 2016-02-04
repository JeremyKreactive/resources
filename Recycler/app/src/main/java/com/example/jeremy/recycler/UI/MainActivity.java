package com.example.jeremy.recycler.UI;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jeremy.recycler.service.NewsLoader;
import com.example.jeremy.recycler.model.NewsModel;
import com.example.jeremy.recycler.OnTaskCompleted;
import com.example.jeremy.recycler.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnTaskCompleted {

    RecyclerView recyclerView;
    NewsAdapter newsAdapter;

    public Context getContext() {
        return (Context)this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.cardList);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        NewsLoader ressourceLoader = new NewsLoader(getContext(), this);
        ressourceLoader.execute();

    }


    @Override
    public void onTaskCompleted(List<NewsModel> data) {
        newsAdapter = new NewsAdapter(data, getContext());
        recyclerView.setAdapter(newsAdapter);
    }
}
