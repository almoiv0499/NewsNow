package com.application.newsnow.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.newsnow.R
import com.application.newsnow.adapter.NewsAdapter
import com.application.newsnow.data.repository.FetchNewsRepositoryImpl
import com.application.newsnow.data.retrofit.RetrofitInstance
import com.application.newsnow.databinding.FragmentSectionNewsFeedBinding
import com.application.newsnow.domain.usecase.GetNewsByCategoryUseCase
import com.application.newsnow.model.NewsView
import com.application.newsnow.model.Section
import com.application.newsnow.util.OnNewsListener
import com.application.newsnow.viewmodel.SectionNewsViewModel
import com.application.newsnow.viewmodelfactory.SectionNewsViewModelFactory

class SectionNewsFeedFragment : Fragment(), OnNewsListener {

    companion object {
        private const val SECTION_KEY_BUNDLE = "section_key"
        private const val RETURN = "return"

        fun getInstance(section: Section): SectionNewsFeedFragment {
            val fragment = SectionNewsFeedFragment()
            val bundle = Bundle()
            bundle.putSerializable(SECTION_KEY_BUNDLE, section)
            fragment.arguments = bundle
            return fragment
        }
    }

    private val getNewsByCategoryUseCase by lazy {
        GetNewsByCategoryUseCase(
            repository = FetchNewsRepositoryImpl(
                api = RetrofitInstance.getInstance().api
            )
        )
    }

    private val viewModel: SectionNewsViewModel by lazy {
        ViewModelProvider(
            this,
            SectionNewsViewModelFactory(getNewsByCategoryUseCase)
        )[SectionNewsViewModel::class.java]
    }
    private val sectionAdapter by lazy { NewsAdapter(this) }
    private lateinit var binding: FragmentSectionNewsFeedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSectionNewsFeedBinding.inflate(inflater, container, false)

        val section = arguments?.getSerializable(SECTION_KEY_BUNDLE) as Section

        setToolbar(section)
        initRecyclerView()
        fetchNewsByCategory(section)

        return binding.root
    }

    private fun setToolbar(section: Section) {
        with(binding.toolbarNewsSectionFeed) {
            inflateMenu(R.menu.menu_top_news)
            title = section.section.uppercase()
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_new_24)
            setNavigationOnClickListener { requireActivity().onBackPressed() }
        }
    }

    private fun initRecyclerView() {
        with(binding.listNewsSection) {
            setHasFixedSize(true)
            val manager = LinearLayoutManager(context)
            layoutManager = manager
            adapter = sectionAdapter
        }
    }

    private fun fetchNewsByCategory(section: Section) {
        viewModel.fetchNewsByCategory(section)

        viewModel.news.observe(requireActivity()) { response ->
            binding.loadNewsSection.visibility = View.GONE
            sectionAdapter.addPosters(response.results)
        }

        viewModel.error.observe(requireActivity()) { error ->
            binding.loadNewsSection.visibility = View.GONE
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onNewsClick(news: NewsView) {
        val fragment = NewsDetailFragment.getInstance(news)

        activity?.let {
            it.supportFragmentManager.commit {
                add(R.id.news_fragment_container, fragment, RETURN)
                addToBackStack(RETURN)
            }
        }
    }

}