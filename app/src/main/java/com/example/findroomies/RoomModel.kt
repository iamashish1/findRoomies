package com.example.findroomies

data class RoomModel (
    val title:String,
    val address:String,
    val rent: String,
    val isUtilityIncluded:Boolean,
    val furnishingType: FurnishingType,
    val houseType: PropertyType
)

enum class FurnishingType {
    FULLY_FURNISHED,
    SEMI_FURNISHED,
    UNFURNISHED
}

enum class PropertyType {
    HOUSE,
    APARTMENT,
    CONDO
}


