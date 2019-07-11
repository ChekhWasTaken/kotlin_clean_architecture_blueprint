package com.example.jsonplaceholderclient.di

import androidx.fragment.app.Fragment
import com.example.jsonplaceholderclient.presentation.post.PostFragment
import com.example.jsonplaceholderclient.presentation.postlist.PostListFragment
import com.example.jsonplaceholderclient.presentation.user.UserFragment
import dagger.Binds
import dagger.Module
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import androidx.fragment.app.FragmentFactory as AndroidFragmentFactory

internal class FragmentFactory @Inject constructor(private val creators: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<Fragment>>) :
    AndroidFragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val fragmentClass = loadFragmentClass(classLoader, className)

        return creators[fragmentClass]?.get() ?: super.instantiate(classLoader, className)
    }

}

@Module
internal interface FragmentModule {
    @Binds
    @Singleton
    fun fragmentFactory(fragmentFactory: FragmentFactory): AndroidFragmentFactory

    @Binds
    @IntoMap
    @FragmentKey(PostFragment::class)
    fun postFragment(postFragment: PostFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(PostListFragment::class)
    fun postListFragment(postListFragment: PostListFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(UserFragment::class)
    fun userFragment(userFragment: UserFragment): Fragment
}