package org.esca.app.cadastros.estudantes.config;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidatorUsingIDateFormat implements IDateValidator {
    private String dateFormat;

    public DateValidatorUsingIDateFormat(String dateFormat){
        this.dateFormat = dateFormat;
    }

    public boolean isValid(String dateStr){
        DateFormat sdf = new SimpleDateFormat(this.dateFormat);
        sdf.setLenient(false);
        try{
            sdf.parse(dateStr);
        }catch (ParseException e){
            return false;
        }
        return true;
    }
}
