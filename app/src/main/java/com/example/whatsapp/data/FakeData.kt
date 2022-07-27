package com.example.whatsapp.data

import com.example.whatsapp.R

data class FakeChat(
    val name: String,
    val time: String,
    val message: String,
    val image : Int,
    val id: Int
)

data class FakeStatus(
    val status: String
)

data class FakeCalls(
    val callerName: String,
    val time: String,
    val videoCall: Boolean,
    val missed: Boolean
)

object FakeChatData{
    val list = listOf(
        FakeChat(
            "Programmers",
            "17:45",
            "sara: hello",
            R.drawable.code,
            0
        ),
        FakeChat(
            "Family",
            "15:40",
            "Mom: good",
            R.drawable.flower,
            1
        ),
        FakeChat(
            "Yasaman",
            "9:20",
            "Where are you? we should be there in...",
            R.drawable.girl,
            2
        )
    )
}