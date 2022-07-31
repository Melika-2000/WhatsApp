package com.example.whatsapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whatsapp.data.FakeStatus
import com.example.whatsapp.data.FakeStatusData
import com.example.whatsapp.ui.theme.LightGreen
import com.example.whatsapp.ui.theme.TealDarkGreen
import com.example.whatsapp.ui.theme.WhiteChocolate


@Composable
fun statusInfo(){

    Column(modifier= Modifier.fillMaxSize()) {

        val status = FakeStatusData.list
        var hasRecent = false
        var hasViewed = false

        myInfoStatus(status = status[0])

        for(i in 1 .. status.size-1){

            if (status[i].isRecent){
                if (hasRecent == false){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = WhiteChocolate)
                            .height(30.dp)
                            .padding(start = 10.dp)
                    ) {
                        Text(
                            text = "Recent updates",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = TealDarkGreen,
                            modifier = Modifier.align(Alignment.CenterStart)
                        )
                    }
                    hasRecent = true
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(75.dp)
                        .clickable { }
                        .padding(start = 7.dp)
                        .padding(10.dp)
                ){
                    Row(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = status[i].image),
                            "picture",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(54.dp)
                                .clip(CircleShape)
                                .border(2.dp, LightGreen, CircleShape)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 13.dp, top = 5.dp, bottom = 5.dp, end = 10.dp)
                        ) {
                            Text(
                                text = status[i].name,
                                modifier = Modifier.align(Alignment.TopStart)
                            )
                            Text(
                                text = status[i].info,
                                modifier = Modifier.align(Alignment.BottomStart),
                                color = Color.Gray
                            )
                        }
                    }
                }


            }
            else {

                if (hasViewed == false){
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = WhiteChocolate)
                            .height(30.dp)
                            .padding(start = 10.dp)
                    ) {
                        Text(
                            text = "Viewed updates",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = TealDarkGreen,
                            modifier = Modifier.align(Alignment.CenterStart)
                        )
                    }
                    hasViewed = true
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(75.dp)
                        .clickable { }
                        .padding(start = 7.dp)
                        .padding(10.dp)
                ){
                    Row(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = status[i].image),
                            "picture",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(54.dp)
                                .clip(CircleShape)
                                .border(2.dp, Color.LightGray, CircleShape)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 13.dp, top = 5.dp, bottom = 5.dp, end = 10.dp)
                        ) {
                            Text(
                                text = status[i].name,
                                modifier = Modifier.align(Alignment.TopStart)
                            )
                            Text(
                                text = status[i].info,
                                modifier = Modifier.align(Alignment.BottomStart),
                                color = Color.Gray
                            )
                        }
                    }
                }
            }


        }
    }
}

@Composable
fun myInfoStatus(status: FakeStatus){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .clickable { }
            .padding(10.dp)
    ){
        Row(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = status.image),
                "picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(65.dp)
                    .clip(CircleShape)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, top = 5.dp, bottom = 5.dp, end = 10.dp)
            ) {
                Text(
                    text = status.name,
                    modifier = Modifier.align(Alignment.TopStart)
                )
                Text(
                    text = status.info,
                    modifier = Modifier.align(Alignment.BottomStart),
                    color = Color.Gray
                )
            }
        }
    }
}