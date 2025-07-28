package com.github.libill.compose.ui.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.libill.compose.R
import com.github.libill.compose.ui.theme.WeComposeTheme


@Composable
fun WeNavigationBar(selected: Int, onSelected: (Int) -> Unit) {
    Row(Modifier.background(MaterialTheme.colorScheme.background)) {
        TabItem(
            if (selected == 0) R.drawable.ic_launcher_background else R.drawable.ic_launcher_foreground,
            "聊天",
            if (selected == 0) MaterialTheme.colorScheme.primary else Color.Gray,
            modifier = Modifier
                .weight(1f)
                .clickable { onSelected.invoke(0) }
        )
        TabItem(
            if (selected == 1) R.drawable.ic_launcher_background else R.drawable.ic_launcher_foreground,
            "通讯录",
            if (selected == 1) MaterialTheme.colorScheme.primary else Color.Gray,
            modifier = Modifier
                .weight(1f)
                .clickable { onSelected.invoke(1) }
        )
        TabItem(
            if (selected == 2) R.drawable.ic_launcher_background else R.drawable.ic_launcher_foreground,
            "发现",
            if (selected == 2) MaterialTheme.colorScheme.primary else Color.Gray,
            modifier = Modifier
                .weight(1f)
                .clickable { onSelected.invoke(2) }
        )
        TabItem(
            if (selected == 3) R.drawable.ic_launcher_background else R.drawable.ic_launcher_foreground,
            "我",
            if (selected == 3) MaterialTheme.colorScheme.primary else Color.Gray,
            modifier = Modifier
                .weight(1f)
                .clickable { onSelected.invoke(3) }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WeNavigationPreview() {
    var selectedTab by remember { mutableIntStateOf(1) }
    WeComposeTheme {
        WeNavigationBar(selectedTab) {
            selectedTab = it
        }
    }
}

@Composable
fun TabItem(@DrawableRes iconId: Int, title: String, tint: Color, modifier: Modifier = Modifier) {
    Column(
        modifier.padding(top = 10.dp, bottom = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(painterResource(iconId), title, Modifier.size(24.dp).unread(true, MaterialTheme.colorScheme.primary), tint = tint)
        Text(text = title, fontSize = 11.sp, color = tint)
    }
}

@Preview(showBackground = true)
@Composable
fun TabItemPreview() {
    TabItem(R.drawable.ic_launcher_background, "聊天", MaterialTheme.colorScheme.primary)
}