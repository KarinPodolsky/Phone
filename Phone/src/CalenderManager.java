import java.util.ArrayList;
import java.util.Scanner;

public class CalenderManager {
    ArrayList<Appointment> appointmentArrayList;
    Scanner scanner;

    public CalenderManager() {
        this.appointmentArrayList = new ArrayList<Appointment>();
        this.scanner = new Scanner(System.in);
    }
    /**
     * Method is used to add appointment to the array list
     **/
    public void addAppointment(Appointment ap) {
        this.appointmentArrayList.add(ap);
    }
    /**
     * Method is used to remove appointment from the array list
     **/
    public void removeAppointment(){
        this.scanner = new Scanner(System.in);
        int i = 1;
        for (Appointment a : this.appointmentArrayList){
            System.out.println("" + i + ". meeting at: " + a.meetingDetails.calendar.getTime() + " with length:" + a.meetingDetails.meetingLength);
        }
        System.out.println("choose a meeting to remove (enter number)");
        int meetingToRemove = this.scanner.nextInt();
        try {
            this.appointmentArrayList.remove(meetingToRemove-1);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
