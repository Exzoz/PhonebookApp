package cp330.phonebook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class PhoneBook implements IPhoneBook {

    private Map<String, Contact> contacts;
    private List<Contact> family;
    private List<Contact> friends;
    private List<Contact> colleagues;

    public PhoneBook() {
        contacts = new HashMap<>();
        family = new ArrayList<>();
        friends = new ArrayList<>();
        colleagues = new ArrayList<>();
    }

    @Override
    public void processCommand(String line) throws FileNotFoundException {
        String command = line.split("\\s+")[0];
        switch (command) {
            case "save":
                saveToFile();
                break;
            case "load":
                loadFromFile();
                break;
            case "add":
                add(line);
                break;
            case "help":
                help();
                break;
            case "list":
                list(line);
                break;
            case "remove":
                remove(line);
                break;
            case "show":
                show(line);
                break;
            case "search":
                search(line);
                break;
            case "sortName":
                sortName();
                break;
            case "sortEmail":
                sortEmail();
                break;
            case "addEmail":
                addEmail(line);
                break;
            case "addRingtone":
                addRingtone(line);
                break;
            case "addGroup":
                addGroup(line);
                break;
            default:
                System.out.println("Unknown command. Type help for list of valid commands.");
                break;
        }
    }

    @Override
    public void saveToFile() throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter("phonebook.rtf")) {
            Iterator it = contacts.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Contact> pair = (Map.Entry) it.next();
                System.out.println(pair.getValue().toStringForFile());
            }
        }
        System.out.println("Phonebook Saved");
    }

    @Override
    public void loadFromFile() throws FileNotFoundException {
        File file = new File("phonebook.rtf");
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {
            String[] c = input.nextLine().split(",");
            String phoneNumber = c[0];
            String name = c[1];
            String email = c[2];
            RingtoneType ringtoneType = RingtoneType.valueOf(c[3]);
            ContactType contactType = ContactType.valueOf(c[4]);
            createAndAddContact(phoneNumber, name, email, ringtoneType, contactType);
        }
        input.close();
        System.out.println("Phonebook Loaded From File");
    }


    @Override
    public void add(String command) {
        String[] s = splitCommand(command);
        //expect properly formed command of 3 strings
        if (s.length != 3) {
            System.out.println("Incorrect command format. Command should be in format 'add NAME PHONE_NUMBER)'");
            return;
        }
        String name = s[1];
        String phoneNumber = s[2];
        createAndAddContact(phoneNumber, name, "n/a", RingtoneType.none, ContactType.none);
        System.out.println("Contact Added Successfully!");

    }


    private void createAndAddContact(String phoneNumber, String name, String email, RingtoneType ringtone, ContactType contactType) {

        Contact contact = new Contact(phoneNumber, name, email, ringtone, contactType);
        contacts.put(phoneNumber, contact);
        addToGroup(contact);
    }

    private void addToGroup(Contact contact) {
        if (contact.getContactType() != null) {
            switch (contact.getContactType()) {
                case friends:
                    friends.add(contact);
                    Collections.sort(friends, new DescendingByNameContact());
                    break;
                case family:
                    family.add(contact);
                    Collections.sort(family, new DescendingByNameContact());
                    break;
                case colleagues:
                    colleagues.add(contact);
                    Collections.sort(colleagues, new DescendingByNameContact());
                    break;
                default:
                    break;
            }
        }
    }


    @Override
    public void help() {
        System.out.println("========Phonebook Menu========");
        System.out.println("Available Commands:");
        System.out.println("save - Saves Phonebook Entries To File ");
        System.out.println("load - Loads Phonebook Entries From File");
        System.out.println("add - Adds Name + Phone Number to Phonebook");
        System.out.println("addGroup - Adds Category to Entry ");
        System.out.println("addRingtone - Adds RingtoneEither ding, chord, pulse to entry");
        System.out.println("addEmail - Adds email to entry");
        System.out.println("help - Displays Application Help Menu");
        System.out.println("list - List All Phonebook Entries by Group. If Category Is Not Provided Entire Phonebook Is Listed");
        System.out.println("remove - Removes Phone Number Entry From Phonebook");
        System.out.println("show - Prints Contact Entry Based On Phone Number Inputted");
        System.out.println("search - Search For Entry By Name");
        System.out.println("sortName - Sorts Contact By Name");
        System.out.println("sortEmail - Sorts Contact By Email");

    }

    @Override
    public void list(String command) {
        String[] s = splitCommand(command);
        displayHeaders();
        if (s.length == 1) {
            sortName();
        } else {
            String group = s[1];
            switch (group) {
                case "Friends":
                    printList(friends);
                    break;
                case "Family":
                    printList(family);
                    break;
                case "Colleagues":
                    printList(colleagues);
                    break;
                default:
                    System.out.println("Invalid Group Name. Expected Groups: Friends, Family, Colleagues");
            }
        }

    }

    private void printList(List<Contact> list) {
        for (Contact contact : list) {
            System.out.println(contact);
        }
    }


    @Override
    public void remove(String command) {
        String[] s = splitCommand(command);
        if (s.length != 2) {
            System.out.println("Incorrect command format. Command should be in format 'remove PHONE_NUMBER' ");
            return;
        }
        String phoneNumber = s[1];
        Contact contact = contacts.get(phoneNumber);
        if (contact == null) {
            System.out.println("Attempted to delete contact that does not exist");
        } else {
            // removing from the phonebook
            contacts.remove(phoneNumber);
            System.out.println("Contact Removed Successfully");
            // removing from specific group
            if (contact.getContactType() != null) {
                switch (contact.getContactType().toString()) {
                    case "friends":
                        friends.remove(contact);
                        break;
                    case "family":
                        family.remove(contact);
                        break;
                    case "colleagues":
                        colleagues.remove(contact);
                        break;
                    default:
                        break;
                }
            }
        }
    }



    @Override
    public void show(String command) {
        String[] s = splitCommand(command);
        if (s.length != 2) {
            System.out.println("Incorrect command format. Command should be in format 'show PHONE_NUMBER'");
            return;
        }
        String phoneNumber = s[1];
        Contact contact = contacts.get(phoneNumber);
        if (contact == null) {
            System.out.println("Failed To Find:" + phoneNumber);
        } else {
            displayHeaders();
            System.out.println(contact);
        }

        }

    @Override
    public void search(String command) {
        String[] s = splitCommand(command);
        if (s.length != 2) {
            System.out.println("Incorrect command format. Command should be in format 'search NAME'");
            return;
        }
        String searchTerm = s[1];
        List<Contact> results = new ArrayList();
        Iterator it = contacts.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Contact> pair = (Map.Entry) it.next();
            if (pair.getValue().getName().contains(searchTerm) || pair.getValue().getEmail().contains(searchTerm) || pair.getValue().getPhoneNumber().contains(searchTerm)) {
                results.add(pair.getValue());
            }
        }
        for (Iterator iterator = results.iterator(); iterator.hasNext();) {
            Contact contact = (Contact) iterator.next();
            System.out.println(contact);
        }
    }

    @Override
    public void sortName() {
        List<Entry<String, Contact>> list = new ArrayList(contacts.size());
        list.addAll(contacts.entrySet());
        Collections.sort(list, new DescendingByName());
        for (Entry<String, Contact> contact : list) {
            System.out.println(contact.getValue());
        }
    }

    @Override
    public void sortEmail() {
        List<Entry<String, Contact>> list = new ArrayList(contacts.size());
        list.addAll(contacts.entrySet());
        Collections.sort(list, new DescendingByEmail());
        for (Entry<String, Contact> contact : list) {
            System.out.println(contact.getValue());

        }
    }

    @Override
    public void addGroup(String command) {
        String[] s = splitCommand(command);
        if (s.length != 3) {
            System.out.println("Incorrect command format. Correct format: addGroup Category Phone Number ");
            return;
        }
        String contactType = s[1];
        String phoneNumber = s[2];
        Contact contact = contacts.get(phoneNumber);
        if (contact != null) {
            try {
                ContactType type = ContactType.valueOf(contactType);
                contact.setContactType(type);
                addToGroup(contact);
                System.out.println("Category Added Successfully!");
            } catch(Exception e) {
                System.out.println("Unknown Category. Available Categories: family, friends, colleagues, none");
            }
        } else {
            System.out.println("Failed to find contact with Phone Number: " + phoneNumber);
        }
    }

    @Override
    public void addRingtone(String command) {
        String[] s = splitCommand(command);
        if (s.length != 3) {
            System.out.println("Incorrect command format. Correct Format: addRingtone (ding, chord, pulse, none) PHONE_NUMBER");
            return;
        }
        String ringtone = s[1];
        String phoneNumber = s[2];
        Contact contact = contacts.get(phoneNumber);
        if (contact != null) {
            try {
                RingtoneType ringtoneType = RingtoneType.valueOf(ringtone);
                contact.setRingtoneType(ringtoneType);
                System.out.println("Ringtone Added Successfully!");
            } catch(Exception e) {
                System.out.println("Unknown Type. Available Ringtones: ding, chord, pulse, none");
            }
        } else {
            System.out.println("Failed to find contact with phoneNumber: " + phoneNumber);
        }
    }

    @Override
    public void addEmail(String command) {
        String[] s = splitCommand(command);
        if (s.length != 3) {
            System.out.println("Incorrect command format. Command should be in format 'addEmail EMAIL PHONE_NUMBER)'");
            return;
        }
        String email = s[1];
        String phoneNumber = s[2];
        Contact contact = contacts.get(phoneNumber);
        if (contact != null) {
            contact.setEmail(email);
            System.out.println("Email Added Successfully!");
        } else {
            System.out.println("Failed to find contact with phoneNumber: " + phoneNumber);
        }
    }


    private String[] splitCommand(String line) {
        return line.split("\\s+");
        }

    private void displayHeaders() {
        System.out.println("Name  Phone Number  Email  Ringtone  Category");
    }
}





