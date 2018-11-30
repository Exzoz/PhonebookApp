package cp330.phonebook;

import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
    public static void main(final String[] args) throws FileNotFoundException {
        System.out.println("Welcome to Phonebook");
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.help();
        final Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            String line = input.nextLine();
            phoneBook.processCommand(line);
        }
    }
}
