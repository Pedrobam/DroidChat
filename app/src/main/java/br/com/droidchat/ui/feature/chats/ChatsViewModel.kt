package br.com.droidchat.ui.feature.chats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.droidchat.data.repository.ChatRepository
import br.com.droidchat.model.Chat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    private val repository: ChatRepository
) : ViewModel() {

    private val _chatsListUiState = MutableStateFlow<ChatsListUiState>(ChatsListUiState.Loading)
    val chatsListUiState = _chatsListUiState.asStateFlow()

    init {
        getChats()
    }

    private fun getChats() {
        viewModelScope.launch {
            repository.getChats(
                offset = 0,
                limit = 10
            ).fold(
                onSuccess = { chats ->
                    _chatsListUiState.emit(ChatsListUiState.Success(chats))
                },
                onFailure = {
                    _chatsListUiState.update {
                        ChatsListUiState.Error
                    }
                }
            )
        }
    }

    sealed interface ChatsListUiState {
        object Loading : ChatsListUiState
        data class Success(val chats: List<Chat>) : ChatsListUiState
        object Error : ChatsListUiState
    }
}