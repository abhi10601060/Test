package com.app.login.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.login.ui.location_selection.LandscapePreview
import com.app.ui.components.buttons.RegularTextButton
import com.app.ui.components.icon.CircularBackgroundIcon
import com.app.ui.components.lists.PartList

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

}

@Composable
fun MainScreenUi(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        MainScreenHeader(modifier = Modifier.fillMaxWidth(), type = "ABC", value = "897654", location = "Pune 1")

        Row(modifier = Modifier.fillMaxWidth().weight(1f).background(color = Color.Yellow)
        ) {
            Box(
                modifier = Modifier.fillMaxHeight().weight(0.65f)
                    .background(color = Color.Black)
            ) {


                Text(
                    modifier = Modifier.align(Alignment.BottomStart).background(color = Color.Black).padding(5.dp),
                    text = "ABCD:ABCD EFGHIJ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            PartList(modifier = Modifier.fillMaxHeight().weight(0.4f))
        }

        MainScreenBottomBar(modifier = Modifier.fillMaxWidth())
    }


}

@Composable
fun MainScreenHeader(
    modifier: Modifier = Modifier,
    type: String,
    value: String,
    location: String
) {

    Row(
        modifier = modifier
            .background(color = Color.DarkGray)
            .padding(horizontal = 15.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "${type}:${value}", fontSize = 28.sp, color = Color.White)
        Text(text = location, fontSize = 28.sp, color = Color.White)
    }
}

@Composable
fun MainScreenBottomBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(color = Color.DarkGray)
            .padding(horizontal = 15.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        RegularTextButton(text = "Cancel Process", onClick = {})
        RegularTextButton(text = "Cancel Process", onClick = {})
        CameraControls()
        RegularTextButton(text = "Cancel Process", onClick = {})
        RegularTextButton(text = "Cancel Process", onClick = {})
    }
}

@Composable
fun CameraControls(
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier.wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularBackgroundIcon(
            modifier = Modifier,
            size = 30.dp,
            imageVector = Icons.Default.Add,
            bgColor = Color.White.copy(alpha = 0.3f),
            iconTint = Color.White
        )


        Icon(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .size(60.dp)
                .clickable {},
            imageVector = Icons.Default.Camera,
            contentDescription = "Capture Button",
            tint = Color.Blue
        )

        CircularBackgroundIcon(
            modifier = Modifier,
            size = 30.dp,
            imageVector = Icons.Default.Remove,
            bgColor = Color.White.copy(alpha = 0.3f),
            iconTint = Color.White
        )
    }
}

@LandscapePreview
@Composable
fun MainScreenPreview(){
    MainScreenUi(
        modifier = Modifier.fillMaxSize()
    )
}