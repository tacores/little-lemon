package com.bucchi.littlelemon

import android.content.SharedPreferences
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bucchi.littlelemon.component.Header

@Composable
fun Home(navController: NavController, sharedPreferences: SharedPreferences) {
    val context = LocalContext.current
    val inputStream = context.assets.open("profile.png")
    val profile = BitmapFactory.decodeStream(inputStream).asImageBitmap()

    Column {
        Header()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        )
        {
            Button(
                onClick = {
                    navController.navigate(Profile.route)
                },
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = Color(0xFF495E57)
                ),
            )
            {
                Image(
                    bitmap = profile,
                    contentDescription = "profile",
                    modifier = Modifier
                        .width(184.dp)
                        .height(179.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    //Home()
}

