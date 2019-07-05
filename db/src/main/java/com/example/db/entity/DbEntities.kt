package com.example.db.entity

import androidx.room.*

@Entity(
    foreignKeys = [ForeignKey(entity = DbUser::class, parentColumns = ["id"], childColumns = ["userId"])],
    indices = [Index("userId")]
)
internal data class DbPost(@PrimaryKey val id: Int, val title: String, val body: String, val userId: Int) {
    companion object
}

@Entity
internal data class DbUser(
    @PrimaryKey val id: Int,
    @Embedded(prefix = "dbAddress") val dbAddress: DbAddress,
    @Embedded(prefix = "dbCompany") val dbCompany: DbCompany,
    val email: String,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
) {
    companion object
}

internal data class DbCompany(
    val bs: String,
    val catchPhrase: String,
    val name: String
) {
    companion object
}

internal data class DbAddress(
    val city: String,
    @Embedded(prefix = "dbGeo") val dbGeo: DbGeo,
    val street: String,
    val suite: String,
    val zipcode: String
) {
    companion object
}

internal data class DbGeo(
    val lat: String,
    val lng: String
) {
    companion object
}

internal data class PostWithUser(
    @Embedded val dbPost: DbPost,
    @Relation(entity = DbUser::class, parentColumn = "userId", entityColumn = "id")
    val dbUser: List<DbUser>
)

internal data class UserWithPosts(
    @Embedded val dbUser: DbUser,

    @Relation(entity = DbPost::class, parentColumn = "id", entityColumn = "userId")
    val dbUserPosts: List<DbPost>
)