package com.example.findroomies.data.model

import com.google.firebase.Timestamp
import com.google.firebase.firestore.IgnoreExtraProperties

data class RoomModel (
    val id:String,
    val title:String,
    val description:String,
    val address:String,
    val rent: String,
    val addedBy: UserModel?,
    val timestamps: Timestamp?,
    val bookmarkedBy: List<String?>?,
    val imageUrl: List<String?>?,
    val isUtilityIncluded:Boolean,
    val furnishingType: FurnishingType,
    val houseType: PropertyType
)

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


