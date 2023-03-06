package utils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner; // Import the Scanner class to read text files


public class FileUtils {
    /**
     * Method is used to create a file (to hold the contact data)
     **/
    public void CreateContactFile() {
        try {
            File myFile = new File("exported_contacts.txt");
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists.");
                System.out.println("file deleted: " + myFile.delete());
                System.out.println("new file created: " + myFile.createNewFile());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Method is used to write the contact data from the contact list to a file
     *
     * @param contactDetails - a string with the format:
     *                       contact.name + ", " + contact.phoneNumber + "\n"
     **/
    public void WriteToContactFile(String contactDetails) {
        try {
            FileWriter myWriter = new FileWriter("exported_contacts.txt", true);
            myWriter.append(contactDetails);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Method is used to write the contact data from the contact list to a file
     *
     * @return string with all contacts separated by a new line and contact details separated by a comma
     **/
    public String ReadContactFile() throws FileNotFoundException {
        File file = new File("exported_contacts.txt");
        Scanner sc = new Scanner(file);
        sc.useDelimiter("\\Z");
        String data = sc.next();
        sc.close();
        return data;
    }

    public void CreateChatFile() {
        try {
            File myFile = new File("exported_chats.txt");
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            }
//            else {
//                System.out.println("File already exists.");
//                System.out.println("file deleted: " + myFile.delete());
//                System.out.println("new file created: " + myFile.createNewFile());
//            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void ClearChatFile() {
        try {
            File myFile = new File("exported_chats.txt");
            myFile.delete();
            myFile.createNewFile();
//            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Method is used to write the contact data from the contact list to a file
     *
     * @param contactDetails - a string with the format:
     *                       contact.name + ", " + contact.phoneNumber + "\n"
     **/
    public void WriteToChatFile(String contactDetails) {
        try {
            FileWriter myWriter = new FileWriter("exported_chats.txt", true);
            myWriter.append(contactDetails);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Method is used to write the contact data from the contact list to a file
     *
     * @return string with all contacts separated by a new line and contact details separated by a comma
     **/
    public String ReadChatFile() throws FileNotFoundException {
        File file = new File("exported_chats.txt");
        Scanner sc = new Scanner(file);
        sc.useDelimiter("\\Z");
        String data = sc.next();
        sc.close();
        return data;
    }

    public void WriteToMeetingFile(String meetingDetails) {
        try {
            FileWriter myWriter = new FileWriter("exported_meetings.txt", true);
            myWriter.append(meetingDetails);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public String ReadMeetingFile() throws FileNotFoundException {
        File file = new File("exported_meetings.txt");
        Scanner sc = new Scanner(file);
        sc.useDelimiter("\\Z");
        String data = sc.next();
        sc.close();
        return data;
    }

    public void DeleteMeetingFile() throws IOException {
        File file = new File("exported_meetings.txt");
        System.out.println("file deleted: " + file.delete());
        System.out.println("new file created: " + file.createNewFile());
    }
    public void DeleteOccasionFile() throws IOException {
        File file = new File("exported_Occasions.txt");
        System.out.println("file deleted: " + file.delete());
        System.out.println("new file created: " + file.createNewFile());
    }

    public void WriteToOccasionFile(String description) {
        try {
            FileWriter myWriter = new FileWriter("exported_occasions.txt", true);
            myWriter.append(description);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String ReadOccasionFile() throws FileNotFoundException {
        File file = new File("exported_occasions.txt");
        Scanner sc = new Scanner(file);
        sc.useDelimiter("\\Z");
        String data = sc.next();
        sc.close();
        return data;
    }

    /**
     * Method is used to return the amount of row in a file (By name)
     *
     * @return int with the amount of rows as the value
     **/
    public int getRowAmountInFile(String fileName) throws FileNotFoundException {
        int amountOfRows = 0;
        Path path = Paths.get(fileName);

        int lines = 0;
        try {
            amountOfRows = (int) Files.lines(path).count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return amountOfRows;
    }
}


