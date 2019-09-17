package com.dd.githubapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dd.githubapp.R;
import com.dd.githubapp.entity.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * GithubApp
 *
 * @author daidong
 */
public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {

    private List<Repository> repositories;

    public RepoAdapter() {
        repositories = new ArrayList<>();
    }

    public void addRepositories(List<Repository> repositories) {
        if (repositories != null && repositories.size() > 0) {
            this.repositories.clear();
            this.repositories.addAll(repositories);
            notifyDataSetChanged();
        }
    }

    public void appendRespositories(List<Repository> repositories) {
        if (repositories != null && repositories.size() > 0) {
            this.repositories.addAll(this.repositories.size(), repositories);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
