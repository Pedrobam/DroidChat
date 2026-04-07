package br.com.droidchat.model.fake

import br.com.droidchat.model.Chat

val chat1 = Chat(
    id = 1,
    lastMessage = "Last Message",
    members = listOf(user1, user2),
    unreadCount = 2,
    timestamp = "12:00"
)

val chat2 = Chat(
    id = 2,
    lastMessage = "Como vai?",
    members = listOf(user1, user3),
    unreadCount = 0,
    timestamp = "15:00"
)


val chat3 = Chat(
    id = 3,
    lastMessage = "Oiiii",
    members = listOf(user1, user4),
    unreadCount = 2,
    timestamp = "15:00"
)