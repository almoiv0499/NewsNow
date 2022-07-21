package com.application.newsnow.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.newsnow.R
import com.application.newsnow.adapter.NewsAdapter
import com.application.newsnow.model.News
import com.application.newsnow.retrofit.RetrofitInstance
import com.application.newsnow.util.OnNewsListener
import kotlinx.coroutines.*

class TopNewsFragment : Fragment(), OnNewsListener {

    companion object {
        private const val RETURN_BACK: String = "return_back"
    }

    private val adapter: NewsAdapter by lazy { NewsAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_top_news, container, false)

        setToolbar(view)

        initRecyclerView(view)

        viewLifecycleOwner.lifecycleScope.launch {
            fetchAllNews(view)
        }

        return view
    }

    private fun setToolbar(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_top_news)
    }

    private fun initRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.posters_list)
        recyclerView.setHasFixedSize(true)

        val manager = LinearLayoutManager(view.context)
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
    }

    private suspend fun fetchAllNews(view: View) {
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val results = getNews()
        when(results.results.isNotEmpty()) {
            true -> {
                progressBar.visibility = View.GONE
                adapter.addPosters(results.results)
            }
            false -> Toast.makeText(
                activity?.applicationContext,
                getString(R.string.fail_toast),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    private suspend fun getNews() = withContext(Dispatchers.IO) {
        RetrofitInstance.getInstance().api.getNewsForTopNewsScreen()
    }

    override fun onNewsClick(news: News?) {
        val fragment = NewsDetailFragment.getInstance(news)

        activity?.let {
            it.supportFragmentManager.beginTransaction()
                .add(R.id.news_fragment_container, fragment, RETURN_BACK)
                .addToBackStack(RETURN_BACK)
                .commit()
        }
    }
}


