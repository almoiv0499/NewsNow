package com.application.newsnow.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.newsnow.R;
import com.application.newsnow.model.NewsView;
import com.squareup.picasso.Picasso;

public class NewsDetailFragment extends Fragment {

    private static final String NEWS_KEY_BUNDLE = "news_object";

    private TextView author, publishedAt, title, description;
    private ImageView image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news_detail, container, false);

        initViews(view);
        setToolbar(view);

        NewsView poster = (NewsView) getArguments().getSerializable(NEWS_KEY_BUNDLE);
        if (poster != null) {
            author.setText(poster.getMultimedia().get(0).getAuthor());
            publishedAt.setText(poster.getPublishedAt());
            title.setText(poster.getTitle());
            description.setText(poster.getDescription());
            Picasso.get().load(poster.getMultimedia().get(0).getImage()).into(image);
        }

        return view;
    }

    private void setToolbar(View view) {
        Toolbar toolbarNewsDetail = view.findViewById(R.id.toolbar_news_detail);
        toolbarNewsDetail.setNavigationOnClickListener(viewClick -> getActivity().onBackPressed());
    }

    public static NewsDetailFragment getInstance(NewsView news) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(NEWS_KEY_BUNDLE, news);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void initViews(View view) {
        author = view.findViewById(R.id.author_news_detail);
        publishedAt = view.findViewById(R.id.published_news_detail);
        title = view.findViewById(R.id.title_news_detail);
        description = view.findViewById(R.id.description_news_detail);
        image = view.findViewById(R.id.image_news_detail);
    }

}