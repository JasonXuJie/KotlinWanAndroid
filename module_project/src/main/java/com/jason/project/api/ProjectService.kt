package com.jason.project.api

import com.jason.net.bean.BaseResult
import com.jason.project.model.ProjectData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProjectService {

    @GET("project/tree/json")
    suspend fun loadTab():BaseResult<List<ProjectTab>>

    @GET("project/list/{page}/json")
    suspend fun loadProjectList(@Path("page") page: Int,@Query("cid") cid: Int):BaseResult<ProjectData>
}