package com.androidlearning.retrofittest.handle_complex_interface

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ExampleService {

    /**
     * 静态 URL 地址
     */
    @GET("get_data.json")
    fun getData(): Call<Data>

    /**
     * 动态 URL 地址
     * GET http://example.com/<page>/get_data.json
     */
    @GET("{page}/get_data.json")
    fun getData(@Path("page") page: Int): Call<Data>

    /**
     * GET http://example.com/get_data.json?u=<user>&t=<token>
     */
    @GET("get_data.json")
    fun getData(@Query("u") user: String, @Query("t") token: String): Call<Data>


    /**
     * DELETE 请求
     * DELETE http://example.com/data/<id>
     */
    @DELETE("data/{id}")
    fun delete(@Path("id") id: String): Call<ResponseBody>

    /**
     * POST 请求
     * POST http://example.com/data/create
     * {"id": 1, "content": "The description for this data."}
     */
    @POST("data/create")
    fun createData(@Body data: Data): Call<ResponseBody>


    /**
     *
     * 指定 Header 参数
     *
     * GET http://example.com/get_data.json
     * User-Agent: okhttp
     * Cache-Control: max-age=0
     *
     */
    // 静态
    @Headers("User-Agent : okhttp", "Cache-Control: max-age=0")
    @GET("get_data.json")
    fun getDataStatic(): Call<Data>


    // 动态
    @GET("get_data.json")
    fun getDataDynamic(
        @Header("User-Agent") userAgent: String,
        @Header("Cache-Control") cacheControl: String
    ): Call<Data>
}