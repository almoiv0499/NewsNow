package com.application.newsnow.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.application.newsnow.OnNewsListener;
import com.application.newsnow.R;
import com.application.newsnow.TopNewsActivity;
import com.application.newsnow.adapter.NewsAdapter;
import com.application.newsnow.model.ListNews;
import com.application.newsnow.model.News;
import com.application.newsnow.retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopNewsFragment extends Fragment implements OnNewsListener {

    private static final String RETURN_BACK = "return_back";

    private List<News> news = new ArrayList<>();
    private NewsAdapter adapter;
    private RecyclerView recyclerViewForNews;
    private ProgressBar load;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_top_news, container, false);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((TopNewsActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        load = view.findViewById(R.id.load);

        recyclerViewForNews = view.findViewById(R.id.posters_list);
        recyclerViewForNews.setHasFixedSize(true);

        adapter = new NewsAdapter(this);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext());
        recyclerViewForNews.setLayoutManager(manager);
        recyclerViewForNews.setAdapter(adapter);

        generateCall();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_top_news, menu);
    }

    @Override
    public void onNewsClick(News poster) {

        Fragment fragment = NewsDetailFragment.getInstance(poster);

        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.news_fragment_container, fragment, RETURN_BACK)
                .addToBackStack(RETURN_BACK)
                .commit();
    }

    private void generateCall() {
        Call<ListNews> call = RetrofitInstance.getInstance().getApi().getAllNews();
        call.enqueue(new Callback<ListNews>() {
            @Override
            public void onResponse(Call<ListNews> call, Response<ListNews> response) {
                if (response.isSuccessful()) {
                    load.setVisibility(View.GONE);

                    news = response.body().getArticles();

                    adapter.addPosters(news);
                }
            }

            @Override
            public void onFailure(Call<ListNews> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "FAIL", Toast.LENGTH_SHORT).show();
            }
        });
    }
}