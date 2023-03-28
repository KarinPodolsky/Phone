import java.util.ArrayList;
import java.util.Scanner;

public class CalenderManager {
    ArrayList<Appointment> cal;
    Scanner scanner;

    public CalenderManager() {
        this.cal = new ArrayList<Appointment>();
        this.scanner = new Scanner(System.in);
    }

    public void addAppointment(Appointment ap) {
        this.cal.add(ap);
    }

    public void removeAppointment(){
        int i = 1;
        for (Appointment a : this.cal){
            System.out.println("" + i + ". " + a.meetingDetails);
        }
        System.out.println("choose a meeting to remove (enter number)");
        int meetingToRemove = this.scanner.nextInt();
        try {
            this.cal.remove(meetingToRemove-1);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
