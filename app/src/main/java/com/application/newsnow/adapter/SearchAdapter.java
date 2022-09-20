package com.application.newsnow.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.newsnow.R;
import com.application.newsnow.model.NewsView;
import com.application.newsnow.util.FilterClass;
import com.application.newsnow.util.OnNewsListener;
import com.application.newsnow.viewholder.NewsViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<NewsViewHolder> implements Filterable {

    private List<NewsView> news = new ArrayList<>();
    private List<NewsView> filtered;
    private OnNewsListener listener;

    public SearchAdapter(OnNewsListener listener) {
        this.listener = listener;
    }

    public void addSearchedNews(List<NewsView> filteredNews) {
        news.clear();
        news.addAll(filteredNews);
        filtered = new ArrayList<>(filteredNews);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_top_news_layout, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(news.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    @Override
    public Filter getFilter() {
        return FilterClass.setFilter(news, filtered, this);
    }

}
