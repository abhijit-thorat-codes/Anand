package com.example.dummy.anand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dummy on 12/05/2017.
 */

public class Validations {

   public boolean checkNumber(String string){
       if(string.matches(".*\\d.*")){
           return true;
       }
       else
        return false;
   }
    public boolean checkSpecialChars(String string){
        Pattern p=Pattern.compile("[^a-z0-9 ]",Pattern.CASE_INSENSITIVE);
        Matcher matcher=p.matcher((string));
        if(matcher.find())
            return true;
         else
            return false;
    }
    public boolean checkLetters(String string){
        if(string.matches("[a-zA-Z]")){
            return true;
        }
        else
            return false;
    }
    public boolean checkNumberSize(String string){
        if(string.length()==10){
            return true;
        }
        else
            return false;
    }
    public boolean checkPasswordSize(String string){
        if(string.length()>=5 && string.length()<=10 ){
            return true;
        }
        else
            return false;
    }


    public boolean checkBlanks(String name, String mail, String phone, String city, String uname, String pass, String pass2, String state) {
    if(name.equals("")||mail.equals("")||phone.equals("")||city.equals("")||uname.equals("")||pass.equals("")||pass2.equals("")|| state.equals("Select State"))
       return true;
    else
       return false;
    }
}
