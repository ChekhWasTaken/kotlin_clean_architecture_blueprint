package com.example.jsonplaceholderclient.presentation.postlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.data.entity.Post
import com.example.jsonplaceholderclient.R
import com.example.jsonplaceholderclient.presentation.common.PostListAdapter
import com.example.jsonplaceholderclient.presentation.toast
import kotlinx.android.synthetic.main.fragment_post_list.*
import javax.inject.Inject

internal class PostListFragment @Inject constructor(viewModelFactory: ViewModelProvider.Factory) : Fragment() {
    private val viewModel by viewModels<PostListViewModel> { viewModelFactory }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postListAdapter = PostListAdapter(object :
            PostListAdapter.OnPostClickListener {
            override fun onPostClick(post: Post) {
                findNavController().navigate(PostListFragmentDirections.actionPostListFragmentToPostFragment(post.id))
            }
        })

        list_post.adapter = postListAdapter

        viewModel.postsLiveData.observe(this, Observer {
            when (it) {
                is UIState.Loading -> toast("Loading data")
                is UIState.Error -> it.exception.message?.let { message -> toast(message) }
                is UIState.Success -> postListAdapter.submit(it.data)
            }
        })

        viewModel.getPosts()
    }

}