package fr.isen.vincentdubaret.androidcontactds

data class DataContactDetails (
    val name: DataContactNames,
    val location: DataLocation,
    val email: String,
    val dob: DataDob,
    val cell: String,
    val picture: DataPicture,
)