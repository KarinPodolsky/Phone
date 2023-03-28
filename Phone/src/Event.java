import java.util.Scanner;

public class Event extends Appointment {

    String meetingDescription;
    Scanner scanner;

    public Event(MeetingDetails meetingDetails, String meetingDescription) {
        super(meetingDetails);
        this.meetingDescription = meetingDescription;
        this.scanner = new Scanner(System.in);

    }
}
