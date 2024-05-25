package pjatk.finalproject;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {

        // Wszystkie nazwy:
        //  - zmiennych,
        //  - metod,
        //  - klas,
        //  - ostrzeżeń o błędach w danych wejściowych
        //  zostały przygotowane w języku angielskim ze względu na brak problemów z odmianą słów.



        try {
            ObjectPlus.writeExtents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
