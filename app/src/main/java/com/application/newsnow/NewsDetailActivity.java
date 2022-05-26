package com.application.newsnow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.newsnow.model.News;

public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        TextView section = findViewById(R.id.author_news_detail);
        TextView timeAgo = findViewById(R.id.time_ago_news_detail);
        TextView title = findViewById(R.id.title_news_detail);
        ImageView image = findViewById(R.id.image_news_detail);

        Toolbar toolbarNewsDetail = findViewById(R.id.toolbar_news_detail);
        setSupportActionBar(toolbarNewsDetail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_new_24);

        News poster = getIntent().getParcelableExtra(getString(R.string.poster_keyIntent));
        if(poster != null) {
            section.setText(poster.getAuthor());
            timeAgo.setText(poster.getPublishedAt());
            title.setText(poster.getTitle());
            image.setImageResource(Integer.parseInt(poster.getUrlToImage()));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_news_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return true;
    }

}