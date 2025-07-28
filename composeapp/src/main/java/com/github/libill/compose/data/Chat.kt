package com.github.libill.compose.data

import com.github.libill.compose.R

data class Chat(
    val name: String,
    val friend: Friend,
    val msgList: List<ChatMessage>,
) {

}

fun getChatList(): List<Chat> {
    return List(20) { index ->
        Chat(
            name = "Chat $index",
            friend = Friend(
                name = "Friend $index",
                avatar = R.drawable.ic_launcher_background,
                lastMessage = "Last message from Friend $index",
                lastMessageTime = "10:${index % 60} AM",
                unreadCount = index % 5
            ),
            msgList = listOf(ChatMessage("今天去哪里玩了？ $index", true))
        )
    }
}

data class Friend(
    val name: String,
    val avatar: Int,
    val lastMessage: String,
    val lastMessageTime: String,
    val unreadCount: Int,
) {
    companion object {
        val Default = Friend(
            name = "Default",
            avatar = R.drawable.ic_launcher_background,
            lastMessage = "Hello!",
            lastMessageTime = "10:00 AM",
            unreadCount = 0
        )
    }
}

data class ChatMessage(
    val text: String,
    val unread: Boolean,
)
