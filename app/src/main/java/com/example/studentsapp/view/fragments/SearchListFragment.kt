package com.example.studentsapp.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentsapp.R
import com.example.studentsapp.databinding.FragmentSearchListBinding
import com.example.studentsapp.view.FeedItemDecorator
import com.example.studentsapp.view.adapters.CategoriesListAdapter
import com.example.studentsapp.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchListFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var fragmentSearchListBinding: FragmentSearchListBinding
    private var searchText: String? = null
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
                    handleSearchView()
                }

            }.catch {
                Log.e("SearchFragment", "Error observing categories list fragment $it")
            }.collect()
        }
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    fun handleSearchView() {
        lifecycleScope.launch {
            fragmentSearchListBinding.searchBar.getSearchFlow()
                .debounce(200)
                .filter {
                    if (it == "") {
                        searchText = ""
                        return@filter false
                    } else {
                        return@filter true
                    }
                }
                .distinctUntilChanged()
                .flatMapLatest { searchQuery ->
                    fetchDataFromNetwork(searchQuery).catch {
                        emitAll(flowOf("error"))
                    }
                }
                .flowOn(Dispatchers.Default)
                .collect { searchQuery ->
                    searchText = searchQuery
                }
        }
    }

    fun fetchDataFromNetwork(searchQuery: String?): Flow<String> {
        return flow<String> {
            delay(3000)
            searchQuery?.let { emit(it) }
        }
    }

    fun SearchView.getSearchFlow(): MutableStateFlow<String?> {
        val searchQuery = MutableStateFlow<String?>("")
        setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchQuery.value = newText
                return true
            }
        })
        return searchQuery
    }
}
