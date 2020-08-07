
/**
 * Represents a Person object. Each Person has a name, gender, and a professional domain. 
 * Class includes getter methods and a toString() method. 
 *
 * @author Leah Teffera & Alina Zheng 
 * @version 5/9/20
 */
public class Person
{
    // instance variables 
    private String name; 
    private String gender; 
    private String domain; 

    /**
     * Constructor for objects of class Person
     * @param nm the name of this Person
     * @param gen the gender of this Person
     * @param dmn the professional domain of this Person
     */
    public Person(String nm, String gen, String dmn)
    {
        name = nm; 
        gender = gen; 
        domain = dmn; 
    }

    /**
     * Gets name of this Person
     * @return name of Person
     */
    public String getName(){
        return this.name; 
    }

    /**
     * Gets gender of this Person
     * @return gender of person
     */
    public String getGender(){
        return this.gender; 
    }

    /**
     * Gets domain of this Person
     * @return domain of Person
     */
    public String getDomain(){ 
        return this.domain; 
    }

    /**
     * Formats information of this Person in a string
     * @return string with information of person
     */
    public String toString(){
        String result = ""; 
        result += this.name + ": " + this.gender + ", " + this.domain + "\n"; 
        return result; 
    }
}
