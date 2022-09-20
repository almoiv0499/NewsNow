package com.application.newsnow.util;

import android.widget.Filter;

import com.application.newsnow.adapter.SearchAdapter;
import com.application.newsnow.model.NewsView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FilterClass {

    public static Filter setFilter(List<NewsView> news, List<NewsView> filtered, SearchAdapter adapter) {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<NewsView> temp = new ArrayList<>();

                if (charSequence == null || charSequence.length() == 0) {
                    temp.addAll(filtered);
                } else {
                    String pattern = charSequence.toString().toLowerCase(Locale.ROOT).trim();
                    for (NewsView item : filtered) {
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
                news.addAll((List<NewsView>) filterResults.values);
                adapter.notifyDataSetChanged();
            }
        };
    }
}
