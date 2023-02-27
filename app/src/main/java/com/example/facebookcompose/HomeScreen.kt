package com.example.facebookcompose


import android.os.Bundle
import android.widget.EditText
import android.widget.TableRow
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Tab
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.facebookcompose.ui.theme.BGray
import com.example.facebookcompose.ui.theme.BrandBlue
import com.example.facebookcompose.ui.theme.FacebookComposeTheme
import androidx.compose.material.TabRow
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.input.ImeAction
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun HomeScreen() {
    Box(
        Modifier
            .background(Color.Gray)
            .fillMaxSize()
    ) {
        Column() {
            TopAppBar()
            TabBar()
            StatusUpdateBar(
                avatarUrl = "\"https://www.collephoto.com/wp-content/uploads/2023/02/tuscany242_websize-683x1024.jpg\"",
                onTextChange = {

                },
                onSendClick = {

                }
            )
        }
    }
}

@Composable
private fun TopAppBar() {
    Surface() {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
                color = BrandBlue,
                fontWeight = FontWeight.Medium
            )
            Spacer(Modifier.weight(1f))
            IconButton(
                onClick = { },
                Modifier
                    .clip(CircleShape)
                    .background(BGray)
            ) {
                Icon(Icons.Rounded.Search, contentDescription = stringResource(R.string.search))
            }
            Spacer(Modifier.width(8.dp))
            IconButton(
                onClick = { },
                Modifier
                    .clip(CircleShape)
                    .background(BGray)
            ) {
                Icon(
                    Icons.Rounded.ChatBubble,
                    contentDescription = stringResource(R.string.chatbubble)
                )
            }
        }
    }
}

data class TabItem(
    val icon: ImageVector,
    val contentDescription: String,
)

@Composable
private fun TabBar() {
    Surface() {
        var index by remember {
            mutableStateOf(0)
        }
        TabRow(
            selectedTabIndex = index,
            backgroundColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ) {
            val tabs = listOf(
                TabItem(Icons.Rounded.Home, stringResource(R.string.home)),
                TabItem(Icons.Rounded.Tv, stringResource(R.string.reels)),
                TabItem(Icons.Rounded.Store, stringResource(R.string.store)),
                TabItem(Icons.Rounded.Newspaper, stringResource(R.string.newspaper)),
                TabItem(Icons.Rounded.Notifications, stringResource(R.string.notification)),
                TabItem(Icons.Rounded.Menu, stringResource(R.string.menu)),

                )

            tabs.forEachIndexed { i, item ->
                Tab(
                    modifier = Modifier.padding(top = 8.dp),
                    selected = index == i,
                    onClick = { index = i },
                    selectedContentColor = BrandBlue,
                ) {
                    Icon(item.icon, contentDescription = item.contentDescription)
                }
            }
        }
    }
}

@Composable
private fun StatusUpdateBar(
    onTextChange: (String) -> Unit,
    avatarUrl: String,
    onSendClick: () -> Unit,
) {
    Surface() {
        Column() {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(
                        LocalContext.current
                    ).data(avatarUrl)
                        .crossfade(true)
                        .placeholder(R.drawable.ic_placeholder)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                //Spacer(modifier = Modifier.width(8.dp))
                var text by remember {
                    mutableStateOf("")
                }
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent

                    ),
                    modifier = Modifier.fillMaxWidth(),
                    value = text,
                    onValueChange = {
                        text = it
                            onTextChange(it)
                    },
                    placeholder = { Text(stringResource(R.string.OnYourMind)) },
                    keyboardActions = KeyboardActions(
                        onSend = {
                            onSendClick()
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Send
                    )
                )
            }
        }
    }
}


