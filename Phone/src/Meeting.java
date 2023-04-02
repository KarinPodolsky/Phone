
public class Meeting extends Appointment{

    Contact eventContact;


    public Meeting(MeetingDetails meetingDetails, Contact contact) {
        super(meetingDetails);
        this.eventContact = contact;

    }

}


