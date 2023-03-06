
import utils.FileUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;


public class CalenderApp {
    Scanner scanner;
    Phonebook phonebook;


    public CalenderApp() throws FileNotFoundException {
        this.scanner = new Scanner(System.in);
        this.phonebook = new Phonebook();
        this.phonebook.ImportContacts();

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
        System.out.println("hi");
    }

    public void CalenderMenu() throws ParseException, IOException {
        int numFromList = 0;

        while (numFromList == 0) {
            printMenu();
            try {
                numFromList = Integer.parseInt(this.scanner.nextLine());
            } catch (Exception e) {
                //TODO: לדבג את החלק הזה - שגיאה מזרקת אחרי מחיקת פגישה
                System.out.println("Error: " + e);
            }
            switch (numFromList) {
                case 1:
                    this.addEvent();
                    break;
                case 2:
                    this.deleteEvent();
                    break;
                case 3:
                    this.eventsByDate();
                    break;
                case 4:
                    this.eventsWithContacts();
                    break;
                case 5:
                    this.overlappingEvents();
                    break;
                case 6:
                    this.eventsByDay();
                    break;
                case 7:
                    return;
            }
            numFromList = 0;
        }
    }

    public void addEvent() throws ParseException, IOException {
        System.out.println("Create meeting or occasion?");
        String choseEvent = this.scanner.nextLine();
        if (choseEvent.equalsIgnoreCase("meeting")) {
            this.phonebook.PrintContacts();
            Contact contactFound = this.phonebook.SearchContact();
            Event myEvent = new Event(contactFound);
            this.ExportMeeting(contactFound, myEvent);

        } else if (choseEvent.equalsIgnoreCase("occasion")) {
            System.out.println("Description the occasion");
            String description = this.scanner.nextLine();
            Event myEvent = new Event(description);
            this.ExportOccasion(description, myEvent);

        } else {
            System.out.println("This is not what i ask! ");
            this.CalenderMenu();
        }
    }


    public void deleteEvent() throws ParseException, IOException {
        FileUtils utils = new FileUtils();
        System.out.println("meeting or occasion?");
        String choseEvent = this.scanner.nextLine();
        if (choseEvent.equalsIgnoreCase("meeting")) {
            String dataFromFile = utils.ReadMeetingFile();
            System.out.println(dataFromFile);

            System.out.println("select a row number from the list: ");
            Integer indexFromList = this.scanner.nextInt();

            ArrayList<String> dataArray = new ArrayList<>();
            dataArray.addAll(Arrays.asList(dataFromFile.split("\n")));
            int rowToDelete = 0;
            for (String row : dataArray){
                if (row.split(", ")[0].equals(indexFromList.toString())){
                    rowToDelete = indexFromList;
                }
            }
            if (rowToDelete != 0){
                dataArray.remove(rowToDelete-1);
            } else {
                System.out.println("no row found - no row deleted.");
            }
            utils.DeleteMeetingFile();
            for (String row : dataArray){
                System.out.println(row);
                utils.WriteToMeetingFile(row + "\n");
            }

        } else if (choseEvent.equalsIgnoreCase("occasion")) {
            String dataFromFile = utils.ReadOccasionFile();
            System.out.println(dataFromFile);

            System.out.println("select a row number from the list: ");
            Integer indexFromList = this.scanner.nextInt();

            ArrayList<String> dataArray = new ArrayList<>();
            dataArray.addAll(Arrays.asList(dataFromFile.split("\n")));
            int rowToDelete = 0;
            for (String row : dataArray){
                if (row.split(", ")[0].equals(indexFromList.toString())){
                    rowToDelete = indexFromList;
                }
            }
            if (rowToDelete != 0){
                dataArray.remove(rowToDelete-1);
            } else {
                System.out.println("no row found - no row deleted.");
            }
            utils.DeleteOccasionFile();
            for (String row : dataArray){
                System.out.println(row);
                utils.WriteToOccasionFile(row + "\n");
            }

        } else {
            System.out.println("This is not what i ask! ");
            this.CalenderMenu();
        }
    }


    public void eventsByDate() {
    }

    public void eventsWithContacts() {
        this.phonebook.PrintContacts();
        Contact ContactFound = this.phonebook.SearchContact();

    }

    public void overlappingEvents() {

    }

    public void eventsByDay() {

    }

    public void ExportMeeting(Contact ContactFound, Event myEvent) throws FileNotFoundException {
        FileUtils util = new FileUtils();
        Integer rowNum = 0;
        rowNum = util.getRowAmountInFile("exported_meetings.txt") + 1;
        util.WriteToMeetingFile(rowNum.toString() + ", " + myEvent.eventDate.getTime() + ", " + ContactFound.firstName + ", " + ContactFound.middleName + ", " + ContactFound.lastName + ", " + ContactFound.phoneNumber + ", " + ContactFound.company + "\n");
    }

    public void ImportMeeting() throws FileNotFoundException {
        FileUtils util = new FileUtils();
        String str = util.ReadMeetingFile();
        String[] strArr = str.split("\n");
        for (String s : strArr) {
            String[] subArr = s.split(", ");
        }
    }

    public void ExportOccasion(String description, Event myEvent) throws FileNotFoundException {
        FileUtils util = new FileUtils();
        Integer rowNum = 0;
        rowNum = util.getRowAmountInFile("exported_occasions.txt") + 1;
        util.WriteToOccasionFile(rowNum.toString() + ", " + myEvent.eventDate.getTime() + ", " + description + "\n");
    }

    public void ImportOccasion() throws FileNotFoundException {
        FileUtils util = new FileUtils();
        String str = util.ReadOccasionFile();
        String[] strArr = str.split("\n");
        for (String s : strArr) {
            String[] subArr = s.split(", ");
        }
    }

}
