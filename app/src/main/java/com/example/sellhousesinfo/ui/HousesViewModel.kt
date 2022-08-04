package com.example.sellhousesinfo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sellhousesinfo.apidata.RetrofitInstance
import com.example.sellhousesinfo.model.Content
import com.example.sellhousesinfo.model.HousesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HousesViewModel : ViewModel() {

    private var _housesState = MutableStateFlow<List<Content>>(emptyList())
    var newsState: StateFlow<List<Content>> = _housesState

    fun getHousesData() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitInstance.retrofitBuilder.getData()

            if (response.isSuccessful && response.body()!= null) {
                val responseBody: HousesModel? = response.body()
                _housesState.value = responseBody?.content ?: emptyList()
            }
        }
    }
}