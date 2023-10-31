@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.jetpacklearning

import android.annotation.SuppressLint
import android.graphics.Paint
import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

import com.example.jetpacklearning.ui.theme.JetpackLearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackLearningTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationBarKullanimi()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackLearningTheme {
        NavigationBarKullanimi()
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBarKullanimi() {
    val items = listOf("Bir", "İki")
    val secilenItem = remember { mutableStateOf(0) }
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Başlık") }) },
        content = {
            if (secilenItem.value == 0) {
                SayfaBir() }
            if (secilenItem.value == 1) {
                SayfaIki() } },
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = secilenItem.value == index,
                        onClick = { secilenItem.value = index },
                        label = { Text(text = item) },
                        icon = {
                            when (item) {
                                "Bir" -> Icon(
                                    painter = painterResource(id = R.drawable.close),
                                    contentDescription = "")

                                "İki" -> Icon(
                                    painter = painterResource(id = R.drawable.info),
                                    contentDescription = "")
                            }

                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Blue,
                            unselectedIconColor = Color.Yellow,
                            selectedTextColor = Color.Gray,
                            unselectedTextColor = Color.Green,
                            indicatorColor = Color.Cyan //belirteç rengi
                        )
                    )
                }

            }
        }
    )
}


        @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun TopApBarAramaOzelligiEkle() {
            val aramaKontol = remember { mutableStateOf(false) }
            val alinanVeri = remember { mutableStateOf("") }


            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            if (aramaKontol.value) {
                                TextField(
                                    value = alinanVeri.value, onValueChange = {
                                        alinanVeri.value = it
                                        Log.e("arama", "arama :  $it ")
                                    },
                                    label = { Text(text = "Ara") },
                                    colors = TextFieldDefaults.textFieldColors(
                                        containerColor = Color.Transparent,
                                        focusedLabelColor = Color.White,
                                        focusedIndicatorColor = Color.White,
                                        unfocusedIndicatorColor = Color.White,
                                        unfocusedLabelColor = Color.White
                                    )
                                )

                            } else {
                                Text(text = " Başlık")
                            }

                        }, colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = colorResource(id = R.color.anaRenk),
                            titleContentColor = Color.Black,
                        ),
                        actions = {
                            if (aramaKontol.value) {

                                IconButton(onClick = {
                                    aramaKontol.value = false
                                    alinanVeri.value = ""

                                })
                                {
                                    Icon(
                                        painter = painterResource(id = R.drawable.close),
                                        contentDescription = " ",

                                        )
                                }
                            } else {
                                IconButton(onClick = {
                                    aramaKontol.value = true

                                })
                                {
                                    Icon(
                                        painter = painterResource(id = R.drawable.search),
                                        contentDescription = " ",
                                        tint = Color.Blue

                                    )
                                }
                            }


                        })
                },


                content = {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,

                        ) {

                        Text(
                            text = "ADINI ANDROİDE YAZDIM YARİM <3 MÜCAHİT BEDİR",
                            fontSize = 55.sp,
                            color = Color.Blue,
                            fontWeight = FontWeight.Thin,
                            style = TextStyle(background = Color.Yellow)
                        )

                    }

                }

            )
        }


        @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
        fun TopApBarKullanimi() {
            val menuAcilisKontrol = remember { mutableStateOf(false) }


            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Column {
                                Text(text = "Üst Başlık")
                                Text(text = "Alt Başlık", fontSize = 16.sp)


                            }
                        }, colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = colorResource(id = R.color.anaRenk),
                            titleContentColor = Color.Black,
                        ),
                        actions = {
                            Text(text = "Çıkış", color = Color.Red, modifier = Modifier.clickable {
                                Log.e("TopAppBar", "Çıkış seçildi")
                            })
                            IconButton(onClick = {

                                Log.e("TopAppBar", "İnfo seçildi")
                            })
                            {
                                Icon(
                                    painter = painterResource(id = R.drawable.info),
                                    contentDescription = " ",
                                    tint = Color.Red
                                )
                            }
                            IconButton(onClick = {
                                menuAcilisKontrol.value = true
                                Log.e("TopAppBar", "more seçildi")
                            })
                            {
                                Icon(
                                    painter = painterResource(id = R.drawable.more),
                                    contentDescription = " ",
                                    tint = Color.Blue

                                )
                            }
                            DropdownMenu(
                                expanded = menuAcilisKontrol.value,
                                onDismissRequest = { menuAcilisKontrol.value = false }) {
                                DropdownMenuItem(
                                    text = { Text("Sil") },
                                    onClick = { Log.e("TopAppBar", "sil seçildi") })
                                DropdownMenuItem(
                                    text = { Text("Güncelle") },
                                    onClick = { Log.e("TopAppBar", "Güncelle seçildi") })

                            }


                        })
                },


                content = {

                }

            )
        }

        @Composable
        fun DinamikDropdownMenuKullanimi() {
            val menuKontrol = remember { mutableStateOf(false) }
            val ulkeIsimleri = listOf("Türkiye", "Almanya", "Amerika", "Fransa", "Japonya", "Çin")
            val secilenindeks = remember {
                mutableStateOf(0)
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .size(100.dp, 55.dp)
                            .clickable {
                                menuKontrol.value = true
                            }


                    )
                    {
                        Text(text = ulkeIsimleri[secilenindeks.value])
                        Image(
                            painter = painterResource(R.drawable.drop_down_resmi),
                            contentDescription = ""
                        )


                    }
                    DropdownMenu(expanded = menuKontrol.value,
                        onDismissRequest = { menuKontrol.value = false }) {

                        ulkeIsimleri.forEachIndexed { indeks, ulke ->
                            DropdownMenuItem(text = { Text(ulke) }, onClick = {
                                Log.e("menu", "Ülke seçildi: $ulke")
                                menuKontrol.value = false
                                secilenindeks.value = indeks


                            })
                        }

                    }
                }
                Button(onClick = {
                    menuKontrol.value = false
                    Log.e("menu", "Ülke seçildi: ${ulkeIsimleri[secilenindeks.value]}")

                }) {
                    Text(text = "Menüyü Göster")

                }

            }

        }

        @Composable
        fun DropdownMenuKullanimi() {
            val menuAcilisKontrol = remember { mutableStateOf(false) }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    Button(onClick = { menuAcilisKontrol.value = true }) {
                        Text(text = "İçindekiler")

                    }
                    DropdownMenu(
                        expanded = menuAcilisKontrol.value,
                        onDismissRequest = { menuAcilisKontrol.value = false }) {
                        DropdownMenuItem(text = { Text("Anasayfa") },
                            onClick = { menuAcilisKontrol.value = false })
                        DropdownMenuItem(
                            text = { Text("Çıkış") },
                            onClick = { menuAcilisKontrol.value = false })

                    }

                }
            }
        }

        @SuppressLint("SetJavaScriptEnabled")
        @Composable
        fun WebViewKullanimi() {
            val url = "https://gelecegiyazanlar.turkcell.com.tr/"
            AndroidView(factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams( //web siteyi sayfaya yaymak için
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = WebViewClient()
                    loadUrl(url)

                }
            }, update = { it.loadUrl(url) }) // sayfayı güncellemek için

        }

        @Composable
        fun SliderKullanimi() {
            val sliderDeger = remember { mutableStateOf(0f) }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(text = "Sonuç: ${sliderDeger.value.toInt()}")
                Slider(
                    value = sliderDeger.value,
                    onValueChange = { sliderDeger.value = it },
                    valueRange = 0f..100f,
                    modifier = Modifier.padding(all = 20.dp),

                    colors = SliderDefaults.colors(
                        thumbColor = Color.Blue,
                        activeTrackColor = Color.Cyan,
                        inactiveTrackColor = Color.Yellow
                    )


                )

            }
        }

        @Composable
        fun ProgressIndicatorKullanimi() {
            val progresDurum = remember { mutableStateOf(false) }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                if (progresDurum.value) {
                    CircularProgressIndicator(color = Color.Red)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(onClick = { progresDurum.value = true }) {
                        Text(text = "Progressi Başlat")

                    }
                    Button(onClick = { progresDurum.value = false }) {
                        Text(text = "Progressi Durdur")
                    }
                }

            }
        }

        @Composable
        fun SayfaTiklanma() {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier
                    .size(100.dp)
                    .background(Color.Red)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                Log.e("Box", "Tıklandı")
                            },
                            onDoubleTap = {
                                Log.e("Box", " Çift Tıklandı")

                            },
                            onLongPress = {
                                Log.e("Box", "Üzerine uzun basıldı")
                            }
                        )
                    })
            }
        }


        @SuppressLint("UnrememberedMutableState")
        @Composable
        fun SwitchKullanimi() {
            val switchDurum = remember { mutableStateOf(false) }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Switch(
                    checked = switchDurum.value,
                    onCheckedChange = {
                        switchDurum.value = it
                        Log.e("switch seçildi", it.toString())
                    },
                    colors = SwitchDefaults.colors(
                        checkedTrackColor = Color.Blue,
                        checkedThumbColor = Color.Yellow,
                        uncheckedTrackColor = Color.Red,
                        uncheckedThumbColor = Color.Black,

                        )
                )
                Button(onClick = { Log.e("Switch Son Durum", switchDurum.value.toString()) }) {
                    Text(text = "Göster")
                }
            }
        }

        @OptIn(ExperimentalMaterial3Api::class)
        @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
        @Composable
        fun Sayfa() {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text(text = "İlham Ver") },
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = colorResource(id = R.color.anaRenk),
                            titleContentColor = colorResource(id = R.color.white)
                        )
                    )


                },
                content = {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = R.drawable.stevejobs),
                                contentDescription = ""
                            )
                            Text(
                                text = "Steve Jobs",
                                color = Color.Red,
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp
                            )

                        }
                        Text(
                            text = "Dünyayı değiştirecek insanlar," +
                                    " onu değiştirebileceklerini düşünecek kadar çılgın olanlardır.",
                            modifier = Modifier.padding(all = 10.dp),
                            textAlign = TextAlign.Center,
                        )
                        Button(
                            onClick = { Log.e("Button", "İlham Verildi") },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Red,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "İlham Ver")

                        }
                    }

                }

            )
        }


/**
Row {//görsel nesneleri yan yana göstermemize olanak sağlar
Box(modifier = Modifier
.size(80.dp)
.background(Color.Red))
Box(modifier = Modifier
.size(50.dp)
.background(Color.Gray))
Box(modifier = Modifier
.size(95.dp)
.background(Color.Green))

}**/
/**Column {//görsel nesneleri alt alta göstermemize olanak sağlar
Box(modifier = Modifier
.size(30.dp)
.background(Color.Black))
Box(modifier = Modifier
.size(35.dp)
.background(Color.Cyan))
Box(modifier = Modifier
.size(65.dp)
.background(Color.Magenta))

} **/
/** Box {//görsel nesneleri üst üste göstermemize olanak sağlar
Box(modifier = Modifier
.size(55.dp)
.background(Color.Yellow))
Box(modifier = Modifier
.size(45.dp)
.background(Color.Blue))
Box(modifier = Modifier
.size(25.dp)
.background(Color.White))
Text(text = "Selam")

} **/


