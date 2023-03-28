import java.util.Calendar;

public class MeetingDetails {
    Calendar calendar;
    int meetingLength;

    public MeetingDetails(Calendar calendar, int meetingLength) {
        this.calendar = calendar;
        this.meetingLength = meetingLength;
    }
}
