package cp330.phonebook;

import java.util.Comparator;
import java.util.Map;

/** The comparison strategy for sorting the phonebook by name . */

public class DescendingByName implements Comparator<Map.Entry<String, Contact>> {

    public int compare(final Map.Entry<String, Contact> l, final Map.Entry<String, Contact> r) {
        return l.getValue().getName().compareTo(r.getValue().getName());

    }



}
