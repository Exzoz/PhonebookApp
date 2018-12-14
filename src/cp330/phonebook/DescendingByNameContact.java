package cp330.phonebook;

import java.util.Comparator;
import java.util.Map;

/** The comparison strategy for sorting the phonebook by name. */


public class DescendingByNameContact implements  Comparator<Contact> {

    public int compare(final Contact l, final Contact r) {
        return l.getName().compareTo(r.getName());
    }
}
