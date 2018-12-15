package cp330.phonebook;

public class Contact {
    private String phoneNumber;
    private String name;
    private String email;
    private RingtoneType ringtoneType;
    private ContactType contactType;

    public Contact(String phoneNumber, String name, String email, RingtoneType ringtoneType, ContactType contactType) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = email;
        this.ringtoneType = ringtoneType;
        this.contactType = contactType;
    }

    public String toString() {
        return name + " " + phoneNumber + " " + " "  + email + " " + ringtoneType  + " " + contactType;
    }

    public String toStringForFile() {
        return phoneNumber + "," + name + "," + email + "," + ringtoneType + "," + contactType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RingtoneType getRingtoneType() {
        return ringtoneType;
    }

    public void setRingtoneType(RingtoneType ringtoneType) {
        this.ringtoneType = ringtoneType;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }
}
