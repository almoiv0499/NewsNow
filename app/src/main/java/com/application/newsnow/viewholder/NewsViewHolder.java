package com.application.newsnow.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.newsnow.R;
import com.application.newsnow.domain.model.News;
import com.application.newsnow.util.OnNewsListener;
import com.squareup.picasso.Picasso;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    private TextView author, publishedAt, title;
    private ImageView urlToImage;

    private News news;
    private OnNewsListener newsListener;

    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
        author = itemView.findViewById(R.id.author_news);
        publishedAt = itemView.findViewById(R.id.time_ago);
        title = itemView.findViewById(R.id.title_news_poster);
        urlToImage = itemView.findViewById(R.id.image_news_poster);

        itemView.setOnClickListener(view -> newsListener.onNewsClick(news));
    }

    public void bind(News news, OnNewsListener newsListener) {
        this.news = news;
        this.newsListener = newsListener;
        author.setText(news.getMultimedia().get(0).getAuthor());
        publishedAt.setText(news.getPublishedAt());
        title.setText(news.getTitle());
        Picasso.get().load(news.getMultimedia().get(0).getImage()).into(urlToImage);
    }

}
