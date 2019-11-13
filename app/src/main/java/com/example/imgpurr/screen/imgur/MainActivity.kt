package com.example.imgpurr.screen.imgur

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imgpurr.R
import com.example.imgpurr.util.EndlessRecyclerOnScrollListener
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
    }

    private fun prepareObservables() {

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

    companion object {
        private const val IMAGES_THRESHOLD_COUNT = 5
        private const val SPAN_COUNT = 4
    }
}
