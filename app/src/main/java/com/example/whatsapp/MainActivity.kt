package com.example.whatsapp

import android.media.Image
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.example.whatsapp.data.FakeChatData
import com.example.whatsapp.ui.theme.LightGreen
import com.example.whatsapp.ui.theme.TealDarkGreen
import com.example.whatsapp.ui.theme.WhatsAppTheme
import com.google.accompanist.pager.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatsAppTheme {

                HomePage()

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun HomePage(){

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = TealDarkGreen,
            darkIcons = false
        )
    }

    HomeAppBar()

}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalPagerApi::class)
@Composable
private fun HomeAppBar() {

    Column(
        modifier = Modifier
        .fillMaxWidth()
    ) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.app_name),
                    color = Color.White,
                    modifier = Modifier
                        .padding(start = 2.dp)
                        .heightIn(max = 24.dp)
                )
            },
            backgroundColor = TealDarkGreen,
            actions = {
                IconButton( onClick = {} ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "search",
                        tint = Color.White
                    )
                }

                IconButton( onClick = {} ) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = "menu",
                        tint = Color.White
                    )
                }
            }
        )

        TabsLayout()
    }
}

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
            if(list[index]::class.java.typeName.contains("Painter")){
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

    HorizontalPager(state = pagerState) {
        page ->
        when (page) {
            0 -> {
                //Camera page
            }

            1 -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        for (i in 0..2) {
                            chatInfo(i = i)
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
                            painterResource(id = R.drawable.baseline_comment_24),
                            tint = Color.White,
                            contentDescription = "Chat"
                        )
                    }

                }
            }

            2 -> {

                //status page

                Box(modifier = Modifier.fillMaxSize()){
                    FloatingActionButton(
                        onClick = {
                        },
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

                //calls page

                Box(modifier = Modifier.fillMaxSize()){
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

@Composable
fun chatInfo(i : Int) {
    val chats = FakeChatData.list
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .height(75.dp)
            .padding(10.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = chats[i].image),
                "picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 13.dp, top = 4.dp, bottom = 4.dp, end = 10.dp)
            ) {
                Text(
                    text = chats[i].name,
                    modifier = Modifier.align(Alignment.TopStart)
                )
                Text(
                    text = chats[i].time,
                    modifier = Modifier.align(Alignment.TopEnd),
                    color = Color.Gray
                )
                Text(
                    text = chats[i].message,
                    modifier = Modifier.align(Alignment.BottomStart),
                    color = Color.Gray)
            }
        }
    }
}
