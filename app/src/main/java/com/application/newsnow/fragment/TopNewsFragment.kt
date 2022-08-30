package com.application.newsnow.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.newsnow.R
import com.application.newsnow.adapter.NewsAdapter
import com.application.newsnow.databinding.FragmentTopNewsBinding
import com.application.newsnow.domain.model.News
import com.application.newsnow.util.OnNewsListener
import com.application.newsnow.viewmodel.TopNewsViewModel
import com.application.newsnow.viewmodelfactory.TopNewsViewModelFactory

class TopNewsFragment : Fragment(), OnNewsListener {

    companion object {
        private const val RETURN_BACK: String = "return_back"
    }

    private val newsAdapter: NewsAdapter by lazy { NewsAdapter(this) }
    private val viewModel: TopNewsViewModel by lazy {
        ViewModelProvider(
            this,
            TopNewsViewModelFactory()
        )[TopNewsViewModel::class.java]
    }
    private lateinit var topNewsBinding: FragmentTopNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        topNewsBinding = FragmentTopNewsBinding.inflate(inflater, container, false)

        setToolbar()
        initRecyclerView()
        fetchNews()

        return topNewsBinding.root
    }

    private fun setToolbar() {
        topNewsBinding.toolbar.inflateMenu(R.menu.menu_top_news)
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
        viewModel.news.observe(requireActivity()) { response ->
            topNewsBinding.progressBar.visibility = View.GONE
            newsAdapter.addPosters(response.results)
        }
        viewModel.error.observe(requireActivity()) { error ->
            topNewsBinding.progressBar.visibility = View.GONE
            Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onNewsClick(news: News) {
        val fragment = NewsDetailFragment.getInstance(news)
        activity?.let {
            it.supportFragmentManager.beginTransaction()
                .add(R.id.news_fragment_container, fragment, RETURN_BACK)
                .addToBackStack(RETURN_BACK)
                .commit()
        }
    }

}


