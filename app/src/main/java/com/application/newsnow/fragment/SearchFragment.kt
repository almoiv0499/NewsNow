package com.application.newsnow.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.newsnow.R
import com.application.newsnow.adapter.SearchAdapter
import com.application.newsnow.enums.ApiEnum
import com.application.newsnow.model.News
import com.application.newsnow.retrofit.RetrofitInstance
import com.application.newsnow.util.OnNewsListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchFragment : Fragment(), OnNewsListener {

    companion object {
        private const val SEARCH = "Search"
        private const val INPUT_DATA = "Type here..."
        private const val RETURN_SEARCH = "return_search"
    }

    private val disposable by lazy { CompositeDisposable() }
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
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                adapter.filter.filter(p0)
                return true
            }
        })
    }

    private fun fetchNews() {
        disposable.add(
            RetrofitInstance.getInstance().api.getNewsForSearchScreen(ApiEnum.API_KEY.value)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    adapter.addSearchedNews(response.results)
                }, {
                    Toast.makeText(
                        activity?.applicationContext,
                        getString(R.string.fail_toast),
                        Toast.LENGTH_SHORT
                    ).show()
                })
        )
    }

    override fun onNewsClick(news: News?) {
        val fragment = NewsDetailFragment.getInstance(news)
        activity?.let {
            it.supportFragmentManager.beginTransaction()
                .add(R.id.news_fragment_container, fragment, RETURN_SEARCH)
                .addToBackStack(RETURN_SEARCH)
                .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}