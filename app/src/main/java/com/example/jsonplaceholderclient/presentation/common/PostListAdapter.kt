package com.example.jsonplaceholderclient.presentation.common

import android.view.LayoutInflater
import com.example.data.entity.Post
import com.example.framework.BaseAdapter
import com.example.jsonplaceholderclient.databinding.ListItemPostBinding

internal class PostListAdapter(postClickListener: (Post) -> Unit) : BaseAdapter<Post>({ parent, _ ->
    PostViewHolder(
        ListItemPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ), postClickListener
    )
}) {

    private class PostViewHolder(
        private val binding: ListItemPostBinding,
        postClickListener: (Post) -> Unit
    ) :
        BaseViewHolder<Post>(binding.root, postClickListener) {
        override fun onBind(item: Post) {
            binding.post = item
        }
    }
}

