package cp330.phonebook;

import java.io.FileNotFoundException;


public interface IPhoneBook {

    /**
     * Process command from console
     * @param line
     * @throws FileNotFoundException
     */
    void processCommand(String line) throws FileNotFoundException;

    /**
     * Adds new entry to the phonebook
     * @param command
     */
     void add(String command);

    /**
     * Displays help menu
     */
    public void help();

    /**
     * List all or group of phonebook entries
     */
    public void list(String command);

    /**
     * Displays phonebook entry with provided phone number
     * @param command
     */
    public void show(String command);




}
