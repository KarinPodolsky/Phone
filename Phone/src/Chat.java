import utils.FileUtils;

import java.io.FileNotFoundException;
import java.util.*;

public class Chat {
    Scanner scanner;
    Phonebook phonebook;
    String messages;
    ArrayList<String[]> messagesArray;


    public Chat() throws FileNotFoundException {
        this.scanner = new Scanner(System.in);
        this.messagesArray = new ArrayList<String[]>();
        this.phonebook = new Phonebook();
        this.phonebook.ImportContacts();
        this.ImportChats();
    }

    /**
     * Method is used to print the menu
     **/
    public void printMenu() {
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("*********** SMS ************");
        System.out.println("1.chat");
        System.out.println("2.Delete chat");
        System.out.println("3.view chat");
        System.out.println("4.Search message");
        System.out.println("5.view all chats");
        System.out.println("6.Exit");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Enter a number from the menu: ");

    }

    public void ChatMenu() throws FileNotFoundException {

        int numFromList = 0;

        while (numFromList == 0) {
            printMenu();
            try {
                numFromList = Integer.parseInt(this.scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Error");
            }
            switch (numFromList) {
                case 1:
                    this.startChat();
                    break;
                case 2:
                    this.DeleteChat();
                    break;
                case 3:
                    this.viewChat();
                    break;
                case 4:
                    this.SearchMessage();
                    break;
                case 5:
                    this.viewAllChats();
                    break;
                case 6:
                    return;
            }
            numFromList = 0;
        }
    }

    /**
     * Method is used to create chat with contact
     **/
    public void startChat() throws FileNotFoundException {
        FileUtils util = new FileUtils();
        util.CreateChatFile();
        System.out.println(" ");
        System.out.println("Contact list: ");
        this.phonebook.PrintContacts();
        Contact ContactFound = this.phonebook.SearchContact();
        if (ContactFound != null) {
            if (this.messagesArray.size() != 0) {
                for (String[] contactAndMessage : this.messagesArray) {
                    if (ContactFound.getFirstName().equals(contactAndMessage[0]) && ContactFound.getLastName().equals(contactAndMessage[2])) {
                        System.out.println(contactAndMessage[5]);
                    }
                }
                System.out.println(" ");
            }
            System.out.print("Message: ");
            this.messages = this.scanner.nextLine();
            this.ExportChats(ContactFound);
        }
    }

    /**
     * Method is used to delete chat with contact
     **/
    public void DeleteChat() throws FileNotFoundException {
        FileUtils util = new FileUtils();
        String new_message = "";
        boolean found_message = false;
        ArrayList<String[]> messagesArrayToDelete = new ArrayList<>();
        System.out.println("Contact list: ");
        this.phonebook.PrintContacts();
        Contact ContactDelete = this.phonebook.SearchContact();
        if (ContactDelete != null) {
            for (String[] message : this.messagesArray) {
                if (ContactDelete.getFirstName().equals(message[0]) && ContactDelete.getLastName().equals(message[2])) {
                    messagesArrayToDelete.add(message);
                    found_message = true;
                }
            }
            for (String[] message : messagesArrayToDelete) {
                this.messagesArray.remove(message);
            }
            String chatData = util.ReadChatFile();
            for (String[] message : messagesArrayToDelete) {
                new_message = Arrays.toString(message).replace("[", "").replace("]", "");
                chatData = chatData.replace(new_message + "\n", "");
                chatData = chatData.replace(new_message, "");
            }
            util.ClearChatFile();
            util.WriteToChatFile(chatData);
        }
        if (!found_message) {
            System.out.println("No messages with this contact");
        }
    }

    /**
     * Method is used to print a chat with specific contact
     **/
    public void viewChat() throws FileNotFoundException {
        System.out.println("Contact list: ");
        this.phonebook.PrintContacts();
        boolean found_message = false;
        Contact ContactFound = this.phonebook.SearchContact();
        if (ContactFound != null) {
            for (String[] message : this.messagesArray) {
                if (ContactFound.getFirstName().equals(message[0]) && ContactFound.getLastName().equals(message[2])) {
                    found_message = true;
                    System.out.println(message[5]);
                }
            }
        }
        if (!found_message) {
            System.out.println("No messages with this contact");
        }
    }

    /**
     * Method is used to found message and print the contact that hase that message
     **/
    public void SearchMessage() {
        boolean found_message = false;
        Set<String> contactWithMessage = new HashSet<>();
        System.out.println("Enter the Message you would like to find: ");
        String messageToFind = scanner.nextLine();
        for (String[] message : this.messagesArray) {
            if (messageToFind.equals(message[5])) {
                found_message = true;
                contactWithMessage.add(message[0] + " " + message[2]);
            }
        }
        for (String contact : contactWithMessage) {
            System.out.println(contact);
        }
        if (!found_message) {
            System.out.println("message not exists");
        }
    }

    /**
     * Method is used to see all the chats
     **/
    public void viewAllChats() throws FileNotFoundException {
        FileUtils util = new FileUtils();
        String str = util.ReadChatFile();
        System.out.println(str);
    }

    /**
     * Method is used to export Chats from file
     **/
    public void ExportChats(Contact ContactFound) {
        FileUtils util = new FileUtils();
        //util.CreateChatFile();
        util.WriteToChatFile(ContactFound.firstName + ", " + ContactFound.middleName + ", " + ContactFound.lastName + ", " + ContactFound.phoneNumber + ", " + ContactFound.company + ", " + this.messages + "\n");
    }

    /**
     * Method is used to export Chats to file
     **/
    public void ImportChats() throws FileNotFoundException {
        FileUtils util = new FileUtils();
        String str = util.ReadChatFile();
        String[] strArr = str.split("\n");
        for (String s : strArr) {
            String[] subArr = s.split(", ");
            this.messagesArray.add(subArr);
        }
    }
}


