package com.github.libill.compose.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Contact(
    val name: String,
    val avatar: Int = android.R.drawable.ic_menu_gallery,
    val status: String = ""
)

@Composable
fun ContactsPage() {
    val contacts = listOf(
        Contact("新的朋友", status = ""),
        Contact("群聊", status = ""),
        Contact("标签", status = ""),
        Contact("公众号", status = ""),
        Contact("张三", status = "在线"),
        Contact("李四", status = "1小时前"),
        Contact("王五", status = "昨天"),
        Contact("赵六", status = "3天前"),
        Contact("钱七", status = "一周前"),
        Contact("孙八", status = "在线"),
        Contact("周九", status = "2小时前"),
        Contact("吴十", status = "昨天")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {
        items(contacts) { contact ->
            ContactItem(contact = contact)
        }
    }
}

@Composable
fun ContactItem(contact: Contact) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .clickable { }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = contact.avatar),
            contentDescription = null,
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = contact.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            if (contact.status.isNotEmpty()) {
                Text(
                    text = contact.status,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }

    HorizontalDivider(
        color = Color(0xFFE5E5E5),
        thickness = 0.5.dp,
        modifier = Modifier.padding(start = 76.dp)
    )
}