package com.example.testone.data.mapper

import com.example.testone.data.dto.ListItem
import com.example.testone.domain.model.UserUI

fun ListItem.toUser(): UserUI {
    return UserUI(name = name ?: "")
}
