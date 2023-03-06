import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

import utils.FileUtils;

public class Phonebook {
    ArrayList<Contact> contactList;
    Scanner scanner;

    public Phonebook() {
        this.contactList = new ArrayList<Contact>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Method is used to print the menu
     **/
    public void printMenu() {
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("*********** PhoneBook ************");
        System.out.println("1.Add contact");
        System.out.println("2.Delete contact");
        System.out.println("3.View all contacts");
        System.out.println("4.Search contact");
        System.out.println("5.Phonebook sorting by name");
        System.out.println("6.Phonebook sorting by number");
        System.out.println("7.Phonebook arrangement upside down");
        System.out.println("8.Removing duplicates contacts");
        System.out.println("9.Export phonebook data to a file");
        System.out.println("10.Import the phonebook data from a file");
        System.out.println("11.Exit");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Enter a number from the menu: ");
    }

    public void phoneBookMenu() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        int numFromList = 0;

        while (numFromList == 0) {
            printMenu();
            try {
                numFromList = Integer.parseInt(input.nextLine());
            } catch (Exception e) {
                System.out.println("Error");
            }
            switch (numFromList) {
                case 1:
                    this.AddContact();
                    break;
                case 2:
                    this.DeleteContact();
                    break;
                case 3:
                    this.PrintContacts();
                    break;
                case 4:
                    this.SearchContact();
                    break;
                case 5:
                    this.sortingByName();
                    break;
                case 6:
                    this.sortingByNumber();
                    break;
                case 7:
                    this.upsideDown();
                    break;
                case 8:
                    this.RemovingDuplicates();
                    break;
                case 9:
                    this.ExportContacts();
                    break;
                case 10:
                    this.ImportContacts();
                    break;
                case 11:
                    return;
            }
            numFromList = 0;
        }
    }

    /**
     * Method is used to create instance of the contact class
     * and to add them to a list of contacts
     **/
    public void AddContact() {
        Contact newContact = new Contact();
        boolean foundDuplicate = false;
        if (newContact.company != null) {
            for (Contact contact : this.contactList) {
                if (newContact.getFirstName().equals(contact.getFirstName()) && newContact.getLastName().equals(contact.getLastName())) {
                    foundDuplicate = true;
                }
            }
            if (foundDuplicate == false) {
                this.contactList.add(newContact);
                System.out.println("\n~New contact added!~\n");
            } else {
                System.out.println("This contact already exists! ");
            }
        } else {
            System.out.println("contact is not added-company number invalid!");
        }

    }

    /**
     * Method is used to delete a contact from the contact list
     **/
    public void DeleteContact() {
        boolean foundContact = false;
        this.PrintContacts();
        System.out.println("Enter the contact first name that you would like to delete: ");
        String firstNameToDelete = this.scanner.nextLine();
        System.out.println("Enter the contact last name that you would like to delete: ");
        String lastNameToDelete = this.scanner.nextLine();
        for (Contact contact : this.contactList) {
            if (firstNameToDelete.equals(contact.firstName) && lastNameToDelete.equals(contact.lastName)) {
                this.contactList.remove(contact);
                foundContact = true;
                System.out.println("Contact is deleted!");
                return;
            }
        }
        if (foundContact == false) {
            System.out.println("This is not a name from the contact list!");
        }
    }

    /**
     * Method is used to print the contact list
     **/
    public void PrintContacts() {
        for (Contact contact : contactList) {
                if (contact.middleName.equals("")) {
                    System.out.println(contact.firstName + " " + contact.lastName + " - " + contact.phoneNumber + " company: " + contact.company);
                } else {
                    System.out.println(contact.firstName + " " + contact.middleName + " " + contact.lastName + " - " + contact.phoneNumber + " company: " + contact.company);
                }
        }
        System.out.println("");
    }

    /**
     * Method is used to print all contacts information by searching contact name
     **/
    public Contact SearchContact() {
        boolean foundContact = false;
        System.out.println("Enter the contact name that you would like to find: ");
        System.out.print("first name: ");
        String firsNameToFind = this.scanner.next();
        System.out.print("last name: ");
        String lastNameToFind = this.scanner.next();
        for (Contact contact : this.contactList) {
            if (firsNameToFind.equals(contact.firstName) && lastNameToFind.equals(contact.lastName)) {
                System.out.println(contact.firstName + " " + contact.lastName + " - " + contact.phoneNumber);
                foundContact = true;
                return contact;
            }
        }
        if (foundContact == false) {
            System.out.println("This is not a name from the contact list!");
        }
        return null;
    }

    /**
     * Method is used to sort the contact list by name
     **/
    public void sortingByName() {
        this.contactList.sort(Comparator.comparing(Contact::getFirstName).thenComparing(Contact::getLastName));
        this.PrintContacts();
    }

    /**
     * Method is used to sort the contact list by phone number
     **/
    public void sortingByNumber() {
        this.contactList.sort(Comparator.comparing(Contact::getPhoneNumber).reversed());
        this.PrintContacts();
    }

    /**
     * Method is used to revers the contact list
     **/
    public void upsideDown() {
        System.out.println("Before: ");
        this.PrintContacts();
        System.out.println("After: ");
        Collections.reverse(this.contactList);
        this.PrintContacts();
    }

    /**
     * Method is used to remove duplicate contacts from the contact list
     **/
    public void RemovingDuplicates() {
        Set<Contact> uniqueContactSet = this.contactList.stream().collect(Collectors.toCollection(()
                -> new TreeSet<>(Comparator.comparing(Contact::getFirstName).thenComparing(Contact::getPhoneNumber))));

        ArrayList<Contact> removedContactsArrayList = new ArrayList<Contact>(this.contactList);
        for (Contact c : uniqueContactSet) {
            removedContactsArrayList.remove(c);
        }

        for (Contact c : removedContactsArrayList) {
            System.out.println(c.getFirstName() + " " + c.getPhoneNumber() + " is a duplicate contact -> will be removed");
        }
        this.contactList.clear();
        this.contactList.addAll(uniqueContactSet);
    }

    /**
     * Method is used to export contacts from the contact list into tex file
     **/
    public void ExportContacts() {
        FileUtils util = new FileUtils();
        util.CreateContactFile();
        for (Contact contact : this.contactList) {
            util.WriteToContactFile(contact.firstName + ", " + contact.middleName + ", " + contact.lastName + ", " + contact.phoneNumber + ", " + contact.company + "\n");
        }
    }

    /**
     * Method is used to import contacts from a tex file to the contact list
     **/
    public void ImportContacts() throws FileNotFoundException {
        FileUtils util = new FileUtils();
        String str = util.ReadContactFile();
        String[] strArr = str.split("\n");
        for (String s : strArr) {
            String[] subArr = s.split(", ");
            Contact contact = new Contact(subArr[0], subArr[1], subArr[2], subArr[3], subArr[4]);
            this.contactList.add(contact);
        }
        this.RemovingDuplicates();
    }
}









