package com.example.jsonplaceholderclient.presentation.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.data.entity.Post
import com.example.jsonplaceholderclient.databinding.FragmentPostBinding
import com.example.jsonplaceholderclient.presentation.common.PostActionHandler
import com.example.jsonplaceholderclient.presentation.toast
import javax.inject.Inject

internal class PostFragment @Inject constructor(viewModelFactory: ViewModelProvider.Factory) : Fragment() {
    private val viewModel by viewModels<PostViewModel> { viewModelFactory }

    private val args by navArgs<PostFragmentArgs>()

    private lateinit var binding: FragmentPostBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPostBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.handler = object : PostActionHandler {
            override fun onPostAuthorClick(post: Post) {
                findNavController().navigate(PostFragmentDirections.actionPostFragmentToUserFragment(post.user.id))
            }

        }

        viewModel.postLiveData.observe(this, Observer {
            when (it) {
                is UIState.Loading -> toast("Loading data")
                is UIState.Error -> it.exception.message?.let { message -> toast(message) }
                is UIState.Success -> binding.post = it.data
            }
        })

        viewModel.getPost(args.postId)
    }
}