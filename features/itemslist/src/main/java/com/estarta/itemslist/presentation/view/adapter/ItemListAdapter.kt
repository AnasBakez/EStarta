package com.estarta.itemslist.presentation.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.estarta.itemslist.domain.model.ItemModel
import com.estarta.itemslist.presentation.view.ItemsListActionInterface

internal class ItemListAdapter constructor(
    private val actionInterface: ItemsListActionInterface,
) : ListAdapter<ItemModel, ItemViewHolder>(diffCallback) {

    companion object {

        /**
         * This diff callback informs the PagedListAdapter how to compute list differences when new
         * PagedLists arrive.
         * <p>
         * When you add a Cheese with the 'Add' button, the PagedListAdapter uses diffCallback to
         * detect there's only a single item difference from before, so it only needs to animate and
         * rebind a single view.
         *
         * @see DiffUtil
         */
        private val diffCallback = object : DiffUtil.ItemCallback<ItemModel>() {
            override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
                return oldItem.uid == newItem.uid
            }

            /**
             * Note that in kotlin, == checking on data classes compares all contents, but in Java,
             * typically you'll implement Object#equals, and use it to compare object contents.
             */
            override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent, actionInterface)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindData((getItem(position)))
    }
}