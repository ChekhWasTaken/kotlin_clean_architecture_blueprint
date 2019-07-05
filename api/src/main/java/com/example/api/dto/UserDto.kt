package com.example.api.dto

import com.google.gson.annotations.SerializedName

internal data class UserDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("address")
    val address: AddressDto? = null,
    @SerializedName("company")
    val company: CompanyDto? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("phone")
    val phone: String? = null,
    @SerializedName("username")
    val username: String? = null,
    @SerializedName("website")
    val website: String? = null
) {
    companion object {
        fun createFromId(id: Int) = UserDto(id)
    }
}

internal data class CompanyDto(
    @SerializedName("bs")
    val bs: String? = null,
    @SerializedName("catchPhrase")
    val catchPhrase: String? = null,
    @SerializedName("name")
    val name: String? = null
)

internal data class AddressDto(
    @SerializedName("city")
    val city: String? = null,
    @SerializedName("geo")
    val geo: GeoDto? = null,
    @SerializedName("street")
    val street: String? = null,
    @SerializedName("suite")
    val suite: String? = null,
    @SerializedName("zipcode")
    val zipcode: String? = null
)

internal data class GeoDto(
    @SerializedName("lat")
    val lat: String? = null,
    @SerializedName("lng")
    val lng: String? = null
)