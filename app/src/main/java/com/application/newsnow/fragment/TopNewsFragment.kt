package com.application.newsnow.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.app.App
import com.application.newsnow.R
import com.application.newsnow.adapter.NewsAdapter
import com.application.newsnow.databinding.FragmentTopNewsBinding
import com.application.newsnow.fragment.base.BaseFragment
import com.application.newsnow.model.NewsView
import com.application.newsnow.util.OnNewsListener
import com.application.newsnow.viewmodel.TopNewsViewModel
import com.application.newsnow.viewmodelfactory.NewsViewModelFactory
import javax.inject.Inject

class TopNewsFragment : BaseFragment<TopNewsViewModel>(), OnNewsListener {

    @Inject
    lateinit var viewModelFactory: NewsViewModelFactory

    override val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this, viewModelFactory)[TopNewsViewModel::class.java]
    }

    private val newsAdapter: NewsAdapter by lazy { NewsAdapter(this) }

    private lateinit var topNewsBinding: FragmentTopNewsBinding

    override fun onAttach(context: Context) {
        (activity?.applicationContext as App).component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        topNewsBinding = FragmentTopNewsBinding.inflate(inflater, container, false)

        initRecyclerView()
        fetchNews()

        with(topNewsBinding) {

            btnSection.setOnClickListener {
                val fragment = SectionFragment.getInstance()

                activity?.let {
                    it.supportFragmentManager.commit {
                        add(R.id.news_fragment_container, fragment)
                        addToBackStack(null)
                    }
                }
            }
        }

        return topNewsBinding.root
    }

    private fun initRecyclerView() {
        with(topNewsBinding.postersList) {
            setHasFixedSize(true)
            val manager = LinearLayoutManager(context)
            layoutManager = manager
            adapter = newsAdapter
        }
    }

    private fun fetchNews() {
        viewModel.liveDataNews.observe(requireActivity()) { response ->
            topNewsBinding.progressBar.visibility = View.GONE
            newsAdapter.addPosters(response.results)
        }
        viewModel.liveDataException.observe(requireActivity()) { error ->
            topNewsBinding.progressBar.visibility = View.GONE
            Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onNewsClick(news: NewsView) {
        viewModel.navigateToDetails(newsView = news)
    }

}


