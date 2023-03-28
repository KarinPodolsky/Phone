import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Scanner;

public class CalenderApp {
    Phonebook phonebook;
    CalenderManager cm;
    Scanner scanner;


    public CalenderApp() throws FileNotFoundException {
        this.scanner = new Scanner(System.in);
        this.phonebook = new Phonebook();
        this.phonebook.ImportContacts();
        this.cm = new CalenderManager();
    }
    public void printMenu() {
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("*********** Calender ************");
        System.out.println("1.Add event");
        System.out.println("2.Delete event");
        System.out.println("3.view events by date");
        System.out.println("4.view events with contacts");
        System.out.println("5.Delete overlapping events");
        System.out.println("6.view events by day");
        System.out.println("7.Exit");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Enter a number from the menu: ");
    }
    public void CalenderMenu() throws ParseException, IOException {
        int numFromList = 0;

        while (numFromList == 0) {
            printMenu();
            try {
                numFromList = Integer.parseInt(this.scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
            switch (numFromList) {
                case 1:
                    this.addAppointment();
                    break;
                case 2:
                    this.deleteAppointment();
                    break;
                case 3:
                    this.printAppointmentsByDate();
                    break;
                case 4:
                    this.printMeetingsWithContact();
                    break;
                case 5:
                    this.removeCollisions();
                    break;
                case 6:
                    this.AppointmentsInDay();
                    break;
                case 7:
                    return;
            }
            numFromList = 0;
        }
    }

    /**
     * creates and adds a new appointment to callendar manager arrayList<>
     * **/
    public void addAppointment(){
        int input;
        Calendar calendarInstance = Calendar.getInstance();
        System.out.println("When will you like the meeting to take place?");
        System.out.println("enter a day(number)");
        input = this.scanner.nextInt();
        calendarInstance.set(Calendar.DATE, input);

        System.out.println("enter a month(number)");
        input = this.scanner.nextInt();
        input = input - 1;
        calendarInstance.set(Calendar.MONTH, input);

        System.out.println("enter a year");
        input = this.scanner.nextInt();
        calendarInstance.set(Calendar.YEAR, input);

        System.out.println("enter the hour");
        input = this.scanner.nextInt();
        calendarInstance.set(Calendar.HOUR, input);

        System.out.println("enter the minutes");
        input = this.scanner.nextInt();
        calendarInstance.set(Calendar.MINUTE, input);
        calendarInstance.set(Calendar.SECOND, 0);
        calendarInstance.set(Calendar.MILLISECOND, 0);


        System.out.println("enter a length for the appointment(in minutes):");
        int meetingLength = this.scanner.nextInt();

        this.scanner = new Scanner(System.in);
        System.out.println("would you like to create a meeting or an event?: ");
        String typeOfAppointment = this.scanner.nextLine();
        boolean meetingTypeSet = false;
        while (!meetingTypeSet){
            if (typeOfAppointment.equalsIgnoreCase("meeting")){
                System.out.println("List of Contacts:");
                this.phonebook.PrintContacts();
                Contact selectedContact = this.phonebook.SearchContact();
                meetingTypeSet = true;
                MeetingDetails newAppointmentDetails = new MeetingDetails(calendarInstance, meetingLength);
                Meeting newMeeting = new Meeting(newAppointmentDetails, selectedContact);
                this.cm.addAppointment(newMeeting);
            }
            else if(typeOfAppointment.equalsIgnoreCase("event")){
                System.out.println("enter a description for the event: ");
                String eventDescription = this.scanner.nextLine();
                meetingTypeSet = true;
                MeetingDetails newAppointmentDetails = new MeetingDetails(calendarInstance, meetingLength);
                Event newEvent = new Event(newAppointmentDetails, eventDescription);
                this.cm.addAppointment(newEvent);
            }
            else {
                System.out.println("try again");
            }
        }
    }

    /**
     * deletes an appointment from callendar manager arrayList<>
     * **/
    public void deleteAppointment(){
        this.cm.removeAppointment();
    }

    /**
     * prints appointments in a specified DATE sorted them by TIME
     * **/
    public void printAppointmentsByDate(){
        this.scanner = new Scanner(System.in);
        System.out.println("Enter the 'DAY(number)' of the appointment you would like to find: ");
        int selectedDay = this.scanner.nextInt();
        System.out.println("Enter the 'MONTH(number)' of the appointment you would like to find: ");
        int selectedMonth = this.scanner.nextInt();
        for (Appointment a : this.cm.cal){
            // TODO: FIX THIS~!~!~! - cant access the variables of Calendar instance
            if (
                a.meetingDetails.calendar.get(Calendar.DATE) == selectedDay &&
                a.meetingDetails.calendar.get(Calendar.DATE) == selectedMonth){
                System.out.println("Meeting Time and Date: " + a.meetingDetails.calendar.getTime());
                System.out.println("Meeting Length in minutes: " + a.meetingDetails.meetingLength);
            }
        }
    }

    /**
     * prints all MEETINGS with a specified Contact sorted by DATE
     * **/
    public void printMeetingsWithContact(){
        // TODO: add sorting to the method(meetings sorted by date)
        this.scanner = new Scanner(System.in);
        this.phonebook.PrintContacts();
        System.out.println("enter first name fo the contact you would like to find");
        String selectedFirstName = this.scanner.nextLine();
        System.out.println("enter last name fo the contact you would like to find");
        String selectedLastName = this.scanner.nextLine();
        for (Contact c : this.phonebook.contactList){
            for (Appointment a : this.cm.cal){
                if(a instanceof Meeting && c.firstName.equals(selectedFirstName) && c.lastName.equals(selectedLastName)){
                    System.out.println("meetings with " + ((Meeting) a).eventContact.firstName + ((Meeting) a).eventContact.lastName + ": ");
                    System.out.println("datetime of meeting: " + a.meetingDetails.calendar.getTime() + "length of meeting: " + a.meetingDetails.meetingLength);
                }
            }
        }
    }

    /**
     * removes appointment collision - by removing the later appointment
     * **/
    public void removeCollisions(){
        //  TODO: cant access the variables of Calendar instance
        System.out.println("removeCollisions");
    }

    /**
     * prints all Appointments in a specified DAY OF THE WEEK
     * **/
    public void AppointmentsInDay(){
        //  TODO: cant access the variables of Calendar instance
        System.out.println("AppointmentsInDay");
    }



    public static void main(String[] args) {
        CalenderManager calMan = new CalenderManager();

        System.out.println("Hi please insert when will you like the meeting to take place, !");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 11);
        cal.set(Calendar.MONTH, 11-1);
        cal.set(Calendar.YEAR, 1990);
        //MeetingDetails md = new MeetingDetails(cal, 120);
        //Event event = new Event(md,"a stupid meeting that wastes my fucking time");
        //calMan.addAppointment(event);
        System.out.println(cal.get(cal.MONTH));
    }

//    public void meetingDetails() {
//        CalenderManager calMan = new CalenderManager();
//        int input;
//        System.out.println("When will you like the meeting to take place? " );
//        Calendar cal = Calendar.getInstance();
//        System.out.println("Enter the year of the event : ");
//        input = this.scanner.nextInt();
//        cal.set(Calendar.YEAR, input);
//        System.out.println("Enter the month of the event : ");
//        input = this.scanner.nextInt();
//        input = input - 1;
//        cal.set(Calendar.MONTH, input);
//        System.out.println("Enter the day of the event : ");
//        input = this.scanner.nextInt();
//        cal.set(Calendar.DATE, input);
//
//
//        MeetingDetails md = new MeetingDetails(cal, 120);
//        System.out.println("Description : ");
//        String Description = this.scanner.nextLine();
//        Event event = new Event(md,Description);
//        calMan.addAppointment(event);
//        System.out.println(cal.getTime());
//    }
}
