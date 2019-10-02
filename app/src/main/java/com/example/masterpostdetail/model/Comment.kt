package com.example.masterpostdetail.model

import java.io.Serializable


data class Comment(
    var postId: String,
    var id: String,
    var email: String,
    var name: String,
    var body: String
) : Serializable