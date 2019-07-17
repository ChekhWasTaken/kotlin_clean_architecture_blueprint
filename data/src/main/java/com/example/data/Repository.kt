package com.example.data

interface Repository<Data, Query : QueryObject> {
    suspend fun get(query: Query): Data

    suspend fun delete(item: Data)

    suspend fun getAll(): List<Data>

    suspend fun update(vararg items: Data)

    suspend fun add(vararg items: Data)
}


interface QueryObject