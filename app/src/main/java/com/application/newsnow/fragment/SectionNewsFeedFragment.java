package com.application.newsnow.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.application.newsnow.R;
import com.application.newsnow.adapter.NewsAdapter;
import com.application.newsnow.model.ListNews;
import com.application.newsnow.model.News;
import com.application.newsnow.model.Section;
import com.application.newsnow.retrofit.RetrofitInstance;
import com.application.newsnow.util.OnNewsListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SectionNewsFeedFragment extends Fragment implements OnNewsListener {

    private static final String SECTION_KEY_BUNDLE = "section_key";
    private static final String RETURN = "return";
    private static final String FAIL = "Oops, something wrong!";

    private List<News> news = new ArrayList<>();
    private NewsAdapter adapter;
    private Section section;

    private ProgressBar load;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_section_news_feed, container, false);

        section = (Section) getArguments().getSerializable(SECTION_KEY_BUNDLE);
        load = view.findViewById(R.id.load_news_section);

        setToolbar(view);

        initRecyclerView(view);

        generateCall();

        return view;
    }

    private void generateCall() {
        Call<ListNews> call = RetrofitInstance.getInstance()
                .getApi()
                .getAllNews(section.getSection());
        call.enqueue(new Callback<ListNews>() {
            @Override
            public void onResponse(@NonNull Call<ListNews> call, @NonNull Response<ListNews> response) {
                if (response.isSuccessful()) {
                    load.setVisibility(View.GONE);

                    news = response.body().getResults();

                    adapter.addPosters(news);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ListNews> call, @NonNull Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), FAIL, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onNewsClick(News poster) {
        Fragment fragment = NewsDetailFragment.getInstance(poster);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.news_fragment_container, fragment, RETURN)
                .addToBackStack(RETURN)
                .commit();
    }

    public static SectionNewsFeedFragment getInstance(Section section) {
        SectionNewsFeedFragment fragment = new SectionNewsFeedFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SECTION_KEY_BUNDLE, section);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void setToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar_news_section_feed);
        toolbar.inflateMenu(R.menu.menu_top_news);
        toolbar.setTitle(section.getSection().toUpperCase(Locale.ROOT));
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_new_24);
        toolbar.setNavigationOnClickListener(viewClick -> getActivity().onBackPressed());
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.list_news_section);
        recyclerView.setHasFixedSize(true);

        adapter = new NewsAdapter(this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
    }
}