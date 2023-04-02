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

    public void getProjectPath(){

    }


    /**
     * Method is used to create a file (to hold the contact data)
     **/
    public void CreateContactFile() {
        try {
            File myFile = new File("src/files/exported_contacts.txt");
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
            FileWriter myWriter = new FileWriter("src/files/exported_contacts.txt", true);
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
        File file = new File("src/files/exported_contacts.txt");
        Scanner sc = new Scanner(file);
        sc.useDelimiter("\\Z");
        String data = sc.next();
        sc.close();
        return data;
    }
    /**
     * Method is used to create a Chat file
     **/
    public void CreateChatFile() {
        try {
            File myFile = new File("src/files/exported_chats.txt");
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    /**
     * Method is used to clear the Chat file
     **/

    public void ClearChatFile() {
        try {
            File myFile = new File("src/files/exported_chats.txt");
            myFile.delete();
            myFile.createNewFile();
//            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Method is used to write to the Chat file
     **/
    public void WriteToChatFile(String contactDetails) {
        try {
            FileWriter myWriter = new FileWriter("src/files/exported_chats.txt", true);
            myWriter.append(contactDetails);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public String ReadChatFile() throws FileNotFoundException {
        File file = new File("src/files/exported_chats.txt");
        Scanner sc = new Scanner(file);
        sc.useDelimiter("\\Z");
        String data = sc.next();
        sc.close();
        return data;
    }


}


