package com.example.imgpurr.screen.imgur

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imgpurr.R
import com.example.imgpurr.util.EndlessRecyclerOnScrollListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private lateinit var onScrollListener: EndlessRecyclerOnScrollListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prepareObservables()
        prepareComponents()

        viewModel.load(fromBeginning = true)
    }

    private fun prepareObservables() {
        viewModel.model.stateOb.observe(this, Observer(::handleState))
    }

    private fun prepareComponents() {
        onScrollListener =
            object : EndlessRecyclerOnScrollListener(SPAN_COUNT * IMAGES_THRESHOLD_COUNT) {
                override fun onLoadMore(currentPage: Int) {
                    viewModel.load()
                }
            }
        recyclerViewImages.apply {
            layoutManager = GridLayoutManager(context, SPAN_COUNT)

            adapter = MainAdapter()

            addOnScrollListener(onScrollListener)
        }

        materialButtonRetry.setOnClickListener {
            viewModel.load(fromBeginning = true)
        }
    }

    private fun handleState(state: MainModel.ImgurState) {
        when (state) {
            is MainModel.ImgurState.Loading -> {
                groupError.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }
            is MainModel.ImgurState.Loaded -> {
                progressBar.visibility = View.GONE
                groupError.visibility = View.GONE
                recyclerViewImages.visibility = View.VISIBLE

                (recyclerViewImages.adapter as MainAdapter)
                    .addItems(state.searchResult ?: listOf())
            }
            is MainModel.ImgurState.Error -> {
                progressBar.visibility = View.GONE
                val adapter = recyclerViewImages.adapter as MainAdapter
                if (adapter.items.isEmpty()) {
                    recyclerViewImages.visibility = View.GONE
                    groupError.visibility = View.VISIBLE

                    textViewErrorMessage.text = state.message
                    Log.d("rafadebug", state.message)
                    onScrollListener.reset()
                } else {
                    Snackbar.make(constraintLayoutParent, state.message, Snackbar.LENGTH_LONG)
                        .show()
                    onScrollListener.reset()
                }

            }
        }
    }

    companion object {
        private const val IMAGES_THRESHOLD_COUNT = 5
        private const val SPAN_COUNT = 4
    }
}
