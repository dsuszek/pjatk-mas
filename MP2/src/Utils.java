public class Utils {
    public static void checkCorrectnessOfStringAttribute(String attribute) {
        if (attribute == null || attribute.isBlank()) {
            throw new IllegalArgumentException(attribute + " cannot be neither empty string nor null.");
        }
    }
}
