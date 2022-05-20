package com.application.newsnow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.application.newsnow.adapter.NewsAdapter;
import com.application.newsnow.model.NewsPoster;

import java.util.ArrayList;
import java.util.List;

public class TopNewsActivity extends AppCompatActivity implements OnNewsListener {

    private List<NewsPoster> posters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_news);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        posters = new ArrayList<>();
        posters.add(new NewsPoster("Sport", "7 hours ago", "MU will play with KP",
                R.drawable.angry_dino));

        NewsAdapter adapter = new NewsAdapter(this);
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

    @Override
    public void onNewsClick(NewsPoster poster) {
        Intent intent = new Intent(this, NewsDetailActivity.class);
        intent.putExtra(getString(R.string.poster_keyIntent), new NewsPoster(poster.getSection(),
                poster.getTimeAgo(),
                poster.getPosterTitle(),
                poster.getPosterImageResource()));
        startActivity(intent);
    }
}