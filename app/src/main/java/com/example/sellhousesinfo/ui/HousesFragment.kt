package com.example.sellhousesinfo.ui


import android.os.Bundle
import android.util.Log.d

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.sellhousesinfo.adapter.HousesRecyclerAdapter

import com.example.sellhousesinfo.databinding.HousesFragmentBinding
import com.example.sellhousesinfo.model.Content
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


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
        setUpRecycler()
    }

    private fun getDataFromApi() {
        viewModel.getHousesData()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newsState.collect {
                    housesAdapter.setData(it)
//                  d("list", "$it")
                }
            }
        }
    }

    private fun setUpRecycler() {
        binding.housesRecycler.adapter = housesAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}