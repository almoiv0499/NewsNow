package com.application.newsnow.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.newsnow.R;
import com.application.newsnow.model.NewsPoster;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<NewsPoster> posters;
    private Context context;

    public NewsAdapter(List<NewsPoster> posters, Context context) {
        this.posters = posters;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_top_news_layout, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsPoster newsPoster = posters.get(position);
        holder.section.setText(newsPoster.getSection());
        holder.timeAgo.setText(newsPoster.getTimeAgo());
        holder.posterTitle.setText(newsPoster.getPosterTitle());
        holder.posterImage.setImageResource(newsPoster.getPosterImageResource());
    }

    @Override
    public int getItemCount() {
        return posters.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{

        private TextView section;
        private TextView timeAgo;
        private TextView posterTitle;
        private ImageView posterImage;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            section = itemView.findViewById(R.id.section_news);
            timeAgo = itemView.findViewById(R.id.time_ago);
            posterTitle = itemView.findViewById(R.id.title_news_poster);
            posterImage = itemView.findViewById(R.id.image_news_poster);
        }
    }
}
