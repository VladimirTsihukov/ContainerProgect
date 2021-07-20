package com.androidapp.containerprogect

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidapp.containerprogect.adapter.FingerprintAdapter
import com.androidapp.containerprogect.adapter.Item
import com.androidapp.containerprogect.adapter.animators.AddableItemAnimator
import com.androidapp.containerprogect.adapter.animators.custom.SimpleCommonAnimator
import com.androidapp.containerprogect.adapter.animators.custom.SlideInLeftCommonAnimator
import com.androidapp.containerprogect.adapter.animators.custom.SlideInTopCommonAnimator
import com.androidapp.containerprogect.adapter.decorations.FeedHorizontalDividerItemDecoration
import com.androidapp.containerprogect.adapter.decorations.GroupVerticalItemDecoration
import com.androidapp.containerprogect.adapter.fingerprint.PostFingerprint
import com.androidapp.containerprogect.adapter.fingerprint.TitleFingerprint
import com.androidapp.containerprogect.databinding.ActivityMainBinding
import com.androidapp.containerprogect.model.UserPost
import com.androidapp.containerprogect.utils.SwipeToDelete
import com.androidapp.containerprogect.utils.getRandomFeed
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: FingerprintAdapter
    private val feed: MutableList<Item> by lazy(LazyThreadSafetyMode.NONE) { getRandomFeed(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        adapter = FingerprintAdapter(getFingerprints())

        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter

            addItemDecoration(FeedHorizontalDividerItemDecoration(40))
            addItemDecoration(GroupVerticalItemDecoration(R.layout.item_post, 100, 0))
            addItemDecoration(GroupVerticalItemDecoration(R.layout.item_title, 0, 100))

            itemAnimator = AddableItemAnimator(SimpleCommonAnimator()).also { animator ->
                animator.addViewTypeAnimation(R.layout.item_post, SlideInLeftCommonAnimator())
                animator.addViewTypeAnimation(R.layout.item_title, SlideInTopCommonAnimator())
                animator.addDuration = 500L
                animator.removeDuration = 500L
            }
        }

        initSwipeToDelete()
        submitInitialListWithDelayForAnimation()
    }

    private fun getFingerprints() = listOf(
        TitleFingerprint(),
        PostFingerprint(::onSavePost)
    )

    private fun onSavePost(post: UserPost) {
        val postIndex = feed.indexOf(post)
        val newItem = post.copy(isSaved = post.isSaved.not())

        feed.removeAt(postIndex)
        feed.add(postIndex, newItem)
        adapter.submitList(feed.toList())
    }

    private fun submitInitialListWithDelayForAnimation() {
        binding.recyclerView.postDelayed({
            adapter.submitList(feed.toList())
        }, 300L)
    }

    private fun initSwipeToDelete() {
        val onItemSwipeToDelete = { positionForRemote: Int ->
            val removedItem = feed[positionForRemote]
            feed.removeAt(positionForRemote)
            adapter.submitList(feed.toList())

            showRestoreItemSnackbar(positionForRemote, removedItem)
        }

        val swipeToDeleteCallback = SwipeToDelete(onItemSwipeToDelete)
        ItemTouchHelper(swipeToDeleteCallback).attachToRecyclerView(binding.recyclerView)
    }

    private fun showRestoreItemSnackbar(position: Int, item: Item) {
        Snackbar.make(binding.recyclerView, "Item was delete", Snackbar.LENGTH_LONG)
            .setAction("Undo") {
                feed.add(position, item)
                adapter.submitList(feed.toList())
            }.show()
    }
}