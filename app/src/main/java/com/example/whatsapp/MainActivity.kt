package com.example.whatsapp

import android.graphics.Paint.Align
import android.media.Image
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.example.whatsapp.data.*
import com.example.whatsapp.ui.theme.*
import com.google.accompanist.pager.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import java.util.Objects

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatsAppTheme {

                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = TealDarkGreen,
                        darkIcons = false
                    )
                }

                HomePage()

            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun HomePage(){

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
    /**
     *  make it scrollable
     */

    HorizontalPager(state = pagerState) {
        page ->
        when (page) {
            0 -> {
                /**
                 *  add color change
                 */

                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)){
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 20.dp)
                    ) {
                        OutlinedButton(
                            onClick = { /*TODO*/ },
                            modifier= Modifier.size(80.dp),
                            shape = CircleShape,
                            border= BorderStroke(8.dp, Color.Gray),
                            contentPadding = PaddingValues(0.dp),
                            colors = ButtonDefaults.textButtonColors(
                                backgroundColor = Color.Black
                            )
                        ) {
                            Icon(Icons.Default.Add, contentDescription = null, tint = Color.Black)
                        }
                    }
                }

            }

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

@Composable
fun chatInfo(i : Int, chats : List<FakeChat>) {
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

@Composable
fun statusInfo(){

    /**
     *    use for loop and if
     *    for multiple users
     */

    Column(modifier= Modifier.fillMaxSize()) {
        val status = FakeStatusData.list
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .clickable { }
                .padding(10.dp)
       ){
            Row(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(id = status[0].image),
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
                        text = status[0].name,
                        modifier = Modifier.align(Alignment.TopStart)
                    )
                    Text(
                        text = status[0].info,
                        modifier = Modifier.align(Alignment.BottomStart),
                        color = Color.Gray
                    )
                }
            }
        }

        /**
         *    Check if it has recent or viewed updates
         */

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
                    painter = painterResource(id = status[1].image),
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
                        text = status[1].name,
                        modifier = Modifier.align(Alignment.TopStart)
                    )
                    Text(
                        text = status[1].info,
                        modifier = Modifier.align(Alignment.BottomStart),
                        color = Color.Gray
                    )
                }
            }
        }


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
                    painter = painterResource(id = status[2].image),
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
                        text = status[2].name,
                        modifier = Modifier.align(Alignment.TopStart)
                    )
                    Text(
                        text = status[2].info,
                        modifier = Modifier.align(Alignment.BottomStart),
                        color = Color.Gray
                    )
                }
            }
        }
    }
}


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