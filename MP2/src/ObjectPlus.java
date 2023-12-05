import java.io.*;
import java.util.*;

public class ObjectPlus implements Serializable {

    public static final String FILE_NAME = "rentalData.dat";
    private static Map<Class<? extends ObjectPlus>, List> extent = new HashMap<>();

    public ObjectPlus() {
        addToExtent();
    }

    protected void addToExtent() {
        List list = extent.get(this.getClass());

        if ( list == null ){
            list = new ArrayList();
            extent.put(this.getClass(), list);
        }

        list.add(this);
    }
    public void removeFromExtent() {
        List list = extent.get(this.getClass());

        if ( list != null ){
            list.remove(this);
        }
    }

    public static void saveExtent() throws IOException {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))
        ) {
            oos.writeObject(extent);
            oos.writeDouble(Rental.getKmPrice());
        }

    }
    public static void loadExtent() throws IOException, ClassNotFoundException {
        try (
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))
        ) {
            extent = (Map<Class<? extends ObjectPlus>, List>) ois.readObject();
            Rental.setKmPrice(ois.readDouble());
        }
    }

    public static <E> List<E> getExtentForClass(Class<E> clas) {
        return Collections.unmodifiableList(extent.get(clas));
    }
}
