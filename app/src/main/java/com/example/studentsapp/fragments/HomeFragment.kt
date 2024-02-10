package com.example.studentsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.studentsapp.databinding.HomeFragmentBinding
import com.example.studentsapp.viewmodel.FeedViewModel
import com.example.studentsapp.viewmodel.UpdatesViewModel
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.plus

class HomeFragment : Fragment() {

    private val viewModel: FeedViewModel by viewModels()
    private val scope = MainScope() + CoroutineName("Home Fragment")

    lateinit var homeFragmentBinding: HomeFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeFragmentBinding = HomeFragmentBinding.inflate(layoutInflater)
        return homeFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.flow.onEach {

        }.catch {

        }.launchIn(scope)
    }
}
