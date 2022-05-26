package com.application.newsnow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.application.newsnow.adapter.NewsAdapter;
import com.application.newsnow.model.ListNews;
import com.application.newsnow.model.News;
import com.application.newsnow.retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopNewsActivity extends AppCompatActivity implements OnNewsListener {

    private List<News> news;
    private NewsAdapter adapter;
    private RecyclerView recyclerViewForNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_news);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerViewForNews = findViewById(R.id.posters_list);
        recyclerViewForNews.setHasFixedSize(true);

        news = new ArrayList<>();
        adapter = new NewsAdapter(this);

        generateCall();


        adapter.addPosters(news);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top_news, menu);
        return true;
    }

    @Override
    public void onNewsClick(News poster) {
        Intent intent = new Intent(this, NewsDetailActivity.class);
        intent.putExtra(getString(R.string.poster_keyIntent), poster);
        startActivity(intent);
    }

    private void generateCall() {
        Call<ListNews> call = RetrofitInstance.getInstance().getApi().getAllNews();
        call.enqueue(new Callback<ListNews>() {
            @Override
            public void onResponse(Call<ListNews> call, Response<ListNews> response) {
                if (response.isSuccessful()) {
                    news = response.body().getArticles();

                    adapter.addPosters(news);

                    RecyclerView.LayoutManager manager = new LinearLayoutManager(TopNewsActivity.this);
                    recyclerViewForNews.setLayoutManager(manager);
                    recyclerViewForNews.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ListNews> call, Throwable t) {
                Toast.makeText(TopNewsActivity.this, "FAIL", Toast.LENGTH_SHORT).show();
            }
        });
    }

}