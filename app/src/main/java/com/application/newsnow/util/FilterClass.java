package com.application.newsnow.util;

import android.widget.Filter;

import com.application.newsnow.adapter.SearchAdapter;
import com.application.newsnow.domain.model.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FilterClass {

    public static Filter setFilter(List<News> news, List<News> filtered, SearchAdapter adapter) {
        return new Filter() {
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
                adapter.notifyDataSetChanged();
            }
        };
    }
}
