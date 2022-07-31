package com.example.whatsapp.ui.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.whatsapp.data.FakeCallData
import com.example.whatsapp.data.FakeChatData
import com.example.whatsapp.ui.theme.LightGreen
import com.example.whatsapp.ui.theme.TealDarkGreen
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import com.example.whatsapp.R


@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalPagerApi
@Composable
fun TabsLayout() {

    val pagerState = rememberPagerState(pageCount = 4)

    Column(
        modifier = Modifier.background(Color.White)
    ) {
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    val list = listOf(
        painterResource(id = R.drawable.baseline_photo_camera_24),
        "CHATS",
        "STATUS",
        "CALLS"
    )
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = TealDarkGreen,
        contentColor = Color.White,
        indicator = { tabPositions ->

            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 3.dp,
                color = Color.White
            )
        }
    ) {

        list.forEachIndexed { index, _ ->
            if(list[index]::class.java.typeName.contains("Painter")){ //Camera Tab
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    icon = {
                        Icon(
                            painterResource(id = R.drawable.baseline_photo_camera_24),
                            contentDescription = "camera",
                        )
                    }
                )
            }
            else {
                Tab(
                    text = {
                        Text(
                            list[index].toString(),
                            color = if (pagerState.currentPage == index) Color.White else Color.LightGray
                        )
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }

    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState) {
    /**
     *  make it scrollable
     */

    HorizontalPager(state = pagerState) {
            page ->
        when (page) {

            0 -> { cameraUI() }

            1 -> {
                val chats = FakeChatData.list
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        for (i in 0..chats.size-1) {
                            chatInfo(i = i, chats)
                            Divider(
                                color = Color(0xFFebebeb),
                                modifier = Modifier.padding(start = 70.dp, end = 22.dp)
                            )
                        }
                    }
                    FloatingActionButton(
                        onClick = {},
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(24.dp),
                        backgroundColor = LightGreen
                    ) {
                        Icon(
                            painterResource(id = R.drawable.baseline_comment_24),
                            tint = Color.White,
                            contentDescription = "Chat"
                        )
                    }

                }
            }

            2 -> {

                Box(modifier = Modifier.fillMaxSize()){

                    statusInfo()

                    FloatingActionButton(
                        onClick = { },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(24.dp),
                        backgroundColor = LightGreen
                    ) {
                        Icon(
                            painterResource(id = R.drawable.baseline_photo_camera_24),
                            tint = Color.White,
                            contentDescription = "Photo"
                        )
                    }
                }
            }

            3 ->{
                val calls = FakeCallData.list
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        for (i in 0..calls.size-1) {
                            callsInfo(i = i, calls)
                            Divider(
                                color = Color(0xFFebebeb),
                                modifier = Modifier.padding(start = 70.dp, end = 22.dp)
                            )
                        }
                    }
                    FloatingActionButton(
                        onClick = {
                        },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(24.dp),
                        backgroundColor = LightGreen
                    ) {
                        Icon(
                            painterResource(id = R.drawable.baseline_add_ic_call_24),
                            tint = Color.White,
                            contentDescription = "Call"
                        )
                    }
                }
            }
        }
    }
}
