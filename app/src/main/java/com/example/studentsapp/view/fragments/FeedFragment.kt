package com.example.studentsapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentsapp.R
import com.example.studentsapp.view.adapters.FeedPagingAdapter
import com.example.studentsapp.databinding.HomeFragmentBinding
import com.example.studentsapp.view.FeedItemDecorator
import com.example.studentsapp.view.adapters.PagingLoaderAdapter
import com.example.studentsapp.viewmodel.FeedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

@AndroidEntryPoint
class FeedFragment : Fragment() {

    private val viewModel: FeedViewModel by viewModels()
    private val scope = MainScope() + CoroutineName("Home Fragment")

    private lateinit var homeFragmentBinding: HomeFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeFragmentBinding = HomeFragmentBinding.inflate(layoutInflater)
        return homeFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val feedPagingAdapter = FeedPagingAdapter()
        with(homeFragmentBinding.recyclerView) {
            adapter = feedPagingAdapter.withLoadStateHeaderAndFooter(
                header = PagingLoaderAdapter(),
                footer = PagingLoaderAdapter()
            )
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(
                FeedItemDecorator(
                    horizontalSpace = resources.getDimensionPixelSize(R.dimen.dimen_8),
                    verticalSpace = resources.getDimensionPixelSize(R.dimen.dimen_8)
                )
            )
        }
        lifecycleScope.launch {
            viewModel.feedDataFlow.collectLatest {
                feedPagingAdapter.submitData(it)
            }
        }
    }
}
