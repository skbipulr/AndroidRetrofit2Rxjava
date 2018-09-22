package com.example.bipul.androidretrofit2rxjava.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.bipul.androidretrofit2rxjava.R;

public class PostViewHolder extends RecyclerView.ViewHolder {

    TextView txt_title,txt_content,txt_author;
    public PostViewHolder(View itemView) {
        super(itemView);

        txt_title=itemView.findViewById(R.id.txt_title);
        txt_content=itemView.findViewById(R.id.txt_content);
        txt_author=itemView.findViewById(R.id.txt_author);
    }
}
