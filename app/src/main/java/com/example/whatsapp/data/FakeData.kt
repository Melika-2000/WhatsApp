package com.example.whatsapp.data

import androidx.compose.ui.graphics.Color
import com.example.whatsapp.R
import com.example.whatsapp.ui.theme.LightGreen

data class FakeChat(
    val name: String,
    val time: String,
    val message: String,
    val image: Int,
)

data class FakeStatus(
    val name: String,
    val info: String,
    val image: Int,
    val isRecent: Boolean
)

data class FakeCall(
    val name: String,
    val time: String,
    val image: Int,
    val isVideoCall: Boolean,
    val isMissed: Boolean,
    val isCaller: Boolean
)

object FakeChatData{
    val list = listOf(
        FakeChat(
            "Programmers",
            "17:45",
            "sara: hello",
            R.drawable.code,
        ),
        FakeChat(
            "Family",
            "15:40",
            "Mom: good",
            R.drawable.flower,
        ),
        FakeChat(
            "Yasaman",
            "9:20",
            "Where are you? we should be there in...",
            R.drawable.girl,
        ),
        FakeChat(
            "Negar",
            "9:00",
            "GoodBye, see you later",
            R.drawable.nature,
        )
    )
}


object FakeStatusData{
    val list = listOf(
        FakeStatus(
            "My Status",
            "tap to add status update",
            R.drawable.status,
            false
        ),
        FakeStatus(
            "Yasaman",
            "3 minutes ago, 18:30 p.m.",
            R.drawable.girl,
            true
        ),
        FakeStatus(
            "Sara",
            "1 hour ago, 17:33 p.m.",
            R.drawable.cat,
            false
        ),
        FakeStatus(
            "Negar",
            "10 hours ago, 10:38 a.m.",
            R.drawable.nature,
            false
        ),
    )
}

object FakeCallData{
    val list = listOf(
        FakeCall(
            "Yasaman",
            "28 July, 21:49",
            R.drawable.girl,
            false,
            false,
            false
        ),
        FakeCall(
            "Sara",
            "18 July, 16:30",
            R.drawable.cat,
            true,
            false,
            true
        ),
        FakeCall(
            "Sara",
            "18 July, 16:25",
            R.drawable.cat,
            true,
            true,
            true
        ),
        FakeCall(
            "Negar",
            "29 June, 11:00",
            R.drawable.nature,
            false,
            true,
            false
        ),
        FakeCall(
            "Negar",
            "29 June, 10:50",
            R.drawable.nature,
            false,
            true,
            true
        ),
        FakeCall(
            "Negar",
            "29 June, 10:40",
            R.drawable.nature,
            true,
            true,
            false
        ),
        FakeCall(
            "Yasaman",
            "28 June, 21:49",
            R.drawable.girl,
            false,
            false,
            false
        ),
        FakeCall(
            "Yasaman",
            "28 June, 20:09",
            R.drawable.girl,
            true,
            false,
            false
        ),
        FakeCall(
            "Negar",
            "22 June, 10:00",
            R.drawable.nature,
            false,
            false,
            false
        ),
        FakeCall(
            "Negar",
            "22 June, 9:00",
            R.drawable.nature,
            false,
            true,
            true
        ),

    )

}