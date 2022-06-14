package com.application.newsnow.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.newsnow.R;
import com.application.newsnow.model.News;
import com.application.newsnow.util.OnNewsListener;
import com.application.newsnow.viewholder.NewsViewHolder;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private List<News> news = new ArrayList<>();
    private OnNewsListener newsListener;

    public NewsAdapter(OnNewsListener newsListener) {
        this.newsListener = newsListener;
    }

    public void addPosters(List<News> newsList) {
        news.clear();
        news.addAll(newsList);
        notifyDataSetChanged();
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
        holder.bind(news.get(position), newsListener);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }
}
