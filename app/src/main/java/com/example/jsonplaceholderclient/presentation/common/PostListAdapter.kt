package com.example.jsonplaceholderclient.presentation.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.entity.Post
import com.example.jsonplaceholderclient.databinding.ListItemPostBinding

internal class PostListAdapter(private val l: OnPostClickListener) :
    RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {
    private val posts = mutableListOf<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PostViewHolder(
            ListItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), l
        )

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) = holder.bind(posts[position])

    fun set(posts: List<Post>) {
        this.posts.clear()
        this.posts.addAll(posts)

        notifyDataSetChanged()
    }

    fun submit(posts: List<Post>) {
        val insertPosition = this.posts.size
        this.posts.addAll(posts)

        notifyItemRangeInserted(insertPosition, posts.size)
    }

    internal class PostViewHolder(private val binding: ListItemPostBinding, private val l: OnPostClickListener) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.post = post
            binding.postClickListener = l
        }
    }

    internal interface OnPostClickListener {
        fun onPostClick(post: Post)
    }
}