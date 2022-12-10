package com.application.newsnow.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.newsnow.R;
import com.application.newsnow.model.NewsView;
import com.application.newsnow.util.OnNewsListener;
import com.squareup.picasso.Picasso;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    private TextView author, publishedAt, title;
    private ImageView urlToImage;

    private NewsView news;
    private OnNewsListener newsListener;

    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
        author = itemView.findViewById(R.id.author_top_news);
        publishedAt = itemView.findViewById(R.id.published_at_top_news);
        title = itemView.findViewById(R.id.title_top_news);
        urlToImage = itemView.findViewById(R.id.image_top_news);

        itemView.setOnClickListener(view -> newsListener.onNewsClick(news));
    }

    public void bind(NewsView news, OnNewsListener newsListener) {
        this.news = news;
        this.newsListener = newsListener;
        author.setText(news.getMultimedia().get(0).getAuthor());
        publishedAt.setText(news.getPublishedAt());
        title.setText(news.getTitle());
        Picasso.get()
                .load(news.getMultimedia().get(0).getImage())
                .error(R.drawable.ic_person)
                .into(urlToImage);
    }

}
