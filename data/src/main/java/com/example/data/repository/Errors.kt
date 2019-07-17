package com.example.data.repository

import com.example.data.QueryObject

sealed class Errors(message: String, cause: Throwable?) : RuntimeException(message, cause) {
    class ContentNotAvailable(cause: Throwable) : Errors("Content is not available", cause)

    class NoMatchForQuery(query: QueryObject, cause: Throwable) : Errors("No match found for query $query", cause)

    class EmptyDataForQuery(query: QueryObject, cause: Throwable) : Errors("Content is empty for query $query", cause)
}
