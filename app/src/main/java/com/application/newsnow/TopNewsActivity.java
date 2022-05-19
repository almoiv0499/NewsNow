package com.application.newsnow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;

import com.application.newsnow.adapter.NewsAdapter;
import com.application.newsnow.model.NewsPoster;

import java.util.ArrayList;
import java.util.List;

public class TopNewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_news);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<NewsPoster> posters = new ArrayList<>();

        NewsAdapter adapter = new NewsAdapter();
        RecyclerView recyclerViewForNews = findViewById(R.id.posters_list);
        recyclerViewForNews.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerViewForNews.setLayoutManager(manager);
        recyclerViewForNews.setAdapter(adapter);

        adapter.addPosters(posters);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top_news, menu);
        return true;
    }

}