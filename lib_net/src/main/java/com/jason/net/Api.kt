package com.jason.net

object Api {

     const val BASE_URL = "https://www.wanandroid.com/"

     //项目分类
     const val PROJECT_CLASSIFY = "project/tree/json"

     //项目列表数据
     fun projectList(page:String,cid:String):String = "project/list/$page/json?cid=$cid"

     //问答
     fun qaList(page:String) = "https://wanandroid.com/wenda/list/$page/json"

     //首页banner
     const val banner = "https://www.wanandroid.com/banner/json"

     // 首页文章列表
     fun homeList(page:String) = "https://www.wanandroid.com/article/list/$page/json"

     //首页置顶文章
     const val homeTopList = "https://www.wanandroid.com/article/top/json"

     //常用网站
     const val  usedWeb = "https://www.wanandroid.com/friend/json"

     //搜索热词
     const val hotKey = "https://www.wanandroid.com//hotkey/json"

     //体系数据
     const val systemData  = "https://www.wanandroid.com/tree/json"

     //体系文章
     fun  systemArticle(page:Int, cid:String):String = "https://www.wanandroid.com/article/list/$page/json?cid=$cid"

     //按照作者昵称搜索文章
     fun  systemAuthorSearch(page:Int,author:String) = "https://wanandroid.com/article/list/$page/json?author=$author"

     //导航数据
     const val navData = "https://www.wanandroid.com/navi/json"

     //登陆
     //参数：
     // 	username，password
     const val login = "https://www.wanandroid.com/user/login"

     //注册
     //参数
     // 	username,password,repassword
     const val register = "https://www.wanandroid.com/user/register"

     //登出
     const val logout = "https://www.wanandroid.com/user/logout/json"

     //搜索
     fun search(page:Int):String  = "https://www.wanandroid.com/article/query/$page/json"

    ///收藏相关
    ///收藏文章列表

     //广场
     //广场列表数据
     fun squareDataList(page:Int) = "https://wanandroid.com/user_article/list/$page/json"

     // 分享人对应列表数据
     fun shareArticlesList(id:String,page:Int) = "https://www.wanandroid.com/user/$id/share_articles/$page/json"

     //自己的分享的文章列表
     fun privateArticlesList(page:Int) = "https://wanandroid.com/user/lg/private_articles/$page/json"

     //删除自己分享的文章
     fun deletePrivate(id:String) = "https://wanandroid.com/lg/user_article/delete/$id/json"

     //分享文章
     const val shareAtricles = "https://www.wanandroid.com/lg/user_article/add/json"

}