public class Meeting extends Appointment{

    Contact eventContact;
    //Phonebook phonebook;

    public Meeting(MeetingDetails meetingDetails, Contact contact) {
        super(meetingDetails);
        this.eventContact = contact;
        //this.phonebook = new Phonebook();
    }

}


