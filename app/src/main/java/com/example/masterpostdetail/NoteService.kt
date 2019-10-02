package com.example.masterpostdetail

import com.example.masterpostdetail.model.Comment
import com.example.masterpostdetail.model.Post
import retrofit2.Call
import retrofit2.http.*


interface NoteService {
    @GET("posts")
    fun getAllPosts(): Call<List<Post>>

    @GET("posts")
    fun getPostByQuery(
        @Query("userId") userId: Int,
        @Query("_sort") sort: String,
        @Query("_order") order: String
    ): Call<List<Post>>

    @GET("posts")
    fun getUsersPosts(
        @Query("userID") users: Array<Int>,
        @Query("_sort") sort: String,
        @Query("_order") order: String
    ): Call<List<Post>>

    @GET("posts")
    fun getUsersQueryMap(@QueryMap parameters: Map<String, String>): Call<List<Post>>

    @POST("posts")
    fun createPost(@Body newPost: Post): Call<Post>

    @FormUrlEncoded
    @POST("posts")
    fun createPost(
        @Field("userId") userId: Int,
        @Field("title") title: String,
        @Field("body") text: String
    ): Call<Post>

    @FormUrlEncoded
    @POST("posts")
    fun createPost(@FieldMap fields: Map<String, String>): Call<Post>

    @PUT("posts/{id}")
    fun updatePost(@Path("id") postId: String, @Body newPost: Post): Call<Post>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") postId: String): Call<Post>

    @GET("posts/{id}/comments")
    fun getAllComments(@Path("id") postId: String): Call<List<Comment>>
}