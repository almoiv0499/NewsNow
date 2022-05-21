package com.application.newsnow.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.newsnow.OnNewsListener;
import com.application.newsnow.R;
import com.application.newsnow.model.NewsPoster;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    private TextView section;
    private TextView timeAgo;
    private TextView posterTitle;
    private ImageView posterImage;

    private NewsPoster newsPoster;
    private OnNewsListener newsListener;

    public NewsViewHolder(@NonNull View itemView) {
        super(itemView);
        section = itemView.findViewById(R.id.section_news);
        timeAgo = itemView.findViewById(R.id.time_ago);
        posterTitle = itemView.findViewById(R.id.title_news_poster);
        posterImage = itemView.findViewById(R.id.image_news_poster);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newsListener.onNewsClick(newsPoster);
            }
        });
    }

    public void bind(NewsPoster poster, OnNewsListener newsListener) {
        this.newsPoster = poster;
        this.newsListener = newsListener;
        section.setText(poster.getSection());
        timeAgo.setText(poster.getTimeAgo());
        posterTitle.setText(poster.getPosterTitle());
        posterImage.setImageResource(poster.getPosterImageResource());
    }
}
