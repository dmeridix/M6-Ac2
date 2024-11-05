import java.io.*;
import java.util.ArrayList;

public class Exportador {

    public void mostrarCSV(File fitxer) {
        try (BufferedReader lectura = new BufferedReader(new FileReader(fitxer))) {
            String linea;
            String[] clientInfo = {};
            if ((linea = lectura.readLine()) != null) {
                clientInfo = linea.split(";");
            }
            System.out.println("\nNom del client: " + clientInfo[0]);
            System.out.println("Telefon del client: " + clientInfo[1]);
            System.out.println("Data de l'encarrec: " + clientInfo[2]);
            System.out.println();
            System.out.println("Quantitat      Unitats    Article               Preu Unitari");
            System.out.println("===========    ========   ===================   ============");

            while ((linea = lectura.readLine()) != null) {
                String[] articles = linea.split(";");
                System.out.printf("%-12s    %-8s   %-20s   %.2f%n",
                        articles[0], articles[1], articles[2], Float.parseFloat(articles[3]));
            }
        } catch (IOException e) {
            System.out.println("Error al llegir el fitxer CSV");
            e.printStackTrace();
        }
    }

    public void mostrarBinari(File fitxer) {
        try (DataInputStream dataStream = new DataInputStream(new FileInputStream(fitxer))) {
            System.out.println("\nNom del client: " + dataStream.readUTF());
            System.out.println("Telefon del client: " + dataStream.readUTF());
            System.out.println("Data de l'encarrec: " + dataStream.readUTF());
            System.out.println();
            System.out.println("Quantitat      Unitats    Article               Preu Unitari");
            System.out.println("===========    ========   ===================   ============");

            int numArticles = dataStream.readInt();
            for (int i = 0; i < numArticles; i++) {
                float quantitat = dataStream.readFloat();
                String unitat = dataStream.readUTF();
                String nomArticle = dataStream.readUTF();
                float preuUnitari = dataStream.readFloat();

                System.out.printf("%-12s    %-8s   %-20s   %.2f%n", quantitat, unitat, nomArticle, preuUnitari);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fitxer binari no existent");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error al mostrar el fitxer binari");
            e.printStackTrace();
        }
    }

    public void lecturaSerialitzada(String fitxer) {
        ArrayList<encarrec> llista = null;
        try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(fitxer))) {
            llista = (ArrayList<encarrec>) objIn.readObject();
            for (encarrec enc : llista) {
                System.out.println(enc);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error durant la lectura serialitzada: " + e.getMessage());
        }
    }
}
