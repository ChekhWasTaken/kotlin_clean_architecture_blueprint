package com.example.jsonplaceholderclient.presentation.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.data.entity.Post
import com.example.framework.IOViewModel
import com.example.framework.IoFragment
import com.example.framework.toast
import com.example.jsonplaceholderclient.databinding.FragmentPostBinding
import com.example.jsonplaceholderclient.presentation.common.PostActionHandler
import javax.inject.Inject
import kotlin.reflect.KClass

internal class PostFragment @Inject constructor(viewModelFactory: ViewModelProvider.Factory) :
    IoFragment<Int, Post>(viewModelFactory) {

    override val viewModelClass: KClass<out IOViewModel<Int, Post>>
        get() = PostViewModel::class

    override val request: Int
        get() = args.postId

    private val args by navArgs<PostFragmentArgs>()
    private lateinit var binding: FragmentPostBinding

    override fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
    }

    override fun onError(throwable: Throwable) {
        throwable.message?.let { message -> toast(message) }
    }


    override fun onSuccess(data: Post) {
        binding.post = data
    }
}