package com.example.imgpurr.util

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessRecyclerOnScrollListener(
    private val visibleThreshold: Int = 5
) : RecyclerView.OnScrollListener() {

    abstract fun onLoadMore(currentPage: Int)

    private var mPreviousTotal = 0
    private var mLoading = true
    private var mCurrentPage = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = recyclerView.childCount
        val totalItemCount = recyclerView.layoutManager?.itemCount ?: 0
        val firstVisibleItem =
            (recyclerView.layoutManager as GridLayoutManager).findFirstVisibleItemPosition()

        if (mLoading) {
            if (totalItemCount > mPreviousTotal) {
                mLoading = false
                mPreviousTotal = totalItemCount
            }
        }
        if (!mLoading && totalItemCount - firstVisibleItem - visibleItemCount <= visibleThreshold) {

            onLoadMore(++mCurrentPage)

            mLoading = true
        }
    }

    fun reset() {
        mPreviousTotal = 0
        mLoading = true
        mCurrentPage = 1
    }

}