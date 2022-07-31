package com.example.whatsapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.whatsapp.data.FakeChat
import com.example.whatsapp.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState



@Composable
fun chatInfo(i : Int, chats : List<FakeChat>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {  }
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

@OptIn(ExperimentalPagerApi::class)
@Composable
fun chatRoom(){
    val pagerState = rememberPagerState(1)
    VerticalPager(state = pagerState) {
            page ->
        when (page) {
            0 -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.background),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }


}