package com.example.studentsapp.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentsapp.R
import com.example.studentsapp.databinding.FragmentSearchListBinding
import com.example.studentsapp.view.FeedItemDecorator
import com.example.studentsapp.view.adapters.CategoriesListAdapter
import com.example.studentsapp.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchListFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var fragmentSearchListBinding: FragmentSearchListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentSearchListBinding = FragmentSearchListBinding.inflate(layoutInflater)
        return fragmentSearchListBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel.getCategories()
        lifecycleScope.launch {
            searchViewModel.categoriesFlow.onEach { categoriesDataModel ->
                val categoriesAdapter = CategoriesListAdapter()
                fragmentSearchListBinding.textView.text =
                    categoriesDataModel?.categoriesTopData?.categoriesData?.title
                with(fragmentSearchListBinding.categoriesList) {
                    adapter = categoriesAdapter
                    layoutManager = LinearLayoutManager(activity)
                    addItemDecoration(
                        FeedItemDecorator(
                            horizontalSpace = resources.getDimensionPixelSize(R.dimen.dimen_8),
                            verticalSpace = resources.getDimensionPixelSize(R.dimen.dimen_8)
                        )
                    )
                    categoriesDataModel?.categoriesItem?.let {
                        categoriesAdapter.updateCategoriesList(it)
                    }
                }
            }.catch {
                Log.e("SearchFragment", "Error observing categories list fragment $it")
            }.collect()
        }
    }
}
