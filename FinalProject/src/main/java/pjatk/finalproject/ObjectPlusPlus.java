package pjatk.finalproject;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;

public abstract class ObjectPlusPlus extends ObjectPlus implements Serializable {
    // przechowuje informacje o wszystkich powiązaniach danego obiektu
    private Map<String, Map<Object, ObjectPlusPlus>> links = new Hashtable<>();

    // przechowuje informacje o wszystkich częściach powiązanych z danym obiektem
    private static Set<ObjectPlusPlus> allParts = new HashSet<>();

    public ObjectPlusPlus() {
        super();
    }

    private void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject, Object qualifier, int counter) {
        Map<Object, ObjectPlusPlus> objectLinks;

        if (counter < 1) {
            return;
        }

        // znajdź zbiór powiązań dla danej roli
        if (links.containsKey(roleName)) {
            objectLinks = links.get(roleName);
        } else {
            // w przypadku braku powiązań - należy je stworzyć
            objectLinks = new HashMap<>();
            links.put(roleName, objectLinks);
        }

        // sprawdź, czy jest już powiązanie
        // jeśli nie, stwórz nowe powiązanie
        if (!objectLinks.containsKey(qualifier)) {
            // dodaj powiązanie dla docelowego obiekt
            objectLinks.put(qualifier, targetObject);

            // dodaj powiązanie zwrotne
            targetObject.addLink(reverseRoleName, roleName, this, this, counter - 1);
        }
    }

    public void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject, Object qualifier) {
        addLink(roleName, reverseRoleName, targetObject, qualifier, 2);
    }

    public void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject) {
        addLink(roleName, reverseRoleName, targetObject, targetObject);
    }

    public void addPart(String roleName, String reverseRoleName, ObjectPlusPlus partObject) throws Exception {
        // sprawdź, czy ta część już gdzieś nie występuje
        if (allParts.contains(partObject)) {
            throw new Exception("The part is already connected to a whole.");
        }

        addLink(roleName, reverseRoleName, partObject);

        // zapamiętaj dodanie obiektu jako części
        allParts.add(partObject);
    }

    public ObjectPlusPlus[] getLinks(String roleName) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;

        if (!links.containsKey(roleName)) {
            // brak powiązań dla tej roli
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);
        return (ObjectPlusPlus[]) objectLinks.values().toArray(new ObjectPlusPlus[0]);
    }

    public void showLinks(String roleName, PrintStream stream) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;

        if (!links.containsKey(roleName)) {
            // brak powiązań dla tej roli
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);
        Collection col = objectLinks.values();
        stream.println(this.getClass().getSimpleName() + " links, role '" + roleName + "':");

        for (Object obj : col) {
            stream.println("    " + obj);
        }
    }

    public ObjectPlusPlus getLinkedObject(String roleName, Object qualifier) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;

        if (!links.containsKey(roleName)) {
            // brak powiązań dla tej roli
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);
        if (!objectLinks.containsKey(qualifier)) {
            // brak powiązań dla tej roli
            throw new Exception("No link for the qualifier: " + qualifier);
        }

        return objectLinks.get(qualifier);
    }

    public boolean isLink(String roleName, ObjectPlusPlus targetObject) throws Exception {
        Map<Object, ObjectPlusPlus> objectLink;

        if (!links.containsKey(roleName)) {
            // brak powiązań dla tej roli
            throw new Exception("No links for the role: " + roleName);
        }

        objectLink = links.get(roleName);

        return objectLink.containsValue(targetObject);
    }
}
