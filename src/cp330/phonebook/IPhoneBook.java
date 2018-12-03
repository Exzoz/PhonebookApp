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
     * Saves phonebook entries to the file
     * @throws FileNotFoundException
     */
    void saveToFile() throws FileNotFoundException;

    /**
     * Loads phonebook entries saved in file into memory
     * @throws FileNotFoundException
     */
    void loadFromFile() throws FileNotFoundException;

    /**
     * Adds new entry to the phonebook
     * @param command
     */
     void add(String command);

    /**
     * Displays help menu
     */
     void help();

    /**
     * List all or group of phonebook entries
     */
     void list(String command);

    /**
     * Displays phonebook entry with provided phone number
     * @param command
     */
     void show(String command);

    /**
     * Search and displays all phonebook entries found with name search
     * @param command
     */
    void search(String command);




}
