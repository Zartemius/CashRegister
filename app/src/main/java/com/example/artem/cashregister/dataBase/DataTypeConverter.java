package com.example.artem.cashregister.dataBase;

import android.arch.persistence.room.TypeConverter;

public class DataTypeConverter{

    @TypeConverter
    public static Double userDataconvert(String data){

        Double result = 0.0;
        return result;
    }

    @TypeConverter
    public static String backInDataConvert(Double result){
        String resultOfCalculation = "res";
        return resultOfCalculation;
    }
}
