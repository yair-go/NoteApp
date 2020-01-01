package com.yair.noteapp.Entity;

import androidx.room.TypeConverter;

/**
 * Created by Yair on 01/01/2020.
 */
public class Converters {
    @TypeConverter
    public static Status getStatus(Integer numeral){
        for(Status ds : Status.values()){
            if(ds.ordinal() == numeral){
                return ds;
            }
        }
        return null;
    }

    @TypeConverter
    public static int integerToStatus(Status status) {
        return status.ordinal();
    }

}
