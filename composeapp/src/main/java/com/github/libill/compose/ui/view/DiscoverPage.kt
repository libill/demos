package com.github.libill.compose.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class DiscoverItem(
    val title: String,
    val icon: ImageVector,
    val hasRedDot: Boolean = false
)

@Composable
fun DiscoverPage() {
    val discoverItems = listOf(
        DiscoverItem("朋友圈", Icons.Default.Search, hasRedDot = true),
        DiscoverItem("视频号", Icons.Default.PlayArrow),
        DiscoverItem("直播", Icons.Default.PlayArrow),
        DiscoverItem("扫一扫", Icons.Default.Search),
        DiscoverItem("摇一摇", Icons.Default.Phone),
        DiscoverItem("看一看", Icons.Default.Search),
        DiscoverItem("搜一搜", Icons.Default.Search),
        DiscoverItem("附近的人", Icons.Default.LocationOn),
        DiscoverItem("购物", Icons.Default.ShoppingCart),
        DiscoverItem("游戏", Icons.Default.Search),
        DiscoverItem("小程序", Icons.Default.Search)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {
        items(discoverItems.chunked(4)) { itemGroup ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
            ) {
                itemGroup.forEach { item ->
                    DiscoverItemRow(item = item)
                    if (item != itemGroup.last()) {
                        Divider(
                            color = Color(0xFFE5E5E5),
                            thickness = 0.5.dp,
                            modifier = Modifier.padding(start = 60.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DiscoverItemRow(item: DiscoverItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color(0xFF07C160)
            )
            if (item.hasRedDot) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Color.Red, CircleShape)
                        .align(Alignment.TopEnd)
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = item.title,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = Color.Gray
        )
    }
}