package com.example.findroomies

data class RoomModel (
    val id:String,
    val title:String,
    val address:String,
    val rent: String,
    val imageAddress: String,
    val isUtilityIncluded:Boolean,
    val furnishingType: FurnishingType,
    val houseType: PropertyType
)

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


