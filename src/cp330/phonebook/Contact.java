package cp330.phonebook;

public class Contact {
    private String phoneNumber;
    private String name;
    private String email;
    private String ringtone;
    private ContactType contactType;

    public Contact(String phoneNumber, String name, String email, String ringtone, ContactType contactType) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = email;
        this.ringtone = ringtone;
        this.contactType = contactType;
    }

    public String toString() {
        return "Contact: phoneNumber=" + phoneNumber + "; name=" + name + "; email=" + email + "; ringtone= + ringtone + "; contactType=" + contactType;
    }

    public String toStringForFile() {
        return phoneNumber + , + name + "," + email + "," + ringtone + "," + contactType;

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

   public ContactType getContactType() {
        return contactType;
        }


}