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
            default:
                System.out.println("Unknown command. Type help for list of valid commands.");
                break;
        }
        }


        @Override
    public void saveToFile() throws FileNotFoundException {

    }

    @Override
    public void loadFromFile() throws FileNotFoundException {


    @Override
    public void add(String command) {
        String[] s = splitCommand(command);
        //expect properly formed command of 6 strings
        if (s.length != 6) {
            System.out.println("Incorrect command format. Command should be in format 'add PHONE_NUMBER NAME EMAIL RINGTONE CONTACT_TYPE(FAMILY, FRIENDS, COLLEAGUES)'");
            return;
        }
        String phoneNumber = s[1];
        String name = s[2];
        String email = s[3];
        String ringtone = s[4];
        try {
            ContactType contactType = ContactType.valueOf(s[5]);
            createAndAddContact(phoneNumber, name, email, ringtone, contactType);
            System.out.println("Contact add to Phonebook");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Contact Type. Expected type of contact: Friends, Family, Colleagues ")
        }

        }


         private void createAndAddContact(String phoneNumber, String name, String email, String ringtone, ContactType contactType) {

                Contact contact = new Contact(phoneNumber, name, email, ringtone, contactType);
                contacts.put(phoneNumber, contact);
                switch (contactType) {
                    case friends:
                        friends.add(contact);
                        break;
                    case family:
                        family.add(contact);
                        break;
                    case colleagues:
                        colleagues.add(contact);
                        break;
                    default:
                        break;
                }
            }



    private String[] splitCommand(String line) {
        return line.split("\\s+");
            }




    @Override
    public void help() {
            System.out.println("================Phonebook Menu========");
            System.out.println("Available methods are:");
            System.out.println("save - Saves phonebook entries to file ");
            System.out.println("load - Loads phonebook entries from file");
            System.out.println("add phone name email ringtone contactType(family, friends, colleagues) - Adds phone number to phonebook");
            System.out.println("help - Displays application help menu");
            System.out.println("list group - List all phonebook entries. Group is optional and can be family, friends, colleagues. If group is not provided entire phonebook is listed");
            System.out.println("remove phone - Removes entrie from phonebook");
            System.out.println("show phone - Prints provided phone entry");
            System.out.println("search name - Search for contact by  name");
            System.out.println("sortName - Sorts contact by name");
            System.out.println("sortEmail - Sorts contact by email");

    }

    @Override
    public void list(String command) {
        String[] s = splitCommand(command);
        if (s.length == 1) {
            System.out.println("Listing All Phonebook Contact Information");
            Iterator it = contacts.entrySet().iterator();
        }

        }


    @Override
    public void remove() {
            }


    @Override
    public void show() {
        }

    @Override
    public void search() {

        }

    @Override
    public void sortName() {

        }

    @Override
    public void sortEmail() {

        }


    }
    }

