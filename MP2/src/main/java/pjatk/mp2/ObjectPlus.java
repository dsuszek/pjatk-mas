package pjatk.mp2;

import java.io.*;
import java.util.*;

public class ObjectPlus implements Serializable {

    public static final String FILE_NAME = "rentalData.dat";
    private static Map<Class, List<ObjectPlus>> allExtents = new Hashtable<>();

    public ObjectPlus() {
        addToExtent();
    }

    protected void addToExtent() {
        List<ObjectPlus> extent = null;
        Class c = this.getClass();

        if (allExtents.containsKey(c)) {
            extent = allExtents.get(c);
        } else {
            extent = new ArrayList<>();
            allExtents.put(c, extent);
        }

        extent.add(this);
    }

    public void removeFromExtent() {
        List list = allExtents.get(this.getClass());

        if (list != null) {
            list.remove(this);
        }
    }

    public static void writeExtents() throws IOException {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
        ) {
            oos.writeObject(allExtents);
            oos.writeDouble(Rental.getKmPrice());
        }
    }

    public static void readExtents(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        allExtents = (Hashtable) ois.readObject();
    }

    public static <T> Iterable<T> getExtent(Class<T> c) throws ClassNotFoundException {
        if (allExtents.containsKey(c)) {
            return (Iterable<T>) allExtents.get(c);
        }

        throw new ClassNotFoundException(
                String.format("%s. Stored extents: %s",
                        c.toString(),
                        allExtents.keySet()));
    }

    public static void showExtentForClass(Class c) throws Exception {
        List<ObjectPlus> extent = null;

        if (allExtents.containsKey(c)) {
            extent = allExtents.get(c);
        } else {
            throw new Exception("Unknown class: " + c + "\n");
        }

        System.out.println("Extent of the class: " + c.getSimpleName());

        for (Object obj : extent) {
            System.out.println(obj);
            System.out.println();
        }
    }
}
