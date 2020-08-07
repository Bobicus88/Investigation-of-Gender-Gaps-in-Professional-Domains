/**
 * Creates Person objects from a CSV file, compiles those Person objects into a LinkedList. 
 * Also sub-divides Person objects into separate LinkedLists based on professional domain. 
 * Includes method to calculate the percentage of men and women in a specified domain. 
 *
 * @author Leah Teffera & Alina Zheng 
 * @version 5/9/20
 */
import java.util.LinkedList;
import java.util.Scanner; 
import java.io.IOException; 
import java.net.URL; 
import java.io.File; 
import java.io.FileNotFoundException; 
public class InvestigateGender
{
    // instance variables
    private LinkedList<Person> people; 
    private LinkedList<Person> sciTechPeople;
    private LinkedList<Person> humanitiesPeople;
    private LinkedList<Person> BLawPeople;

    /**
     * Constructor for objects of class InvestigateGender
     * @param fileName the CSV file to read from to get the LinkedList of Person objects
     */
    public InvestigateGender(String fileName)
    {
        // initialise instance variables
        this.people = readFile(fileName);
        this.sciTechPeople = getPeopleFromDomain("SCIENCE & TECHNOLOGY"); 
        this.humanitiesPeople = getPeopleFromDomain("HUMANITIES"); 
        this.BLawPeople = getPeopleFromDomain("BUSINESS & LAW"); 
    }

    /**
     * Reads a line of the CSV file and creates a Person
     * @param data a line of the CSV file
     * @return a Person
     */
    public Person readLine(String data)
    {
        String[] line = data.split(",");
        String name = ""; 
        String gender = ""; 
        String domain = ""; 
        name = line[1];
        gender = line[5]; 
        domain = line[8]; 
        Person person = new Person(name, gender, domain); 
        return person; 
    }

    /**
     * Reads from a file and creates a LinkedList of Person objects
     * @param fileName the file to read from 
     * @return LinkedList<Person>
     */
    public LinkedList<Person> readFile(String fileName){
        LinkedList<Person> peopleFromFile = new LinkedList<Person>(); 
        try{
            File file = new File(fileName); 
            Scanner scan = new Scanner(file);
            scan.nextLine(); 
            while(scan.hasNext()){
                String temp = scan.nextLine(); 
                Person tempPerson = readLine(temp); 
                peopleFromFile.add(tempPerson); 
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e); 
            System.out.println("The program terminates."); 
            System.exit(0);    
        }
        return peopleFromFile; 
    }

    /**
     * Creates a list of people for a specified domain.
     * We're assuming that the user (us) enters in a domainName that actually exists.
     * @param domainName the specified domain
     * @return a Linked List of People 
     */
    public LinkedList<Person> getPeopleFromDomain(String domainName){
        LinkedList<Person> domainList = new LinkedList<Person>(); 
        for (int i = 0; i<people.size(); i++){
            if(people.get(i).getDomain().equals(domainName)){
                domainList.add(people.get(i)); 
            }
        }
        return domainList; 
    }

    /**
     * Creates a list of the domains
     * @return a list of all the domains 
     */
    public LinkedList<String> getDomains(){
        LinkedList<String> domains = new LinkedList<String>(); 
        for (int i = 0; i<people.size(); i++){
            if ((!domains.contains(people.get(i).getDomain()))){
                domains.add(people.get(i).getDomain()); 
            }
        }
        return domains; 
    }

    /**
     * Calculates the percentages of men and women in a LinkedList of Person objects of a particular domain
     * @param domainName the domain corresponding to the LinkedList of people we want to examine
     * @return a LinkedList of two floats (percentages of men, women in a specified domain) 
     */
    public LinkedList<Float> getPercents(String domainName){
        LinkedList<Float> percents = new LinkedList<Float>(); 
        LinkedList<Person> list; 
        System.out.println("Percent of male and female people in " + domainName);
        if (domainName.equals("SCIENCE & TECHNOLOGY")) {
            list = this.sciTechPeople; 
        } else if (domainName.equals("HUMANITIES")) {
            list = this.humanitiesPeople; 
        } else if(domainName.equals("BUSINESS & LAW")){
            list = this.BLawPeople;
        } else {
            list = this.people; 
        }
        float men = 0; 
        float women = 0; 
        float total_people = list.size(); 
        for (int i = 0; i < total_people; i++) { 
            if (list.get(i).getGender().equals("Male")) {
                men++; 
            }
            else {
                women++; 
            }
        }
        float male_percent = (men / total_people)*100; 
        float fem_percent = (women / total_people)*100; 
        percents.add(male_percent); 
        percents.add(fem_percent);
        return percents; 
    }

    /**
     * Main method for running our investigation of gender
     */
    public static void main (String[] args){
        InvestigateGender test1 = new InvestigateGender("pantheon_nodes_1000.csv"); 
        //System.out.println(test1.people.toString()); 
        // System.out.println(test1.sciTechPeople.toString()); 
        //LinkedList<String> domains1 = test1.getDomains(); 
        //System.out.println(domains1.toString()); 
        System.out.println(test1.getPercents("ALL").toString()); 
        LinkedList<Float> sciTechPercent = test1.getPercents("SCIENCE & TECHNOLOGY"); 
        System.out.println(sciTechPercent.toString());
        LinkedList<Float> humanitiesPercent = test1.getPercents("HUMANITIES"); 
        System.out.println(humanitiesPercent.toString());
        LinkedList<Float> bLawPercent = test1.getPercents("BUSINESS & LAW"); 
        System.out.println(bLawPercent.toString());
    }
}
