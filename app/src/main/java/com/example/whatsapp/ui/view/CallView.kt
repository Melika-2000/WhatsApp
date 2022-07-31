package com.example.whatsapp.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.whatsapp.data.FakeCall
import com.example.whatsapp.ui.theme.LightGreen
import com.example.whatsapp.ui.theme.semiLightGreen
import com.example.whatsapp.R

@Composable
fun callsInfo(i : Int, calls : List<FakeCall>){

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { }
            .height(75.dp)
            .padding(10.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = calls[i].image),
                "picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
            )
            Box(
                modifier = Modifier
                    .width(280.dp)
                    .padding(start = 13.dp, top = 4.dp, bottom = 4.dp, end = 10.dp)
            ) {

                Column(modifier = Modifier.fillMaxSize()) {

                    var rotationDeg: Float = 0f
                    var isMissed: Color = LightGreen
                    Text( text = calls[i].name)

                    Row(modifier = Modifier.fillMaxWidth()){

                        if(calls[i].isCaller == false)
                            rotationDeg = 180f

                        if(calls[i].isMissed)
                            isMissed = Color.Red
                        Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                            Icon(
                                painterResource(id = R.drawable.baseline_north_east_24),
                                tint = isMissed,
                                contentDescription = "call",
                                modifier = Modifier
                                    .rotate(rotationDeg)
                                    .size(15.dp),
                            )
                        }

                        Spacer(modifier = Modifier.size(3.dp))

                        Text(
                            text = calls[i].time,
                            color = Color.Gray
                        )
                    }
                }

            }
            Box(modifier = Modifier.align(Alignment.CenterVertically)){
                if ( calls[i].isVideoCall )
                    Icon(
                        painterResource(id = R.drawable.baseline_videocam_24),
                        tint = semiLightGreen,
                        contentDescription = "call"
                    )
                else{
                    Icon(
                        imageVector = Icons.Filled.Call,
                        tint = semiLightGreen,
                        contentDescription = "call",
                    )
                }
            }
        }
    }
}