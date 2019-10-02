package com.example.masterpostdetail.model

import androidx.annotation.NonNull



class Comment {
    var postId: String? = null
    var id: String? = null
    var email: String? = null
    var name: String? = null
    var body: String? = null

    constructor(postId: String, id: String, email: String, name: String, body: String) {
        this.postId = postId
        this.email = email
        this.name = name
        this.body = body
    }
    override fun toString(): String {
        return email + "\n\n" + body
    }
}