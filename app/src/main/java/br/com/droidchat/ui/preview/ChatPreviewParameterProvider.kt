package br.com.droidchat.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.droidchat.model.Chat
import br.com.droidchat.model.fake.chat1
import br.com.droidchat.model.fake.chat2
import br.com.droidchat.model.fake.chat3

class ChatPreviewParameterProvider : PreviewParameterProvider<Chat> {
    override val values: Sequence<Chat> = sequenceOf(
        chat1,
        chat2,
        chat3,
    )
}