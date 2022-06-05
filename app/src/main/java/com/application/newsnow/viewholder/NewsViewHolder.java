package com.application.newsnow.viewholder;

import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.application.newsnow.OnNewsListener;
import com.application.newsnow.R;
import com.application.newsnow.model.Multimedia;
import com.application.newsnow.model.News;
import com.squareup.picasso.Picasso;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    public static String PATTERN_DATE_TIME = "dd.MM.yyyy HH:ss";
    public static String SPLIT_WORD = " for";
    public static int INDEX_ZERO = 0;

    private TextView author, publishedAt, title;
    private ImageView urlToImage;

    private News news;
    private Multimedia multimedia;
    private OnNewsListener newsListener;

    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
        author = itemView.findViewById(R.id.author_news);
        publishedAt = itemView.findViewById(R.id.time_ago);
        title = itemView.findViewById(R.id.title_news_poster);
        urlToImage = itemView.findViewById(R.id.image_news_poster);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newsListener.onNewsClick(news);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void bind(News news, OnNewsListener newsListener) {
        this.news = news;
        this.newsListener = newsListener;
        author.setText(convertAuthor(news.getMultimedia().get(INDEX_ZERO).getAuthor()));
        publishedAt.setText(convertDateTime(news.getPublishedAt()));
        title.setText(news.getTitle());
        Picasso.get().load(news.getMultimedia().get(INDEX_ZERO).getImage()).into(urlToImage);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String convertDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern(PATTERN_DATE_TIME)
                .withZone(ZoneId.systemDefault());

        OffsetDateTime input = OffsetDateTime.parse(date);
        Instant instant = input.toInstant();
        return formatter.format(instant);
    }

    private String convertAuthor(String author) {
        return author.split(SPLIT_WORD)[INDEX_ZERO];
    }
}
