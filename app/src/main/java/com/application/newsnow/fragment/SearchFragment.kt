package com.application.newsnow.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.newsnow.R
import com.application.newsnow.adapter.SearchAdapter
import com.application.newsnow.data.repository.FetchNewsRepositoryImpl
import com.application.newsnow.data.retrofit.RetrofitInstance
import com.application.newsnow.databinding.FragmentSearchBinding
import com.application.newsnow.domain.usecase.GetSearchedNewsUseCase
import com.application.newsnow.fragment.base.BaseFragment
import com.application.newsnow.model.NewsView
import com.application.newsnow.util.OnNewsListener
import com.application.newsnow.viewmodel.SearchNewsViewModel
import com.application.newsnow.viewmodelfactory.SearchNewsViewModelFactory

class SearchFragment : BaseFragment<SearchNewsViewModel>(), OnNewsListener {

    companion object {
        fun getInstance() = SearchFragment()
    }

    private lateinit var binding: FragmentSearchBinding

    private val getSearchedNewsUseCase by lazy {
        GetSearchedNewsUseCase(
            repository = FetchNewsRepositoryImpl(
                api = RetrofitInstance.getInstance().api
            )
        )
    }
    override val viewModel by lazy {
        ViewModelProvider(
            this,
            SearchNewsViewModelFactory(getSearchedNewsUseCase = getSearchedNewsUseCase)
        )[SearchNewsViewModel::class.java]
    }
    private val searchAdapter: SearchAdapter by lazy { SearchAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSearchBinding.inflate(inflater, container, false)

        initRecyclerView()
        setSearchView()
        fetchNews()

        return binding.root
    }

    private fun initRecyclerView() {
        with(binding.searchRecyclerView) {
            setHasFixedSize(true)
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setSearchView() {
        with(binding.search) {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    searchAdapter.filter.filter(newText)
                    return true
                }
            })
        }
    }

    private fun fetchNews() {
        viewModel.liveDataNews.observe(requireActivity()) { response ->
            binding.progressBarSearch.visibility = View.GONE
            searchAdapter.addSearchedNews(response.results)
        }

        viewModel.liveDataException.observe(requireActivity()) { error ->
            binding.progressBarSearch.visibility = View.GONE
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onNewsClick(news: NewsView) {
        viewModel.navigateToDetails(newsView = news)
    }

}