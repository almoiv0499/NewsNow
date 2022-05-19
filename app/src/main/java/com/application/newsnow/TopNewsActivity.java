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
        posters.add(new NewsPoster("Sport", "7 hours ago", "MU will play with KP", R.drawable.ic_baseline_person_24));

        NewsAdapter adapter = new NewsAdapter();
        RecyclerView recyclerViewForNews = findViewById(R.id.posters_list);
        recyclerViewForNews.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerViewForNews.setLayoutManager(manager);
        recyclerViewForNews.setAdapter(adapter);

        adapter.addPosters(posters, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top_news, menu);
        return true;
    }

    @Override
    public void onNewsClick(int position) {
        Intent intent = new Intent(this, NewsDetailActivity.class);
        intent.putExtra("section", posters.get(position).getSection());
        intent.putExtra("time_ago", posters.get(position).getTimeAgo());
        intent.putExtra("title", posters.get(position).getPosterTitle());
        intent.putExtra("image", posters.get(position).getPosterImageResource());
        startActivity(intent);
    }
}