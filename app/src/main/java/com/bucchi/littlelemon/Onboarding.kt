package com.bucchi.littlelemon

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Onboarding() {
    Column {
        Header()
        Box(
            modifier = Modifier
                .background(Color(0xFF495E57))
                .fillMaxWidth()
                .height(120.dp)
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = "Let's get to know you",
                fontSize = 24.sp,
                color = Color(0xFFFFFFFF)
            )
        }
        InputFields()
        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 50.dp, 20.dp, 0.dp),
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color(0xFFF4CE14),
                contentColor = Color(0xFF333333),
                disabledContentColor = Color(0xFFAFAFAF)
            )
        ) {
            Text(
                text = "Register",
            )
        }
    }
}

@Composable
fun Header() {
    val context = LocalContext.current
    val inputStream = context.assets.open("logo.png")
    val logo = BitmapFactory.decodeStream(inputStream).asImageBitmap()
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    )
    {
        Image(
            bitmap = logo,
            contentDescription = "Logo",
            modifier = Modifier
                .width(200.dp)
                .height(80.dp)
        )
    }
}

@Composable
fun InputFields() {
    var firstName by remember { mutableStateOf(TextFieldValue("")) }
    var lastName by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }

    Text("Personal Information",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(5.dp, 15.dp, 0.dp, 15.dp))
    Text("First name",
        fontSize = 12.sp,
        modifier = Modifier
            .padding(10.dp, 10.dp, 0.dp, 0.dp))
    TextField(
        value = firstName,
        onValueChange = { newText ->
            firstName = newText
        },
        modifier = Modifier
            .padding(10.dp, 0.dp, 0.dp, 0.dp)
            .clip(shape = RoundedCornerShape(6.dp)))
    Text("Last name",
        fontSize = 12.sp,
        modifier = Modifier
            .padding(10.dp, 10.dp, 0.dp, 0.dp))
    TextField(
        value = lastName,
        onValueChange = { newText ->
            lastName = newText
        },
        modifier = Modifier
            .padding(10.dp, 0.dp, 0.dp, 0.dp)
            .clip(shape = RoundedCornerShape(6.dp)))
    Text("Email",
        fontSize = 12.sp,
        modifier = Modifier
            .padding(10.dp, 10.dp, 0.dp, 0.dp))
    TextField(
        value = email,
        onValueChange = { newText ->
            email = newText
        },
        modifier = Modifier
            .padding(10.dp, 0.dp, 0.dp, 0.dp)
            .clip(shape = RoundedCornerShape(6.dp)))
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    Onboarding()
}