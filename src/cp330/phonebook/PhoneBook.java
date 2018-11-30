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


        @Override
    public void saveToFile() throws FileNotFoundException {

    }

    @Override
    public void loadFromFile() throws FileNotFoundException {


    @Override
    public void add() {
        }
    }




    @Override
    public void help() {
            System.out.println("================Phonebook Menu========");
            System.out.println("Available methods are:");



    }

    @Override
    public void list(String command) {
        }
    }



    @Override
    public void remove() {


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