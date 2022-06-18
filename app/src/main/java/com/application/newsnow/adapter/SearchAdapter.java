package com.application.newsnow.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.newsnow.R;
import com.application.newsnow.model.News;
import com.application.newsnow.util.OnNewsListener;
import com.application.newsnow.viewholder.NewsViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchAdapter extends RecyclerView.Adapter<NewsViewHolder> implements Filterable {

    private List<News> news = new ArrayList<>();
    private List<News> filtered;
    private OnNewsListener listener;

    public SearchAdapter(OnNewsListener listener) {
        this.listener = listener;
    }

    public void addSearchedNews(List<News> filteredNews) {
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
        return filter;
    }

    public Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<News> temp = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                temp.addAll(filtered);
            } else {
                String pattern = charSequence.toString().toLowerCase(Locale.ROOT).trim();
                for (News item : filtered) {
                    if (item.getTitle().toLowerCase(Locale.ROOT).contains(pattern)) {
                        temp.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = temp;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            news.clear();
            news.addAll((List<News>) filterResults.values);
            notifyDataSetChanged();
        }
    };
}
