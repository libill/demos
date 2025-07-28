package com.github.libill.compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.libill.compose.data.getChatList

class WeComposeViewModel: ViewModel() {
    var selectedTab by mutableIntStateOf(0)

    var chats by mutableStateOf(
        getChatList()
    )
}