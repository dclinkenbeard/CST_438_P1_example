package com.daclink.drew.sp22.plainolnotes.database;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.Date;


public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp){
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimeStamp(Date date){
        return date == null ? null : date.getTime();
    }
}
