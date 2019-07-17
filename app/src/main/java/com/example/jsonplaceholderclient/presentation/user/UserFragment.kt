package com.example.jsonplaceholderclient.presentation.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.data.entity.Post
import com.example.framework.IOViewModel
import com.example.framework.IoFragment
import com.example.framework.toast
import com.example.jsonplaceholderclient.R
import com.example.jsonplaceholderclient.presentation.common.PostListAdapter
import kotlinx.android.synthetic.main.fragment_post_list.*
import javax.inject.Inject
import kotlin.reflect.KClass

internal class UserFragment @Inject constructor(viewModelFactory: ViewModelProvider.Factory) :
    IoFragment<Int, List<Post>>(viewModelFactory) {

    override val viewModelClass: KClass<out IOViewModel<Int, List<Post>>>
        get() = UserViewModel::class

    override val request: Int
        get() = args.userId

    private val args by navArgs<UserFragmentArgs>()

    override fun onCreateContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_user, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postListAdapter = PostListAdapter {}

        list_post.adapter = postListAdapter
    }

    override fun onError(throwable: Throwable) {
        throwable.message?.let { message -> toast(message) }
    }

    override fun onSuccess(data: List<Post>) {
        (list_post.adapter as PostListAdapter).submit(data)
    }
}