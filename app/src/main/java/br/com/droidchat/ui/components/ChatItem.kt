package br.com.droidchat.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import br.com.droidchat.R
import br.com.droidchat.ui.theme.DroidChatTheme

@Composable
fun ChatItem(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        val (avatarRef,
            firstNameRef,
            lastMessageRef,
            lastTimeRef,
            unreadCountRef) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.no_profile_image),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(60.dp)
                .constrainAs(avatarRef) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                }
        )

        Text(
            text = "User Name",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(firstNameRef) {
                    width = Dimension.fillToConstraints
                    top.linkTo(avatarRef.top)
                    start.linkTo(avatarRef.end, margin = 16.dp)
                    end.linkTo(lastTimeRef.start, margin = 16.dp)
                    bottom.linkTo(lastMessageRef.top)
                }
        )

        Text(
            text = "Last Message",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.constrainAs(lastMessageRef) {
                width = Dimension.fillToConstraints
                top.linkTo(firstNameRef.bottom)
                start.linkTo(avatarRef.end, margin = 16.dp)
                bottom.linkTo(avatarRef.bottom)
                end.linkTo(unreadCountRef.start, margin = 16.dp)
            }
        )

        Text(
            text = "12:00",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.constrainAs(lastTimeRef) {
                width = Dimension.wrapContent
                top.linkTo(firstNameRef.top)
                end.linkTo(parent.end)
                bottom.linkTo(unreadCountRef.top)
            }
        )

        Text(
            text = "2",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(horizontal = 4.dp)
                .constrainAs(unreadCountRef) {
                    width = Dimension.wrapContent
                    top.linkTo(lastTimeRef.bottom)
                    end.linkTo(parent.end)
                    bottom.linkTo(lastMessageRef.bottom)
                }
        )
    }
}

@Preview
@Composable
private fun ChatItemPreview() {
    DroidChatTheme {
        ChatItem()
    }
}