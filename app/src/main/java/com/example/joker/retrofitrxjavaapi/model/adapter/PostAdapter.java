package com.example.joker.retrofitrxjavaapi.model.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.joker.retrofitrxjavaapi.R;
import com.example.joker.retrofitrxjavaapi.model.pojo.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private Context context;
    private List<Post> postList;

    public PostAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
         View view = LayoutInflater.from(viewGroup.getContext())
                                            .inflate(R.layout.post_item, viewGroup, false);

        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.txt_author.setText(String.valueOf(postList.get(position).id));
        holder.txt_title.setText(postList.get(position).title);
        holder.txt_content.setText(new StringBuilder(postList.get(position).body).substring(0,20));

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{

        TextView txt_title, txt_content, txt_author;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_title = itemView.findViewById(R.id.txt_title);
            txt_author = itemView.findViewById(R.id.txt_author);
            txt_content = itemView.findViewById(R.id.txt_content);

        }
    }
}
