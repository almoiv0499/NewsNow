package com.application.newsnow.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.newsnow.R
import com.application.newsnow.adapter.NewsAdapter
import com.application.newsnow.enums.Category
import com.application.newsnow.model.ListNews
import com.application.newsnow.model.News
import com.application.newsnow.retrofit.RetrofitInstance
import com.application.newsnow.util.OnNewsListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopNewsFragment : Fragment(), OnNewsListener {

    companion object {
        private const val RETURN_BACK: String = "return_back"
        private const val FAIL: String = "Oops, something wrong!"
    }

    private var news: List<News> = ArrayList<News>()
    private var adapter: NewsAdapter? = null
    private var load: ProgressBar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_top_news, container, false)

        setToolbar(view)

        load = view.findViewById(R.id.load)

        initRecyclerView(view)

        generateCall()

        return view
    }

    private fun setToolbar(view: View) {
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.menu_top_news)
    }

    private fun initRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.posters_list)
        recyclerView.setHasFixedSize(true)

        adapter = NewsAdapter(this)

        val manager = LinearLayoutManager(view.context)
        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
    }

    private fun generateCall() {
        val call = RetrofitInstance.getInstance()
            .api
            .getAllNews(Category.TOP.category)

        call.enqueue(object : Callback<ListNews> {
            override fun onResponse(call: Call<ListNews>, response: Response<ListNews>) {
                if (response.isSuccessful) {
                    load?.visibility = View.GONE

                    news = response.body()?.results as List<News>

                    adapter?.addPosters(news)
                }
            }

            override fun onFailure(call: Call<ListNews>, t: Throwable) {
                Toast.makeText(activity?.applicationContext, FAIL, Toast.LENGTH_SHORT).show()
            }
        })
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