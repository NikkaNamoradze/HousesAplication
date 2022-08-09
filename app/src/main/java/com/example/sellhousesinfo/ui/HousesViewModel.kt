package com.example.sellhousesinfo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sellhousesinfo.apidata.RetrofitInstance
import com.example.sellhousesinfo.model.Content
import com.example.sellhousesinfo.responsehandling.ApiResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HousesViewModel : ViewModel() {

    private var _responseState = MutableStateFlow<ApiResponseHandler<List<Content>>>(ApiResponseHandler.Loading())
    var newsState  = _responseState.asStateFlow()

    fun getHousesData() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitInstance.retrofitBuilder.getData()
            if (response.isSuccessful && response.body()!= null) {
                _responseState.emit(ApiResponseHandler.Success(response.body()?.content))
            }else{
                _responseState.emit(ApiResponseHandler.Failure(response.errorBody().toString()))
            }
        }
    }
}