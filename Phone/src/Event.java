import java.util.Calendar;
import java.util.Scanner;

public class Event {
    Scanner scanner;
    Calendar eventDate;
    String description;
    Contact eventContact;




    //   meeting constructor
    public Event(Contact contact) {
        this.scanner = new Scanner(System.in);
        this.eventDate = Calendar.getInstance();
        this.eventContact = contact;
        this.createDate();
    }

    //  OCCASION constructor
    public Event(String description) {
        this.scanner = new Scanner(System.in);
        this.eventDate = Calendar.getInstance();
        this.description = description;
        this.createDate();
    }

    public void createDate() {
        int input;
        this.eventDate.set(Calendar.AM_PM, 0);
        System.out.println("Enter the year of the event : ");
        input = this.scanner.nextInt();
        this.eventDate.set(Calendar.YEAR, input);
        System.out.println("Enter the month of the event : ");
        input = this.scanner.nextInt();
        input = input - 1;
        this.eventDate.set(Calendar.MONTH, input);
        System.out.println("Enter the day of the event : ");
        input = this.scanner.nextInt();
        this.eventDate.set(Calendar.DAY_OF_MONTH, input);
        System.out.println("Enter the start hour of the event: ");
        input = this.scanner.nextInt();
        this.eventDate.set(Calendar.HOUR, input);
        System.out.println("Enter the start minute of the event : ");
        input = this.scanner.nextInt();
        this.eventDate.set(Calendar.MINUTE, input);
//        System.out.println("Enter the end hour of the event : ");
//        calender.set(Calendar.HOUR, input);
//        System.out.println("Enter the end minute of the event : ");
//        calender.set(Calendar.MINUTE, input);


        System.out.println(this.eventDate.getTime());




    }
}
