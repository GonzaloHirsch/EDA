package graphs.helper;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class User extends Person{

    private String gender;
    private Integer age;
    private String country;
    private GregorianCalendar registered;

    public User() {
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        System.out.println("gender " + gender);
        this.gender = gender == null || gender.equals(" ") ? "NA" : gender;
    }
/*
    public Integer getAge() {
        return age;
    }
*/
    public void setAge(String age) {
        System.out.println("age " + age);
        try{
            this.age = Integer.valueOf(age);
        } catch (Exception ignored){
            this.age = -1;
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        System.out.println("country " + country);
        this.country = country == null || country.equals(" ") ? "NA" : country;
    }

    /*
    public GregorianCalendar getRegistered() {
        return registered;
    }*/

    public void setRegistered(String registered) {
        System.out.println("registered " + registered);
        SimpleDateFormat fmt = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
        try {
            this.registered.setTime(fmt.parse(registered));
        } catch (Exception ignored){
            this.registered = new GregorianCalendar();
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "gender='" + gender + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", registered=" + registered +
                ", id='" + id + '\'' +
                '}';
    }
}
