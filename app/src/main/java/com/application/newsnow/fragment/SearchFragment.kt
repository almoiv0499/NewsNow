package com.application.newsnow.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.newsnow.R
import com.application.newsnow.adapter.SearchAdapter
import com.application.newsnow.data.repository.FetchNewsRepositoryImpl
import com.application.newsnow.data.retrofit.RetrofitInstance
import com.application.newsnow.domain.usecase.GetSearchedNewsUseCase
import com.application.newsnow.model.NewsView
import com.application.newsnow.util.OnNewsListener
import com.application.newsnow.viewmodel.SearchNewsViewModel
import com.application.newsnow.viewmodelfactory.SearchedNewsViewModelFactory

class SearchFragment : Fragment(), OnNewsListener {

    companion object {
        private const val SEARCH = "Search"
        private const val INPUT_DATA = "Type here..."
        private const val RETURN_SEARCH = "return_search"

        fun getInstance() = SearchFragment()
    }

    private val getSearchedNewsUseCase by lazy {
        GetSearchedNewsUseCase(
            repository = FetchNewsRepositoryImpl(
                api = RetrofitInstance.getInstance().api
            )
        )
    }

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            SearchedNewsViewModelFactory(getSearchedNewsUseCase)
        )[SearchNewsViewModel::class.java]
    }
    private val adapter: SearchAdapter by lazy { SearchAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_search, container, false)

        setToolbar(view)
        initRecyclerView(view)
        setSearchView(view)
        fetchNews()

        return view
    }

    private fun setToolbar(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar_search)
        toolbar.inflateMenu(R.menu.menu_top_news)
        toolbar.title = SEARCH
    }

    private fun initRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.search_recyclerView)
        recyclerView.setHasFixedSize(true)
        val manager = LinearLayoutManager(view.context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = manager
    }

    private fun setSearchView(view: View) {
        val searchView = view.findViewById<SearchView>(R.id.search)
        searchView.isIconifiedByDefault = false
        searchView.queryHint = INPUT_DATA
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })
    }

    private fun fetchNews() {
        viewModel.searchedNews.observe(requireActivity()) { response ->
            adapter.addSearchedNews(response.results)
        }

        viewModel.error.observe(requireActivity()) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onNewsClick(news: NewsView?) {
        val fragment = NewsDetailFragment.getInstance(news)
        activity?.let {
            it.supportFragmentManager.beginTransaction()
                .add(R.id.news_fragment_container, fragment, RETURN_SEARCH)
                .addToBackStack(RETURN_SEARCH)
                .commit()
        }
    }

}