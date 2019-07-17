package com.example.data.entity

import com.example.data.QueryObject


data class User(
    val address: Address,
    val company: Company,
    val id: Int,
    val email: String,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)

data class Company(
    val bs: String,
    val catchPhrase: String,
    val name: String
)

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
)

data class Geo(
    val lat: String,
    val lng: String
)

data class UserQuery(val id: Int = -1, val email: String = "") : QueryObject