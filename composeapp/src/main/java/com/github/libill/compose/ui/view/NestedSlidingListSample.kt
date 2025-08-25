package com.github.libill.compose.ui.view

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt

@Composable
fun NestedSlidingListSample() {

    var offsetY by remember { mutableFloatStateOf(0f) }

    val dispatcher = remember { NestedScrollDispatcher() } // 定义子组件分发滑动事件的行为

    // 定义父组件响应并处理滑动事件的行为
    val connection = remember {
        object : NestedScrollConnection {
            // 子组件处理滑动之前，不消费任何位移，也就是子组件优先滚动
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                return super.onPreScroll(available, source)
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource
            ): Offset {
                // 子组件处理滑动之后，消费掉剩余的垂直位移
                offsetY += available.y
                return Offset(x = 0f, available.y) // 返回直接消费的量
            }
        }
    }
    Column(
        Modifier
            .offset { IntOffset(x = 0, y = offsetY.roundToInt()) }
            .draggable(
                state = rememberDraggableState { delta ->
                    // 父组件实际消费的滚动距离
                    val consumed = dispatcher.dispatchPreScroll(
                        available = Offset(x = 0f, y = delta),
                        source = NestedScrollSource.UserInput
                    )

                    // 子组件消费掉当前可用的滚动距离
                    offsetY += (delta - consumed.y)

                    // 通知父组件，子组件实际消费的距离
                    dispatcher.dispatchPostScroll(
                        consumed = Offset(
                            x = 0f,
                            y = (delta - consumed.y)
                        ), available = Offset.Zero, source = NestedScrollSource.UserInput
                    )
                },
                orientation = Orientation.Vertical,
            )
            .nestedScroll(dispatcher = dispatcher, connection = connection) // 自定义嵌套滑动
    ) {
        for (i in 1..10) {
            Text(text = "Text $i")
        }

        // 嵌套滑动列表测试
        LazyColumn(Modifier.fillMaxHeight()) {
            items(count = 35){
                Text("Lazy Text $it")
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun NestedSlidingListSamplePreview() {
    NestedSlidingListSample()
}
