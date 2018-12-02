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
    public void add(String command);

    /**
     * Displays help menu
     */
    public void help();




}
