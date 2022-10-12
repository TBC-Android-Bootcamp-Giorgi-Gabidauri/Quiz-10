package com.gabo.quiz10.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabo.quiz10.data.handleResponse.Resource
import com.gabo.quiz10.domain.useCases.GetChatInfoUseCase
import com.gabo.quiz10.ui.model.ChatModelUi
import com.gabo.quiz10.ui.transformers.toModelUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getChatInfoUseCase: GetChatInfoUseCase) :
    ViewModel() {
    private val _state = MutableStateFlow(ViewState())
    val state = _state.asStateFlow()

    init {
        getChatInfo()
    }

    fun getChatInfo() {
        viewModelScope.launch {
            resetViewState()
            _state.value = _state.value.copy(loading = true)
            getChatInfoUseCase(Unit).collect {
                when (it) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            loading = false,
                            data = it.data!!.map { modelDomain -> modelDomain.toModelUi() })
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(loading = false, errorMsg = it.errorMsg!!)
                    }
                }
            }
        }
    }

    private fun resetViewState() {
        _state.value = _state.value.copy(loading = false, data = emptyList(), errorMsg = "")
    }

    data class ViewState(
        val loading: Boolean = false,
        val data: List<ChatModelUi> = emptyList(),
        val errorMsg: String = ""
    )
}