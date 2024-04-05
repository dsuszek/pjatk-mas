package pjatk.mp2;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;

public abstract class ObjectPlusPlus extends ObjectPlus implements Serializable {
    private Map<String, Map<Object, ObjectPlusPlus>> links = new Hashtable<>();
    private static Set<ObjectPlusPlus> allParts = new HashSet<>();

    public ObjectPlusPlus() {
        super();
    }

    private void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject, Object qualifier, int counter) {
        Map<Object, ObjectPlusPlus> objectLinks;

        if (counter < 1) {
            return;
        }

        if (links.containsKey(roleName)) {
            objectLinks = links.get(roleName);
        } else {
            objectLinks = new HashMap<>();
            links.put(roleName, objectLinks);
        }

        if (!objectLinks.containsKey(qualifier)) {
            objectLinks.put(qualifier, targetObject);
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
        // check if the part exists somewhere
        if (allParts.contains(partObject)) {
            throw new Exception("The part is already connected to a whole!");
        }

        addLink(roleName, reverseRoleName, partObject);

        // store adding the object as a part
        allParts.add(partObject);
    }

    public void removeWhole(String roleName) throws Exception {
        // check if the whole contains any parts
        if (this.links.isEmpty()) {
            this.removeFromExtent();
        } else {
            for(link : getLinks(roleName)) {

            }
        }
    }

    public ObjectPlusPlus[] getLinks(String roleName) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;

        if (!links.containsKey(roleName)) {
            // no links for the role
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);
        return objectLinks.values().toArray(new ObjectPlusPlus[0]);
    }

    public void showLinks(String roleName, PrintStream stream) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;

        if (!links.containsKey(roleName)) {
            // no links
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);
        Collection collection = objectLinks.values();
        stream.println(this.getClass().getSimpleName() + " links, role '" + roleName + "':");

        for (Object o : collection) {
            stream.println(o);
        }
    }

    public ObjectPlusPlus getLinkedObject(String roleName, Object qualifier) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;

        if(!links.containsKey(roleName)) {
            // no links
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);
        if(!objectLinks.containsKey(qualifier)) {
            // no link for the qualifier
            throw new Exception("No link for the qualifier: " + qualifier);
        }

        return objectLinks.get(qualifier);
    }
}
