package com.example.masterpostdetail.model

import java.io.Serializable


class Post : Serializable {
    var userId: Int? = null
    var id: Int? = null
    var title: String? = null
    var body: String? = null

    constructor(title: String, body: String, userId: Int) {
        this.title = title
        this.body = body
        this.userId = userId
    }


    override fun toString(): String {
        return title + "\n\n" + body
    }
}