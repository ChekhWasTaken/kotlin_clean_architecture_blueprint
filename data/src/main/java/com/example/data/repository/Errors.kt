package com.example.data.repository

sealed class Errors(string: String) : RuntimeException(string) {
    class NoSuchPostException(postId: Int) : Errors("No post with id=$postId found")
    class NoSuchUserException(userId: Int) : Errors("No user with id=$userId found")
    class NoUserDataException(postId: Int, val userId: Int) :
        Errors("No user data found for userId=$userId, postId=$postId")

    object ContentNotAvailable : Errors("Content is not available")
}
