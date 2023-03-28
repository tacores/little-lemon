package com.bucchi.littlelemon

import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.view.MenuItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bucchi.littlelemon.component.Header
import com.bumptech.glide.integration.compose.GlideImage
import androidx.compose.runtime.livedata.observeAsState
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi

@Composable
fun Home(navController: NavController, database: AppDatabase) {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    Column {
        HomeNavbar(navController)
        Hero(searchText)
        MenuBreakdown()
        MenuItems(database, searchText)
    }
}

@Composable
fun HomeNavbar(navController: NavController) {
    val context = LocalContext.current
    var inputStream = context.assets.open("logo.png")
    val logo = BitmapFactory.decodeStream(inputStream).asImageBitmap()
    inputStream = context.assets.open("profile.png")
    val profile = BitmapFactory.decodeStream(inputStream).asImageBitmap()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            //.wrapContentSize(Alignment.Center)
    )
    {
        Image(
            bitmap = logo,
            contentDescription = "Logo",
            modifier = Modifier
                .width(200.dp)
                .height(80.dp)
                .align(Alignment.Center)
        )
        Image(
            bitmap = profile,
            contentDescription = "profile",
            modifier = Modifier
                .width(70.dp)
                .height(70.dp)
                .padding(0.dp, 0.dp, 10.dp, 0.dp)
                .clickable {}
                .align(Alignment.CenterEnd),
        )
    }
}

@Composable
fun Hero(searchText: TextFieldValue) {

    val context = LocalContext.current
    val inputStream = context.assets.open("hero.png")
    val hero_image = BitmapFactory.decodeStream(inputStream).asImageBitmap()

    Box(
        modifier = Modifier
            .background(Color(0xFF495E57))
            .fillMaxWidth()
            .padding(10.dp, 10.dp, 10.dp, 10.dp)
    ) {
        Column() {
            Text("Little Lemon",
                color = Color(0xFFF4CE14),
                fontSize = 36.sp,
                //modifier = Modifier
                //    .padding(10.dp, 3.dp, 0.dp, 0.dp)
            )
            Row() {
                Column() {
                    Text(
                        "Chicago",
                        color = Color(0xFFFFFFFF),
                        fontSize = 28.sp,
                        //modifier = Modifier
                        //    .padding(10.dp, 3.dp, 0.dp, 0.dp)
                    )
                    Text(
                        "We are a family-owned\n" +
                                "Mediterranean restaurant,\n" +
                                "focused on traditional\n" +
                                "recipes served with a\n" +
                                "modern twist.",
                        color = Color(0xFFFFFFFF),
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(0.dp, 15.dp, 0.dp, 0.dp)
                    )
                }
                Image(bitmap = hero_image, contentDescription = "hero",
                    modifier = Modifier
                        .padding(50.dp, 0.dp, 20.dp, 10.dp)
                        .clip(shape = RoundedCornerShape(30.dp))
                        .height(150.dp),
                    contentScale = ContentScale.FillWidth)
            }
            TextField(value = searchText,
                onValueChange = {  },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color(0xFFFFFFFF),
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(10.dp))
            )
        }
    }
}

@Composable
fun MenuBreakdown() {
    Box() {
        Column() {
            Text("ORDER FOR DELIVERY!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(5.dp, 15.dp, 0.dp, 0.dp))
            Row() {
                FilterButton("Starters")
                FilterButton("Mains")
                FilterButton("Desserts")
                FilterButton("Drinks")
            }
        }
    }
}

@Composable
fun MenuItems(database: AppDatabase, searchText: TextFieldValue) {

    val databaseMenuItems by database.menuItemDao().getAll().observeAsState(emptyList())

    LazyColumn() {
        itemsIndexed(databaseMenuItems.filter {
            it.title.contains(
                searchText.text,
                ignoreCase = true
            )
        } ) { index, it ->
            Divider(
                color = Color.LightGray, thickness = 1.dp,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            MenuItem(
                title = it.title,
                description = it.description,
                price = it.price,
                image = it.image
            )
        }
    }

}

@Composable
fun FilterButton(str: String) {
    Button(onClick = {},
        modifier = Modifier
            .padding(5.dp, 5.dp, 0.dp, 5.dp),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = Color(0xFFAFAFAF),
            contentColor = Color(0xFF495E57),
            disabledContentColor = Color(0xFFAFAFAF)),
    ) {
        Text(str)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(title: String, description: String, price: String, image: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column (){
            Text(
                text = title,
            )
            Text(
                text = description,
                maxLines = 3,
                modifier = Modifier
                    .width(250.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "$ $price",
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        GlideImage(
            model = image,
            contentDescription = title,
            modifier = Modifier
                .size(80.dp),
            contentScale = ContentScale.Crop
        )
    }

}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    //Home()
}

