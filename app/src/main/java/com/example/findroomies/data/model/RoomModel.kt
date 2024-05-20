package com.example.findroomies.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class RoomModel (
    val id:String?=null,
    val title:String?=null,
    val description:String?=null,
    val address:String?=null,
    val rent: String?=null,
    val addedBy: UserModel?=null,
    val timestamps: Timestamp?=null,
    val bookmarkedBy: List<String?>?=null,
    val imageUrl: List<String?>?=null,
    val isUtilityIncluded:Boolean?=null,
    val furnishingType: FurnishingType?=null,
    val houseType: PropertyType?=null
) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}


//data class UserModel(
//    val userId:  String?,
//    val name:  String?,
//    val email:  String?,
//    val imageUrl: String?
//    )

@IgnoreExtraProperties
data class UserModel(val userId: String? = null, val name: String? = null,val email: String? = null,val imageUrl: String? = null) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}

enum class FurnishingType {
                          UNKNOWN,
    FULLY_FURNISHED,
    SEMI_FURNISHED,
    UNFURNISHED
}

enum class PropertyType {
     UNKNOWN,
    HOUSE,
    APARTMENT,
    CONDO
}


@IgnoreExtraProperties
data class FirebaseUserModel(val userId: String? = null, val name: String? = null,val email: String? = null,val imageUrl: String? = null,val phone:String?=null,val address:String?=null, val bio:String?=null) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}

