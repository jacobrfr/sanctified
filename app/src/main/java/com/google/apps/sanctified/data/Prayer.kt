package com.google.apps.sanctified.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "prayers")
data class Prayer(
        @ColumnInfo(name = "prayer_subject") val subject: String,
        @ColumnInfo(name = "prayer_description") val description: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var prayerId: Int = 0
    @ColumnInfo(name = "prayer_date")
    var date: String = SimpleDateFormat(
            "M/dd/yyyy",
            Locale.getDefault()
    ).format(Date()).toString()

    override fun toString() = subject
}