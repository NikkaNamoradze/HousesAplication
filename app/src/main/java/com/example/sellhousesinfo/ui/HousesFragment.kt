package com.example.sellhousesinfo.ui


import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.sellhousesinfo.adapter.HousesRecyclerAdapter

import com.example.sellhousesinfo.databinding.HousesFragmentBinding
import com.example.sellhousesinfo.model.Content
import com.example.sellhousesinfo.responsehandling.ApiResponseHandler

import kotlinx.coroutines.launch


class HousesFragment : Fragment() {

    private var _binding: HousesFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HousesViewModel by viewModels()
    private val housesAdapter by lazy { HousesRecyclerAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HousesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        getDataFromApi()
        refresh()
    }

    private fun refresh(){
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getHousesData()
            binding.swipeRefresh.isRefreshing = false
        }
    }


    private fun getDataFromApi() {
        viewModel.getHousesData()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newsState.collect {
                    when(it){
                        is ApiResponseHandler.Success -> setUpRecycler(it.data)
                        is ApiResponseHandler.Failure -> errorToastMaker()
                        else -> {}
                    }
                }
            }
        }
    }

    private fun setUpRecycler(housesData: List<Content>?) {
        binding.housesRecycler.adapter = housesAdapter
        housesData?.let { housesAdapter.setData(it) }
    }

    private fun errorToastMaker(){
        Toast.makeText(context, "error occurred", Toast.LENGTH_SHORT).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}