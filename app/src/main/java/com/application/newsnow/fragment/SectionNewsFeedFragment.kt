package com.application.newsnow.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.app.App
import com.application.newsnow.adapter.SearchAdapter
import com.application.newsnow.databinding.FragmentSectionNewsFeedBinding
import com.application.newsnow.fragment.base.BaseFragment
import com.application.newsnow.model.NewsView
import com.application.newsnow.model.Section
import com.application.newsnow.util.OnNewsListener
import com.application.newsnow.viewmodel.SectionNewsViewModel
import com.application.newsnow.viewmodelfactory.NewsViewModelFactory
import javax.inject.Inject

class SectionNewsFeedFragment : BaseFragment<SectionNewsViewModel>(), OnNewsListener {

    companion object {
        private const val SECTION_KEY_BUNDLE = "section_key"

        fun getInstance(section: Section): SectionNewsFeedFragment {
            val fragment = SectionNewsFeedFragment()
            val bundle = Bundle()
            bundle.putSerializable(SECTION_KEY_BUNDLE, section)
            fragment.arguments = bundle
            return fragment
        }
    }

    @Inject
    lateinit var viewModelFactory: NewsViewModelFactory

    override val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this, viewModelFactory)[SectionNewsViewModel::class.java]
    }

    private val searchAdapter by lazy { SearchAdapter(this) }

    private val section by lazy(LazyThreadSafetyMode.NONE) {
        arguments?.getSerializable(SECTION_KEY_BUNDLE) as Section
    }

    private lateinit var binding: FragmentSectionNewsFeedBinding

    override fun onAttach(context: Context) {
        (activity?.applicationContext as App).component.inject(this)
        super.onAttach(context)
        viewModel.fetchNewsByCategory(section)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSectionNewsFeedBinding.inflate(inflater, container, false)

        val section = arguments?.getSerializable(SECTION_KEY_BUNDLE) as Section

        setToolbar(section)
        initRecyclerView()
        setSearch()
        fetchNewsByCategory()

        return binding.root
    }

    private fun setToolbar(section: Section) {
        with(binding.toolbarNewsSectionFeed) {
            title = section.section.replaceFirstChar { it.uppercase() }
            setNavigationOnClickListener { requireActivity().onBackPressed() }
        }
    }

    private fun initRecyclerView() {
        with(binding.listNewsSection) {
            setHasFixedSize(true)
            val manager = LinearLayoutManager(context)
            layoutManager = manager
            adapter = searchAdapter
        }
    }

    private fun setSearch() {
        with(binding.searchNews) {
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

    private fun fetchNewsByCategory() {
        viewModel.liveDataNews.observe(requireActivity()) { response ->
            binding.loadNewsSection.visibility = View.GONE
            searchAdapter.addSearchedNews(response.results)
        }

        viewModel.liveDataException.observe(requireActivity()) { error ->
            binding.loadNewsSection.visibility = View.GONE
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onNewsClick(news: NewsView) {
        viewModel.navigateToDetails(newsView = news)
    }

}