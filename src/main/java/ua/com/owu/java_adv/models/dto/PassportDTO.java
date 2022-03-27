package ua.com.owu.java_adv.models.dto;

import lombok.Data;
import ua.com.owu.java_adv.models.entity.Passport;

@Data
public class PassportDTO {
    private String series;
    private int number;


    public PassportDTO (Passport passport){
        this.series = passport.getSeries();
        this.number = passport.getNumber() ;
    }

    public PassportDTO (){

    }


}
