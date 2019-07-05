package com.example.api.dto

import com.example.data.entity.*

internal fun UserDto.toUser() = User(
    address.toAddress(),
    company.toCompany(),
    id ?: -1,
    email ?: "",
    name ?: "",
    phone ?: "",
    username ?: "",
    website ?: ""
)

internal fun PostDto.toPost() = Post(id ?: -1, UserDto.createFromId(userId ?: -1).toUser(), title ?: "", body ?: "")

internal fun PostDto.toPost(userDto: UserDto) = Post(id ?: -1, userDto.toUser(), title ?: "", body ?: "")

private fun AddressDto?.toAddress(): Address = this?.let {
    Address(city ?: "", geo.toGeo(), street ?: "", suite ?: "", zipcode ?: "")
}
    ?: Address("", Geo("", ""), "", "", "")

private fun GeoDto?.toGeo(): Geo = this?.let { Geo(lat ?: "", lng ?: "") } ?: Geo("", "")

private fun CompanyDto?.toCompany(): Company =
    this?.let { Company(bs ?: "", catchPhrase ?: "", name ?: "") } ?: Company("", "", "")
