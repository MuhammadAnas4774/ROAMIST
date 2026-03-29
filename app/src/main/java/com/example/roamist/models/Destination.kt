package com.example.roamist.models

import android.os.Parcel
import android.os.Parcelable

/**
 * [F2] Model — implements Parcelable so it can be placed in a Bundle
 * and passed between Fragments without using global state.
 */
data class Destination(
    val id: Int,
    val name: String,
    val region: String,
    val description: String,
    val rating: Float,
    val pricePerNight: Int,   // PKR
    val category: String,     // e.g., Mountains, Coastal, Historical
    val iconEmoji: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        id            = parcel.readInt(),
        name          = parcel.readString() ?: "",
        region        = parcel.readString() ?: "",
        description   = parcel.readString() ?: "",
        rating        = parcel.readFloat(),
        pricePerNight = parcel.readInt(),
        category      = parcel.readString() ?: "",
        iconEmoji     = parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(region)
        parcel.writeString(description)
        parcel.writeFloat(rating)
        parcel.writeInt(pricePerNight)
        parcel.writeString(category)
        parcel.writeString(iconEmoji)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Destination> {
        override fun createFromParcel(parcel: Parcel): Destination = Destination(parcel)
        override fun newArray(size: Int): Array<Destination?> = arrayOfNulls(size)
    }
}
