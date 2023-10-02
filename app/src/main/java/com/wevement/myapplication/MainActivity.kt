package com.wevement.myapplication


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalComposeUiApi::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

@Composable
fun HomeScreen(viewModel: MainViewModel = viewModel()) {
    var (text, setText) = remember{
        mutableStateOf("Hello World!")
    }
    Column (){
        Text(text = "Hello World!")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Click me!")
        }
        TextField(value = text, onValueChange = setText)
    }
}

class MainViewModel : ViewModel() {
    //뮤터블 스테이트오브는 익기 쓰기가 모두 가능 까보면 오버라이드 var로 되어있음 변수가 두개인데 각각 게터 세터로 되어있음
    private val _value = mutableStateOf("Hello World!")
    val value: State<String> = _value // 스테이트는 읽기만 가능 까보면 val로 선언되어있어서 변경불가
}