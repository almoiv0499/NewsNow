package com.application.newsnow.fragment;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.newsnow.R;
import com.application.newsnow.adapter.SectionAdapter;
import com.application.newsnow.enums.Category;
import com.application.newsnow.enums.Link;
import com.application.newsnow.model.Section;
import com.application.newsnow.util.OnSectionClickListener;

import java.util.ArrayList;
import java.util.List;

public class SectionFragment extends Fragment implements OnSectionClickListener {

    private static final String RETURN = "return";
    private static final String SECTION = "Sections";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_section, container, false);

        initRecyclerView(view);

        return view;
    }

    @Override
    public void onSectionListener(Section section) {
        Fragment fragment = SectionNewsFeedFragment.Companion.getInstance(section);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.news_fragment_container, fragment, RETURN)
                .addToBackStack(RETURN)
                .commit();
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerViewSection = view.findViewById(R.id.section_recyclerView);
        recyclerViewSection.setHasFixedSize(true);

        SectionAdapter adapter = new SectionAdapter(this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(view.getContext());
        recyclerViewSection.setAdapter(adapter);
        recyclerViewSection.setLayoutManager(manager);

        adapter.addSections(generateAll());
    }

    private List<Section> generateAll() {
        List<Section> sections = new ArrayList<>();
        sections.add(new Section(Link.LINK_ARTS.getLink(), Category.ARTS.getCategory()));
        sections.add(new Section(Link.LINK_BUSSINESS.getLink(), Category.BUSINESS.getCategory()));
        sections.add(new Section(Link.LINK_AUTO.getLink(), Category.AUTO.getCategory()));
        sections.add(new Section(Link.LINK_FASHION.getLink(), Category.FASHION.getCategory()));
        sections.add(new Section(Link.LINK_FOOD.getLink(), Category.FOOD.getCategory()));
        sections.add(new Section(Link.LINK_OPINION.getLink(), Category.OPINION.getCategory()));
        sections.add(new Section(Link.LINK_TECHNOLOGY.getLink(), Category.TECHNOLOGY.getCategory()));
        sections.add(new Section(Link.LINK_REALESTATE.getLink(), Category.REAL_ESTATE.getCategory()));
        sections.add(new Section(Link.LINK_MOVIES.getLink(), Category.MOVIES.getCategory()));
        sections.add(new Section(Link.LINK_SPORTS.getLink(), Category.SPORTS.getCategory()));
        return sections;
    }

    public static SectionFragment getInstance() {
        return new SectionFragment();
    }

}