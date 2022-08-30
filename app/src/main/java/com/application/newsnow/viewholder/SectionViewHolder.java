package com.application.newsnow.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.newsnow.R;
import com.application.newsnow.domain.model.Section;
import com.application.newsnow.util.OnSectionClickListener;
import com.squareup.picasso.Picasso;

public class SectionViewHolder extends RecyclerView.ViewHolder {

    private ImageView image;
    private TextView title;

    private Section section;
    private OnSectionClickListener listener;

    public SectionViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.topic_image);
        title = itemView.findViewById(R.id.topic_title);
        itemView.setOnClickListener(view -> listener.onSectionListener(section));
    }

    public void bind(Section section, OnSectionClickListener listener) {
        this.section = section;
        this.listener = listener;
        title.setText(section.getSection());
        Picasso.get().load(section.getImage()).into(image);
    }
}
