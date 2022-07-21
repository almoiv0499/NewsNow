package com.application.newsnow.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.application.newsnow.R;
import com.application.newsnow.adapter.SearchAdapter;
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

public class SearchFragment extends Fragment implements OnNewsListener {

    private static final String SEARCH = "Search";
    private static final String INPUT_DATA = "Type here...";
    private static final String RETURN_SEARCH = "return_search";
    private static final String FAIL_SEARCH = "Oops, something wrong!";

    private List<News> news = new ArrayList<>();
    private SearchAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        setToolbar(view);

        initRecyclerView(view);

        setSearchView(view);

        generateCall();

        return view;
    }

    private void setToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar_search);
        toolbar.inflateMenu(R.menu.menu_top_news);
        toolbar.setTitle(SEARCH);
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.search_recyclerView);
        recyclerView.setHasFixedSize(true);

        adapter = new SearchAdapter(this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext());

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private void setSearchView(View view) {
        SearchView searchView = view.findViewById(R.id.search);
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint(INPUT_DATA);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return true;
            }
        });
    }

    private void generateCall() {
        Call<ListNews> call = RetrofitInstance.getInstance()
                .getApi()
                .getNewsByCategory(Category.TOP.getCategory());

        call.enqueue(new Callback<ListNews>() {
            @Override
            public void onResponse(Call<ListNews> call, Response<ListNews> response) {
                if (response.isSuccessful()) {
                    news = response.body().getResults();
                    adapter.addSearchedNews(news);
                }
            }

            @Override
            public void onFailure(Call<ListNews> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), FAIL_SEARCH, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onNewsClick(News news) {
        Fragment fragment = NewsDetailFragment.getInstance(news);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.news_fragment_container, fragment, RETURN_SEARCH)
                .addToBackStack(RETURN_SEARCH)
                .commit();
    }

}