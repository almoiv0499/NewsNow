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
        RecyclerView.Adapter adapter = new NewsAdapter(posters);
        RecyclerView recyclerViewForNews = findViewById(R.id.posters_list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);

        recyclerViewForNews.setHasFixedSize(true);

        recyclerViewForNews.setAdapter(adapter);
        recyclerViewForNews.setLayoutManager(manager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top_news, menu);
        return true;
    }
}