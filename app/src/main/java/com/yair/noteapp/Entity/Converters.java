package com.yair.noteapp.Entity;

import android.location.Location;

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
    public static int StatusToInetger(Status status) {
        return status.ordinal();
    }

    @TypeConverter
    public static Location stringToLocation(String fromRoom) {
        if (fromRoom != "") {
            String[] latlong = fromRoom.split(",");
            double latitude = Double.parseDouble(latlong[0]);
            double longitude = Double.parseDouble(latlong[1]);
            Location location = new Location("");
            location.setLatitude(latitude);
            location.setLongitude(longitude);
            return location;
        }
        return null;
    }


    @TypeConverter
    public static String locationToString(Location location) {
        if (location!= null){
            return Location.convert(location.getLatitude(), Location.FORMAT_DEGREES) + "," + Location.convert(location.getLongitude(), Location.FORMAT_DEGREES);
        }
        return "";
    }


}
