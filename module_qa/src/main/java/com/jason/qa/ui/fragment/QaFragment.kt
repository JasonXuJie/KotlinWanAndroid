package com.jason.qa.ui.fragment

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.alibaba.android.arouter.facade.annotation.Route
import com.jason.common.bean.WebContent
import com.jason.common.config.BundleKey
import com.jason.common.ui.theme.*
import com.jason.qa.R
import com.jason.qa.model.Data
import com.jason.qa.vm.QaViewModel
import com.jason.router.Navigator
import com.jason.router.Routes
import com.like.IconType
import com.like.LikeButton
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
@Route(path = Routes.QA)
class QaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                Column(Modifier.fillMaxSize()) {
                    TitleView()
                    ListView()
                }
            }
        }
    }

    @Composable
    private fun TitleView() {
        Row(Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(ColorPrimary),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            Text(text = stringResource(id = R.string.qa),style = whiteNormal14)
        }
    }

    @Composable
    private fun ListView(){
        val qaViewModel : QaViewModel = viewModel()
        val lazyPagingItems = qaViewModel.loadList().collectAsLazyPagingItems()
        LazyColumn(Modifier.fillMaxSize()){
//            item {
//                AndroidView(factory = {context ->  SmartRefreshLayout(context).apply {
//
//                }})
//            }
            items(lazyPagingItems){ itemData->
                ItemView(itemData!!)

            }
        }
    }


    @Composable
    private fun ItemView(itemData: Data){
        Column(Modifier
            .fillMaxWidth()
            .clickable {
                val bundle = bundleOf()
                bundle.putParcelable(BundleKey.WEB_CONTENT, WebContent(itemData.author,itemData.link))
                Navigator.pushByParams(Routes.WEB,bundle)
            }
            .padding(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (itemData.fresh){
                    Row {
                        Text(text = stringResource(id = R.string.text_new),style = mainNormal14)
                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }
                Text(text = itemData.author,style = blackNormal12)
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = itemData.tags[0].name,style = mainNormal12,
                    modifier = Modifier
                        .border(0.5.dp, ColorPrimary, RoundedCornerShape(3.dp))
                        .padding(horizontal = 4.dp, vertical = 2.dp))
                Spacer(modifier = Modifier.weight(1f))
                Text(text = itemData.niceDate,style = darkGreyNormal13)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = itemData.title,style = blackNormal16)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = fromHtml(itemData.desc, Html.FROM_HTML_MODE_LEGACY).toString(),style = darkGreyNormal14,maxLines = 3)
            Spacer(modifier = Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "${itemData.superChapterName}.${itemData.chapterName}",style = blackNormal14,modifier = Modifier.weight(1f))
                AndroidView(
                    factory = {
                            context ->  LikeButton(context).apply {
                            setIcon(IconType.Heart)
                            setIconSizeDp(20)
                    }},
                    modifier = Modifier
                        .size(20.dp)
                )
            }
        }
    }

    private fun fromHtml(str:String,flags:Int): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            Html.fromHtml(str, flags)
        } else {
            Html.fromHtml(str)
        }
    }

}