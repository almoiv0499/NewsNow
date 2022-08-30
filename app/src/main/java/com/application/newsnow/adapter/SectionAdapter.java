package com.application.newsnow.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.newsnow.R;
import com.application.newsnow.domain.model.Section;
import com.application.newsnow.util.OnSectionClickListener;
import com.application.newsnow.viewholder.SectionViewHolder;

import java.util.ArrayList;
import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<SectionViewHolder> {

    private List<Section> sections = new ArrayList<>();
    private OnSectionClickListener listener;

    public SectionAdapter(OnSectionClickListener listener) {
        this.listener = listener;
    }

    public void addSections(List<Section> sectionList) {
        sections.clear();
        sections.addAll(sectionList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_section, parent, false);
        return new SectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, int position) {
        holder.bind(sections.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return sections.size();
    }

}
