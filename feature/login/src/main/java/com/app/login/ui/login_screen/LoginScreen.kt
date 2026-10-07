package com.app.login.ui.login_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.ui.components.dropdown.BorderedTextDropdown
import com.app.ui.util.data.ScreenState

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {

}

@Composable
fun LoginScreenUi(
    modifier: Modifier = Modifier,
    screenData: ScreenState<LoginScreenData>
) {
    Row(
        modifier =  modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        AppInfoBox(
            modifier = Modifier.weight(0.4f)
        )

        Column(
            modifier = Modifier.weight(0.6f)
                .fillMaxSize()
                .background(color = Color.White)
                .padding(8.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {

            }

            Text(
                modifier = modifier
                    .align(Alignment.End)
                    .padding(end = 10.dp)
                    .background(color = Color.DarkGray, shape = RoundedCornerShape(percent = 50))
                    .padding(horizontal = 12.dp, vertical = 5.dp),
                text = "admin login",
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}

@Composable
fun AppInfoBox(
    modifier: Modifier = Modifier,
    languages: List<String> = listOf("en", "sk"),
    selectedLanguage: String = "en",
    onLanguageSelected: (String) -> Unit = {},
    appVersion: String = "R7.0.01",
    deviceId: String = "Device-1"
) {
    Column(modifier = modifier.background(color = Color.Black).padding(5.dp)) {
        BorderedTextDropdown(
            modifier = Modifier.width(80.dp),
            items = languages,
            itemToString = {it},
            placeholder = "Lang",
            selectedItem = selectedLanguage,
            onItemSelected = {_, lang -> onLanguageSelected(lang)}
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(com.app.ui.R.drawable.logo),
                contentDescription = "app logo",
                modifier = Modifier.size(100.dp),
            )

            Text(text = "App-Name", fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "App Version: ${appVersion}", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Device Id: ${deviceId}", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }


    }
}

@Preview(device ="spec:width=411dp,height=891dp,orientation=landscape")
@Composable
private fun LoginScreenPrev() {
    LoginScreenUi(screenData = ScreenState(LoginScreenData()))
}
