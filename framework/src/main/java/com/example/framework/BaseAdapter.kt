package com.example.framework

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T> protected constructor(private val vhFactory: (parent: ViewGroup, viewType: Int) -> BaseViewHolder<T>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        vhFactory(parent, viewType) as RecyclerView.ViewHolder

    override fun getItemCount() = items.size

    @Suppress("UNCHECKED_CAST") // creating BaseViewHolder<T> in onCreateViewHolder, so cast is safe
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as BaseViewHolder<T>).bind(items[position])

    fun set(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)

        notifyDataSetChanged()
    }

    fun submit(posts: List<T>) {
        val insertPosition = this.items.size
        this.items.addAll(posts)

        notifyItemRangeInserted(insertPosition, posts.size)
    }

    protected abstract class BaseViewHolder<T>(itemView: View, private val itemClickListener: ((T) -> Unit)? = null) :
        RecyclerView.ViewHolder(itemView) {
        init {
            if (itemClickListener != null) {
                itemView.setOnClickListener {
                    @Suppress("UNCHECKED_CAST") // setting item in bind method, so we are sure, that tag will be of type T
                    itemClickListener.invoke(itemView.tag as T)
                }
            }
        }

        fun bind(item: T) {
            if (itemClickListener != null) itemView.tag = item

            onBind(item)
        }

        abstract fun onBind(item: T)
    }
}