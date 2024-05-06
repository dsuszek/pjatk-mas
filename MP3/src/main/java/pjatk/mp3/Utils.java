package pjatk.mp3;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class Utils {

    public static void checkCorrectnessOfStringAttribute(String attribute, String paramName) {
        if (attribute == null || attribute.isBlank()) {
            throw new IllegalArgumentException(paramName + " cannot be neither empty string nor null.");
        }
    }

    public static void checkCorrectnessOfSetOfStringsAttribute(Set<String> attribute, String paramName) {
        if (attribute == null || attribute.isEmpty()) {
            throw new IllegalArgumentException(paramName + " cannot be neither empty string nor null.");
        }

        if (attribute.stream().anyMatch(e -> e == null || e.trim().isEmpty())) {
            throw new IllegalArgumentException("Any of the " + paramName.toLowerCase() + " cannot be neither empty nor null.");
        }
    }

    public static void checkCorrectnessOfNumericalValueGreaterThanZero(int value, String paramName) {
        if (value <= 0) {
            throw new IllegalArgumentException(paramName + " must be greater than 0.");
        }
    }

    public static void checkCorrectnessOfNumericalValueGreaterThanZero(Double value, String paramName) {
        if (value <= 0) {
            throw new IllegalArgumentException(paramName + " must be greater than 0.");
        }
    }

    public static void checkCorrectnessOfNumericalValueGreaterThanOrEqualToZero(Double value, String paramName) {
        if (value < 0) {
            throw new IllegalArgumentException(paramName + " must be greater than or equal to 0.");
        }
    }

    public static void checkCorrectnessOfNumericalValueGreaterThanZero(double value, String paramName) {
        if (value <= 0) {
            throw new IllegalArgumentException(paramName + " must be greater than 0.");
        }
    }

    public static void checkCorrectnessOfNumericalValueGreaterThanOrEqualToZero(double value, String paramName) {
        if (value < 0) {
            throw new IllegalArgumentException(paramName + " must be greater than or equal to 0.");
        }
    }

    public static void checkCorrectnessOfOptionalNumericalValueGreaterThanOrEqualToZero(Double value, String paramName) {
        if (value != null && value < 0) {
            throw new IllegalArgumentException(paramName + " must be greater than or equal to 0.");
        }
    }

    public static void checkIfDateIsNotInPast(LocalDate date, String paramName) {
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(paramName + " cannot be in the past.");
        }
    }

    public static void checkIfDateIsNotInFuture(LocalDate date, String paramName) {
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(paramName + " cannot be in the future.");
        }
    }

    public static void logError(String message) {
        System.err.println(message);
    }

    public static <T> void printInfoAboutObjects(List<T> objects) {
        for (T object : objects) {
            System.out.println(object);
            System.out.println();
        }
    }

    public static <T> void printInfoAboutObjects(Set<T> objects) {
        for (T object : objects) {
            System.out.println(object);
            System.out.println();
        }
    }
}
