package com.example.data.repository

sealed class Errors(message: String, cause: Throwable?) : RuntimeException(message, cause) {
    class NoSuchPostException(postId: Int, cause: Throwable) : Errors("No post with id=$postId found", cause)
    class NoSuchUserException(userId: Int, cause: Throwable) : Errors("No user with id=$userId found", cause)
    class NoUserDataException(postId: Int, val userId: Int) :
        Errors("No user data found for userId=$userId, postId=$postId", null)

    class ContentNotAvailable(cause: Throwable) : Errors("Content is not available", cause)
}
