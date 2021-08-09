package com.jason.main.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.accompanist.pager.ExperimentalPagerApi
import com.jason.common.R
import com.jason.common.ui.theme.ColorPrimary
import com.jason.common.ui.theme.whiteNormal14
import com.jason.main.ui.adapter.GuideAdapter
import com.jason.main.vm.GuideViewModel
import com.jason.router.Routes
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
@Route(path = Routes.GUIDE)
class GuideActivity : ComponentActivity() {

    private val pics = mutableListOf(R.drawable.img_guide_one,
        R.drawable.img_guide_two,
        R.drawable.img_guide_three,
        R.drawable.img_guide_four)


    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentView()
        }
    }

    @Composable
    private fun ContentView() {
        val vm: GuideViewModel = viewModel()
        var isVisible by remember { mutableStateOf(false)}
        Box(Modifier.fillMaxSize()) {
            AndroidView(modifier = Modifier.fillMaxSize(), factory = { context ->
                ViewPager2(context).apply {
                    adapter = GuideAdapter(pics)
                    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                        override fun onPageSelected(position: Int) {
                            super.onPageSelected(position)
                            isVisible = position == pics.size - 1
                        }
                    })
                }
            })
            if (isVisible){
                Button(colors = ButtonDefaults.buttonColors(
                    backgroundColor = ColorPrimary
                ),onClick = { vm.jumpToMain() }, modifier = Modifier
                    .size(100.dp, 50.dp)
                    .align(
                        Alignment.BottomCenter)
                    .offset(y = (-50).dp)) {
                    Text(text = stringResource(id = com.jason.main.R.string.main_sure),style = whiteNormal14)
                }
            }
        }
    }

}


//@ExperimentalPagerApi
//@Composable
//private fun ContentView(){
//    val vm:GuideViewModel = viewModel()
//    val pagerState = rememberPagerState(pageCount = 4,initialOffscreenLimit = 4)
//    HorizontalPager(
//        state = pagerState,
//        modifier = Modifier.fillMaxSize()
//    ){
//        when(currentPage){
//            0->{
//                Image(painter = painterResource(id = R.drawable.img_guide_one), contentDescription = null,
//                    modifier = Modifier.fillMaxSize(),contentScale = ContentScale.FillBounds)
//            }
//            1->{
//                Image(painter = painterResource(id = R.drawable.img_guide_two), contentDescription = null,
//                    modifier = Modifier.fillMaxSize(),contentScale = ContentScale.FillBounds)
//            }
//            2->{
//                Image(painter = painterResource(id = R.drawable.img_guide_three), contentDescription = null,
//                    modifier = Modifier.fillMaxSize(),contentScale = ContentScale.FillBounds)
//            }
//            3->{
//                Box {
////                    Image(painter = painterResource(id = R.drawable.img_guide_four), contentDescription = null,
////                    modifier = Modifier.fillMaxSize(),contentScale = ContentScale.FillBounds)
//                    MyLog.e("Box")
//                    Text(text = stringResource(id = com.jason.main.R.string.main_sure),style = blackNormal14)
//                    Button(onClick = { vm.jumpToMain() }, modifier = Modifier
//                        .size(100.dp, 50.dp)
//                        .padding(bottom = 50.dp)
//                        .background(ColorPrimary)) {
//                        Text(text = stringResource(id = com.jason.main.R.string.main_sure),style = whiteNormal14)
//                    }
//                }
//            }
//        }
//    }
//}

