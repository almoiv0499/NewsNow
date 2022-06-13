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
import com.application.newsnow.enums.Category;
import com.application.newsnow.model.ListNews;
import com.application.newsnow.model.News;
import com.application.newsnow.retrofit.RetrofitInstance;
import com.application.newsnow.util.OnNewsListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopNewsFragment extends Fragment implements OnNewsListener {

    private static final String RETURN_BACK = "return_back";
    private static final String FAIL = "Oops, something wrong!";

    private List<News> news = new ArrayList<>();
    private NewsAdapter adapter;
    private ProgressBar load;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_top_news, container, false);

        setToolbar(view);

        load = view.findViewById(R.id.load);

        initRecyclerView(view);

        generateCall();

        return view;
    }

    private void setToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_top_news);
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
        Call<ListNews> call = RetrofitInstance.getInstance()
                .getApi()
                .getAllNews(Category.TOP.getCategory());

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

    private void initRecyclerView(View view) {
        RecyclerView recyclerViewForNews = view.findViewById(R.id.posters_list);
        recyclerViewForNews.setHasFixedSize(true);

        adapter = new NewsAdapter(this);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext());
        recyclerViewForNews.setLayoutManager(manager);
        recyclerViewForNews.setAdapter(adapter);
    }
}