package com.srikanth.amphibians.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.srikanth.amphibians.Model.Amphibians
import com.srikanth.amphibians.Repo.AmphibiansApi
import com.srikanth.amphibians.Repo.retrofit
import kotlinx.coroutines.launch


var amphibiansApi= retrofit.create(AmphibiansApi::class.java)
class AmphibiansViewModel: ViewModel() {

    var _amphibiansUiState = mutableStateOf(AmphibiansUiState())

    var amphibiansUiState :State<AmphibiansUiState> = _amphibiansUiState


    init {
        getAmphibians()
    }


    fun getAmphibians(){
        viewModelScope.launch {
            try{
                var data=amphibiansApi.getAmphibians()
                _amphibiansUiState.value=_amphibiansUiState.value.copy(
                    loading = false,
                    amphinbiansList = data
                )
            }
            catch (e:Exception){
                _amphibiansUiState.value=
                _amphibiansUiState.value.copy(
                    loading = false,
                    error = true
                )
            }
        }
    }

}

data class AmphibiansUiState(
    var loading:Boolean=true,
    var amphinbiansList:List<Amphibians> = emptyList(),
    var error:Boolean=false
)