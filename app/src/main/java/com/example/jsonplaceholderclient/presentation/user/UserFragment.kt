package com.example.jsonplaceholderclient.presentation.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.framework.BaseFragment
import com.example.framework.UIState
import com.example.framework.toast
import com.example.jsonplaceholderclient.R
import com.example.jsonplaceholderclient.presentation.common.PostListAdapter
import com.example.jsonplaceholderclient.presentation.postlist.PostListFragmentDirections
import kotlinx.android.synthetic.main.fragment_post_list.*
import javax.inject.Inject

internal class UserFragment @Inject constructor(viewModelFactory: ViewModelProvider.Factory) : BaseFragment() {
    private val viewModel by viewModels<UserViewModel> { viewModelFactory }
    private val args by navArgs<UserFragmentArgs>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postListAdapter = PostListAdapter {
            findNavController().navigate(
                PostListFragmentDirections.actionPostListFragmentToPostFragment(it.id)
            )
        }

        list_post.adapter = postListAdapter

        viewModel.userLiveData.observe(this, Observer {
            when (it) {
                is UIState.Loading -> toast("Loading data")
                is UIState.Error -> it.ex.message?.let { message -> toast(message) }
                is UIState.Success -> postListAdapter.submit(it.data)
            }
        })

        viewModel.getPostsForUser(args.userId)
    }
}