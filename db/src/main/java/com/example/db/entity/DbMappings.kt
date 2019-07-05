package com.example.db.entity

import com.example.data.entity.*

/* to mappings */
internal fun PostWithUser.toPost() = Post(dbPost.id, dbUser[0].toUser(), dbPost.title, dbPost.body)

internal fun DbUser.toUser() =
    User(dbAddress.toAddress(), dbCompany.toCompany(), id, email, name, phone, username, website)

internal fun DbAddress.toAddress() = Address(city, dbGeo.toGeo(), street, suite, zipcode)

internal fun DbGeo.toGeo() = Geo(lat, lng)

internal fun DbCompany.toCompany() = Company(bs, catchPhrase, name)
/* */


/* from mappings */
internal fun DbPost.Companion.fromPost(post: Post): DbPost = with(post) {
    DbPost(id, title, body, user.id)
}

internal fun DbUser.Companion.fromUser(user: User) = with(user) {
    DbUser(id, DbAddress.fromAddress(address), DbCompany.fromCompany(company), email, name, phone, username, website)
}

internal fun DbAddress.Companion.fromAddress(address: Address) = with(address) {
    DbAddress(city, DbGeo.fromGeo(geo), street, suite, zipcode)
}

internal fun DbGeo.Companion.fromGeo(geo: Geo) = with(geo) {
    DbGeo(lat, lng)
}

internal fun DbCompany.Companion.fromCompany(company: Company) = with(company) {
    DbCompany(bs, catchPhrase, name)
}

/* */