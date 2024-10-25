import java.io.BufferedReader;
import java.io.File;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.ObjectInputStream;

public class Exportador {
    public void mostrarCSV(File fitxer) {
        try {
            BufferedReader lectura = new BufferedReader(new FileReader(fitxer));
            String linea;
            String[] clientInfo = {};
            if ((linea = lectura.readLine()) != null) {
                clientInfo = linea.split(";");
            }
            System.out.println("\nNom del client: " + clientInfo[0]);
            System.out.println("Telefon del client: " + clientInfo[1]);
            System.out.println("Data de l'encarrec: " + clientInfo[2]);
            System.out.println();
            System.out.println("Quantitat      Unitats    Article");
            System.out.println("===========    ========   ===================");

            while ((linea = lectura.readLine()) != null) {
                String[] articles = linea.split(";");
                System.out.println(articles[0] + "            " + articles[1] + "          "
                        + articles[2]);
            }
            lectura.close();
        } catch (IOException e) {
            System.out.println("Error al crear el fitxer txt");
            e.printStackTrace();
        }
    }

    public void mostrarBinari(File fitxer) {
        try {
            FileInputStream fileStream = new FileInputStream(fitxer);
            DataInputStream dataStream = new DataInputStream(fileStream);

            System.out.println("\nNom del client: " + dataStream.readUTF());
            System.out.println("Telefon del client: " + dataStream.readUTF());
            System.out.println("Data de l'encarrec: " + dataStream.readUTF());
            System.out.println();
            System.out.println("Quantitat      Unitats    Article");
            System.out.println("===========    ========   ===================");

            int numArticles = dataStream.readInt();

            for (int i = 0; i < numArticles; i++) {
                float quantitat = dataStream.readFloat();
                String unitat = dataStream.readUTF();
                String nomArticle = dataStream.readUTF();
    
                System.out.printf("%-12s    %-8s   %s%n", quantitat, unitat, nomArticle);
            }
            fileStream.close();
            dataStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fitxer binari no existent");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al mostrar el fitxer bin");
            e.printStackTrace();
        }
    }
    public void lecturaSerelialitzable(String fitxer) {
        ArrayList<encarrec> llista = null; 
        try (FileInputStream fileIn = new FileInputStream(fitxer);
            ObjectInputStream objOut = new ObjectInputStream(fileIn)) {
                llista = (ArrayList<encarrec>)objOut.readObject();
            for(encarrec encarrec : llista){
                System.out.println(encarrec);
            }
        } catch (Exception e) {
            System.out.println("Error durant la lectura serialitzada: " + e.getMessage());
        }
    }
}