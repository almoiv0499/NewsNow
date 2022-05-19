package com.application.newsnow.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.newsnow.OnNewsListener;
import com.application.newsnow.R;
import com.application.newsnow.model.NewsPoster;
import com.application.newsnow.viewholder.NewsViewHolder;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsViewHolder> {

    private List<NewsPoster> posters = new ArrayList<>();
    private OnNewsListener newsListener;

    public void addPosters(List<NewsPoster> postersList, OnNewsListener newsListener) {
        posters.clear();
        posters.addAll(postersList);
        this.newsListener = newsListener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_top_news_layout, parent, false);
        return new NewsViewHolder(view, newsListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(posters.get(position));
    }

    @Override
    public int getItemCount() {
        return posters.size();
    }
}
