package com.application.newsnow.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.newsnow.R;
import com.application.newsnow.TopNewsActivity;
import com.application.newsnow.model.News;
import com.squareup.picasso.Picasso;

public class NewsDetailFragment extends Fragment {

    private static final String NEWS_KEY_BUNDLE = "news_object";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news_detail, container, false);

        TextView author = view.findViewById(R.id.author_news_detail);
        TextView publishedAt = view.findViewById(R.id.time_ago_news_detail);
        TextView title = view.findViewById(R.id.title_news_detail);
        TextView description = view.findViewById(R.id.description_news_detail);
        ImageView image = view.findViewById(R.id.image_news_detail);

        Toolbar toolbarNewsDetail = view.findViewById(R.id.toolbar_news_detail);
        ((TopNewsActivity) getActivity()).setSupportActionBar(toolbarNewsDetail);
        setHasOptionsMenu(true);

        ((TopNewsActivity) getActivity())
                .getSupportActionBar()
                .setDisplayHomeAsUpEnabled(true);

        ((TopNewsActivity) getActivity())
                .getSupportActionBar()
                .setDisplayShowTitleEnabled(false);

        ((TopNewsActivity) getActivity())
                .getSupportActionBar()
                .setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_new_24);

        News poster = (News) getArguments().getSerializable(NEWS_KEY_BUNDLE);
        if (poster != null) {
            author.setText(poster.getAuthor());
            publishedAt.setText(poster.getPublishedAt());
            title.setText(poster.getTitle());
            description.setText(poster.getDescription());
            Picasso.get().load(poster.getUrlToImage()).into(image);
        }
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_news_detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                break;
        }
        return true;
    }

    public static NewsDetailFragment getInstance(News news) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(NEWS_KEY_BUNDLE, news);
        fragment.setArguments(bundle);
        return fragment;
    }

}