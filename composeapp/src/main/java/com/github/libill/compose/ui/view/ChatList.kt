package com.github.libill.compose.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.libill.compose.data.Chat
import com.github.libill.compose.data.getChatList

@Composable
fun ChatList(chats: List<Chat>) {
    Box(
        Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    )
    LazyColumn(Modifier.fillMaxSize()) {
        itemsIndexed(chats) { index, chat ->
            if (index > 0) {
                HorizontalDivider(
                    Modifier.padding(start = 68.dp),
                    color = MaterialTheme.colorScheme.tertiary,
                    thickness = 0.8.dp
                )
            }
            Row {
                Image(
                    painterResource(chat.friend.avatar), chat.friend.name,
                    Modifier
                        .padding(4.dp)
                        .size(48.dp)
                        .unread(chat.msgList.lastOrNull()?.unread == true, MaterialTheme.colorScheme.error)
                        .clip(RoundedCornerShape(4.dp))
                )
                Column(Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)) {
                    Text(chat.friend.name, fontSize = 17.sp, style = MaterialTheme.typography.bodyLarge)
                    Text("${chat.msgList.lastOrNull()?.text}", style = MaterialTheme.typography.bodyLarge)
                }
                Text(
                    chat.friend.lastMessageTime,
                    Modifier.padding(8.dp, 3.dp, 12.dp, 8.dp),
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun Modifier.unread(show: Boolean, color: Color): Modifier = drawWithContent {
    drawContent()
    if (show) {
        drawCircle(color, 5.dp.toPx(), Offset(size.width - 1.dp.toPx(), 1.dp.toPx()))
    }
}

@Preview(showBackground = true)
@Composable
fun ChatListPreview() {
    val list = getChatList()
    ChatList(list)
}