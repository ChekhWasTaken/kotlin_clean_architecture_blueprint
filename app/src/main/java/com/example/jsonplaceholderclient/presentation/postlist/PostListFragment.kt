package com.example.jsonplaceholderclient.presentation.postlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.data.entity.Post
import com.example.framework.IOViewModel
import com.example.framework.ResponseIoFragment
import com.example.framework.toast
import com.example.jsonplaceholderclient.R
import com.example.jsonplaceholderclient.presentation.common.PostListAdapter
import kotlinx.android.synthetic.main.fragment_post_list.*
import javax.inject.Inject
import kotlin.reflect.KClass

internal class PostListFragment @Inject constructor(viewModelFactory: ViewModelProvider.Factory) :
    ResponseIoFragment<List<Post>>(viewModelFactory) {

    override val viewModelClass: KClass<out IOViewModel<Unit, List<Post>>>
        get() = PostListViewModel::class

    override fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_post_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postListAdapter = PostListAdapter {
            findNavController().navigate(
                PostListFragmentDirections.actionPostListFragmentToPostFragment(it.id)
            )
        }

        list_post.adapter = postListAdapter
    }

    override fun onError(throwable: Throwable) {
        throwable.message?.let { message -> toast(message) }
    }

    override fun onSuccess(data: List<Post>) = (list_post.adapter as PostListAdapter).submit(data)
}