package com.github.libill.compose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class WeComposeViewModel: ViewModel() {
    var selectedTab by mutableIntStateOf(0)
}