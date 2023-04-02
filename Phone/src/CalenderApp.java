import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class CalenderApp {
    Phonebook phonebook;
    CalenderManager calenderManager;
    Scanner scanner;


    public CalenderApp() throws FileNotFoundException {
        this.scanner = new Scanner(System.in);
        this.phonebook = new Phonebook();
        this.phonebook.ImportContacts();
        this.calenderManager = new CalenderManager();
    }

    public void debugThisShit(int a, int b, int c, int d, int e) {

        Calendar calendarInstance = Calendar.getInstance();
        calendarInstance.set(Calendar.DATE, a - 1);
        calendarInstance.set(Calendar.MONTH, b);
        calendarInstance.set(Calendar.YEAR, c);
        calendarInstance.set(Calendar.HOUR, d);
        calendarInstance.set(Calendar.MINUTE, e);
        calendarInstance.set(Calendar.SECOND, 0);
        calendarInstance.set(Calendar.MILLISECOND, 0);
        MeetingDetails newAppointmentDetails = new MeetingDetails(calendarInstance, 10);
        Meeting newMeeting = new Meeting(newAppointmentDetails, this.phonebook.contactList.get(0));
        this.calenderManager.addAppointment(newMeeting);
    }

    /**
     * Method is used to print the menu
     **/
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
                //numFromList = Integer.parseInt(this.scanner.nextLine());
                numFromList = this.scanner.nextInt();
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
     * Method is used to create and add new appointment to calendar manager arrayList<>
     **/
    public void addAppointment() {
        int input;
        Calendar calendarInstance = Calendar.getInstance();
        System.out.println("When will you like the meeting to take place?");
        System.out.println("enter a day(number)");
        input = this.scanner.nextInt();
        calendarInstance.set(Calendar.DATE, input);

        System.out.println("enter a month(number)");
        input = this.scanner.nextInt();
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
        while (!meetingTypeSet) {
            if (typeOfAppointment.equalsIgnoreCase("meeting")) {
                System.out.println("List of Contacts:");
                this.phonebook.PrintContacts();
                Contact selectedContact = this.phonebook.SearchContact();
                meetingTypeSet = true;
                MeetingDetails newAppointmentDetails = new MeetingDetails(calendarInstance, meetingLength);
                Meeting newMeeting = new Meeting(newAppointmentDetails, selectedContact);
                this.calenderManager.addAppointment(newMeeting);
            } else if (typeOfAppointment.equalsIgnoreCase("event")) {
                System.out.println("enter a description for the event: ");
                String eventDescription = this.scanner.nextLine();
                meetingTypeSet = true;
                MeetingDetails newAppointmentDetails = new MeetingDetails(calendarInstance, meetingLength);
                Event newEvent = new Event(newAppointmentDetails, eventDescription);
                this.calenderManager.addAppointment(newEvent);
            } else {
                System.out.println("try again");
            }
        }
    }

    /**
     * Method is used to delete an appointment from calendar manager arrayList<>
     **/
    public void deleteAppointment() {
        this.calenderManager.removeAppointment();
    }

    /**
     * Method is used to print appointments in a specified DATE sorted them by date
     **/
    public void printAppointmentsByDate() {
        this.scanner = new Scanner(System.in);
        System.out.println("Enter the 'DAY(number)' of the appointment you would like to find: ");
        int selectedDay = this.scanner.nextInt();
        System.out.println("Enter the 'MONTH(number)' of the appointment you would like to find: ");
        int selectedMonth = this.scanner.nextInt();
        for (Appointment a : this.calenderManager.appointmentArrayList) {
//            System.out.println(a.meetingDetails.calendar.get(Calendar.DATE));
//            System.out.println(a.meetingDetails.calendar.get(Calendar.MONTH));
//            System.out.println(a.meetingDetails.calendar.get(Calendar.YEAR));
            if (
                    a.meetingDetails.calendar.get(Calendar.DATE) == selectedDay &&
                            a.meetingDetails.calendar.get(Calendar.MONTH) == selectedMonth) {
                System.out.println("Meeting Time and Date: " + a.meetingDetails.calendar.getTime());
                System.out.println("Meeting Length in minutes: " + a.meetingDetails.meetingLength);
            }
        }
    }

    /**
     * Method is used to prints all MEETINGS with a specified Contact
     **/
    public void printMeetingsWithContact() {
        this.scanner = new Scanner(System.in);
        this.phonebook.PrintContacts();
        System.out.println("enter first name fo the contact you would like to find");
        String selectedFirstName = this.scanner.nextLine();
        System.out.println("enter last name fo the contact you would like to find");
        String selectedLastName = this.scanner.nextLine();
        for (Contact c : this.phonebook.contactList) {
            for (Appointment a : this.calenderManager.appointmentArrayList) {
                if (a instanceof Meeting && c.firstName.equals(selectedFirstName) && c.lastName.equals(selectedLastName)) {
                    System.out.println("meetings with " + ((Meeting) a).eventContact.firstName + ((Meeting) a).eventContact.lastName + ": ");
                    System.out.println("datetime of meeting: " + a.meetingDetails.calendar.getTime() + "length of meeting: " + a.meetingDetails.meetingLength);
                }
            }
        }
    }

    /**
     * Method is used to remove appointment collision - by removing the later appointment
     **/
    public void removeCollisions() {
//        debugThisShit(10+1, 2-1, 1994, 10, 10);
//        debugThisShit(10+1, 2-1, 1994 ,10 ,11);
//        debugThisShit(10+1, 2-1, 1994, 10, 10);
//        debugThisShit(13+1, 3-1, 1993 ,12 ,30);
//        debugThisShit(14+1, 4-1, 1995 ,12 ,30);
//        debugThisShit(14+1, 4-1, 1995 ,12 ,30);
        System.out.println("before Removing");
        printArraylist();
        ArrayList<Integer> collidingIndexes = new ArrayList<Integer>();
        for (int i = 0; i < this.calenderManager.appointmentArrayList.size(); i++) {
            //System.out.println(this.calenderManager.appointmentArrayList.get(i).meetingDetails.calendar.getTime());
            for (int j = 1; j < this.calenderManager.appointmentArrayList.size(); j++) {
                if (i != j && i < j) {
                    if (
                            this.calenderManager.appointmentArrayList.get(i).meetingDetails.calendar.get(Calendar.DATE) == this.calenderManager.appointmentArrayList.get(j).meetingDetails.calendar.get(Calendar.DATE) &&
                                    this.calenderManager.appointmentArrayList.get(i).meetingDetails.calendar.get(Calendar.MONTH) == this.calenderManager.appointmentArrayList.get(j).meetingDetails.calendar.get(Calendar.MONTH) &&
                                    this.calenderManager.appointmentArrayList.get(i).meetingDetails.calendar.get(Calendar.YEAR) == this.calenderManager.appointmentArrayList.get(j).meetingDetails.calendar.get(Calendar.YEAR)
                    ) {
                        Calendar firstAppointment_START = this.calenderManager.appointmentArrayList.get(i).meetingDetails.calendar;
                        int firstMeetingLength = this.calenderManager.appointmentArrayList.get(i).meetingDetails.meetingLength;
                        Calendar firstAppointment_END = (Calendar) firstAppointment_START.clone();
                        Calendar secondAppointment = this.calenderManager.appointmentArrayList.get(j).meetingDetails.calendar;
                        boolean result = isBetween(firstAppointment_START, firstAppointment_END, secondAppointment, firstMeetingLength);
                        if (result) {
                            collidingIndexes.add(j);
                        }
                    }
                }
            }
        }
        for (int i : collidingIndexes) {
            this.calenderManager.appointmentArrayList.remove(i);
        }
        System.out.println("after Removing");
        printArraylist();
    }

    /**
     * supporting Method - is the 3rd date passed to the method before the first and after the second
     */
    public static boolean isBetween(Calendar cal1, Calendar cal2, Calendar cal3, int meetingLength) {
        cal2.add(Calendar.MINUTE, meetingLength);
        if (cal3.after(cal1) && cal3.before(cal2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * supporting Method - prints the content of the appointment array list
     */
    public void printArraylist() {
        for (Appointment a : this.calenderManager.appointmentArrayList) {
            System.out.println(a.meetingDetails.calendar.getTime() + " has a length of :" + a.meetingDetails.meetingLength);
        }
    }

    /**
     * Method is used print all Appointments in a specified DAY OF THE WEEK
     **/
    public void AppointmentsInDay() {
//        debugThisShit(10+1, 2-1, 1994, 10, 10);
//        debugThisShit(10+1, 2-1, 1994 ,10 ,11);
//        debugThisShit(10+1, 2-1, 1994, 10, 10);
//        debugThisShit(13+1, 3-1, 1993 ,12 ,30);
//        debugThisShit(14+1, 4-1, 1995 ,12 ,30);
//        debugThisShit(14+1, 4-1, 1995 ,12 ,30);
        this.scanner = new Scanner(System.in);
        System.out.println("1.SUNDAY");
        System.out.println("2.MONDAY");
        System.out.println("3.TUESDAY");
        System.out.println("4.WEDNESDAY");
        System.out.println("5.THURSDAY");
        System.out.println("6.FRIDAY");
        System.out.println("7.SATURDAY");
        System.out.println("select a day of appointments to display(choose number): ");
        int choice = this.scanner.nextInt();
        for (Appointment a : this.calenderManager.appointmentArrayList) {
            if (choice == a.meetingDetails.calendar.get(Calendar.DAY_OF_WEEK)) {
                System.out.println("the appointment: " + a.meetingDetails.calendar.getTime() + " is at: " + numToDay(a.meetingDetails.calendar.get(Calendar.DAY_OF_WEEK)));
            }
        }
    }

    /**
     * supporting Method -converts a number representing a day of the week as a string
     */

    public String numToDay(int numOfDay) {
        if (numOfDay == 1)
            return "Sunday";
        else if (numOfDay == 2)
            return "Monday";
        else if (numOfDay == 3)
            return "Tuesday";
        else if (numOfDay == 4)
            return "Wednesday";
        else if (numOfDay == 5)
            return "Thursday";
        else if (numOfDay == 6)
            return "Friday";
        else if (numOfDay == 7)
            return "Saturday";
        else return "Error";
    }

}
