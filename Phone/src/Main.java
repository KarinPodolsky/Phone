import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {
    /**
     * Method is used to print the menu
     **/
    public static void printApps() {
        System.out.println(" ");
        System.out.println("**********************************");
        System.out.println("*********** MAIN MENU ************");
        System.out.println("**********************************");
        System.out.println("1.PhoneBook");
        System.out.println("2.SMS");
        System.out.println("3.Calender");
        System.out.println("4.Exit");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Enter a number from the menu: ");
    }


    public static void main(String[] args) throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        Phonebook myPhonebook = new Phonebook();
        Chat myChat = new Chat();
        CalenderApp myCalender = new CalenderApp();
        int choice = 0;
        while (choice == 0) {
            printApps();
            System.out.print("selection:");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Error");
            }
            switch (choice) {
                case 1:
                    myPhonebook.phoneBookMenu();
                    break;
                case 2:
                    myChat.ChatMenu();
                    break;
                case 3:
                    myCalender.CalenderMenu();
                    break;
                case 4:
                    System.out.println("GoodBye");
                    System.exit(0);
                    break;
            }
            choice = 0;
        }
    }
}






