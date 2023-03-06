import java.util.Scanner;

public class Contact {
    String firstName;
    String middleName;
    String lastName;
    String phoneNumber;
    String company;

    public Contact() {
        setFirstName();
        if (this.firstName != null) {
            setMiddleName();
            setLastName();
            if (this.lastName != null){
                setPhoneNumber();
                if (this.phoneNumber != null){
                    setCompany();
                }
            }
        }
    }
    public Contact (String firstName,String middleName,String lastName,String phoneNumber,String company){
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.company = company;
    }

    /**
     * Method is used to set the value of the name variable - used by the constructor
     **/
    public void setFirstName() {
        Scanner scanner = new Scanner(System.in);
        int failedAttempts = 0;
        boolean nameValid = true;

        while (failedAttempts < 3 || nameValid == true) {
            nameValid = true;
            System.out.println("Enter name: ");
            String tempName = scanner.nextLine();
            char[] nameChars = tempName.toCharArray();
            for (char c : nameChars) {
                if (Character.isLetter(c) == false && c != ' ') {
                    nameValid = false;
                }
            }
            if (nameValid == true) {
                this.firstName = tempName;
                return;
            } else {
                failedAttempts++;
                System.out.println("This is not a valid first name!");
                if (failedAttempts == 3) {
                    System.out.println("No more tries left");
                }
            }
        }
    }


    public void setMiddleName(){
        Scanner scanner = new Scanner(System.in);
        int failedAttempts = 0;
        boolean nameValid = true;

        while (failedAttempts < 3 || nameValid == true) {
            nameValid = true;
            System.out.println("Enter Middle name(optional): ");
            String tempName = scanner.nextLine();
            if (tempName.equals("")){
                this.middleName = "";
                return;
            }
            char[] nameChars = tempName.toCharArray();
            for (char c : nameChars) {
                if (Character.isLetter(c) == false && c != ' ') {
                    nameValid = false;
                }
            }
            if (nameValid == true) {
                this.middleName = tempName;
                return;
            } else {
                failedAttempts++;
                System.out.println("This is not a valid Middle name!");
                if (failedAttempts == 3) {
                    System.out.println("No more tries left");
                }
            }
        }
    }

    public void setLastName(){
        Scanner scanner = new Scanner(System.in);
        int failedAttempts = 0;
        boolean nameValid = true;

        while (failedAttempts < 3 || nameValid == true) {
            nameValid = true;
            System.out.println("Enter last name: ");
            String tempName = scanner.nextLine();
            char[] nameChars = tempName.toCharArray();
            for (char c : nameChars) {
                if (Character.isLetter(c) == false && c != ' ') {
                    nameValid = false;
                }
            }
            if (nameValid == true) {
                this.lastName = tempName;
                return;
            } else {
                failedAttempts++;
                System.out.println("This is not a valid last name!");
                if (failedAttempts == 3) {
                    System.out.println("No more tries left");
                }
            }
        }
    }
    /**
     * Method is used to set the value of the phone number variable - used by the constructor
     **/
    public void setPhoneNumber() {
        Scanner scanner = new Scanner(System.in);
        int failedAttempts = 0;
        boolean phoneValid = true;

        while (failedAttempts < 3 || phoneValid == true) {
            phoneValid = true;
            System.out.println("Enter phone number: ");
            String tempPhone = scanner.nextLine();
            char[] phoneChars = tempPhone.toCharArray();
            int phoneLength = phoneChars.length;
            for (char c : phoneChars) {
                if (Character.isDigit(c) == false) {
                    phoneValid = false;
                }
            }
            if (phoneValid == true && phoneLength == 10) {
                this.phoneNumber = tempPhone;
                return;
            } else {
                failedAttempts++;
                System.out.println("This is not a valid phone number!");
                if (failedAttempts == 3) {
                    System.out.println("No more tries left");
                    return;
                }
            }
        }
    }
    public void setCompany(){
        Scanner scanner = new Scanner(System.in);
        int failedAttempts = 0;
        boolean nameValid = true;

        while (failedAttempts < 3 || nameValid == true) {
            nameValid = true;
            System.out.println("Enter Company name: ");
            String tempName = scanner.nextLine();
            char[] nameChars = tempName.toCharArray();
            for (char c : nameChars) {
                if (Character.isLetter(c) == false && c != ' ') {
                    nameValid = false;
                }
            }
            if (nameValid == true) {
                this.company = tempName;
                return;
            } else {
                failedAttempts++;
                System.out.println("This is not a valid Company name!");
                if (failedAttempts == 3) {
                    System.out.println("No more tries left");
                }
            }
        }
    }



    public String getFirstName() {
        return firstName;
    }
    public String getMiddleName(){
        return middleName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
}



