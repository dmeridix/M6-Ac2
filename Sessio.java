import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.List;

public class Sessio {
    private List<encarrec> encarr = new ArrayList<>();
    private Exportador exportador = new Exportador();

    public static void main(String[] args) {
        Sessio sessio = new Sessio(); // Crear una instancia de Sessio
        sessio.menu(); // Llamar al método menu
    }

    public void menu() {
        while (true) {
            System.out.println(
                "Introdueix el número de l'acció que vols fer?\n 1 --> Generar un nou encàrrec\n 2 --> Mostrar un encàrrec\n 3 --> Sortir");
            System.out.println("Selecciona una de les tres opcions (1, 2 o 3): ");
            int input = Integer.parseInt(Utils.readLine());
            
            while (input != 1 && input != 2 && input != 3) {
                System.out.println("Opcio incorrecta, has d'escriure 1, 2 o 3");
                input = Integer.parseInt(Utils.readLine());
            }
            switch (input) {
                case 1:
                    generarEncarrec();
                    break;
                case 2:
                    mostrarEncarrec();
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
            System.out.println("Vols fer una altra acció? ");
            String resposta = Utils.readLine().toLowerCase();

            if (!Utils.respostaABoolean(resposta)) {
                System.out.println("Sortint...");
                break;
            }
        }
    }

    public void generarEncarrec() {
        boolean mesEnc = true;
        while (mesEnc){
            encarrec nouEncarrec = new encarrec();
            System.out.println("Nom del client: ");
            String nomCli = Utils.readLine();
            nouEncarrec.setNomClient(nomCli);
            System.out.println("Telefon del client: ");
            String tel = Utils.readLine();
            nouEncarrec.setTelef(tel);
            System.out.println("Data de l'encarrec: ");
            String dat = Utils.readLine();
            nouEncarrec.setData(dat);
            boolean mes = true;
            // Preguntem l'informació sobre l'article que vol
            while (mes) {
                Article nouArticle = new Article();
                System.out.println("Quantitat d'articles: ");
                Float quan = null;
                try {
                    quan = Float.parseFloat(Utils.readLine());
                } catch (NumberFormatException e) {
                    System.out.println("Error: has de introducir un número válido.");
                    e.printStackTrace();
                    continue;
                }
                nouArticle.setQuantitat(quan);
                System.out.println("Unitat de l'article:  ");
                String uni = Utils.readLine();
                nouArticle.setUnitat(uni);
                System.out.println("Nom de l’article: ");
                String arti = Utils.readLine();
                nouArticle.setNom(arti);
                nouEncarrec.setArticles(nouArticle);
                System.out.println("Vols afegir un nou article? ");
                mes = Utils.respostaABoolean(Utils.readLine());
            }
            System.out.println("Vols afegir un nou encarrec? ");
            mesEnc = Utils.respostaABoolean(Utils.readLine());
        }

        System.out.println(
                "Introdueix el format amb el que vols generar el fitxer: \n 1 --> Fitxer amb format albarà \n 2 --> Fitxer amb format CSV \n 3 --> Fitxer amb format binari");
        int input = Integer.parseInt(Utils.readLine());
    
        while (input != 1 && input != 2 && input != 3) {
            System.out.println("Opcio incorrecta, has d'escriure 1, 2 o 3");
            System.out.println(
                    "Introdueix el format amb el que vols generar el fitxer: \n 1 --> Fitxer amb format albarà \n 2 --> Fitxer amb format CSV \n 3 --> Fitxer amb format binari");
            System.out.println("Selecciona una de les tres opcions (1, 2 o 3): ");
            input = Integer.parseInt(Utils.readLine());
        }
        switch (input) {
            case 1:
                generarTxt();
                break;
            case 2:
                generarCSV();
                break;
            case 3:
                generarBinari();
                break;
        }
    }
    
    public void mostrarEncarrec() {

        System.out.println(
                "Introdueix el format amb el que vols mostrar el fitxer: \n 1 --> Fitxer amb format CSV \n 2 --> Fitxer amb format binari");
        int input = Integer.parseInt(Utils.readLine());

        while (input != 1 && input != 2) {
            System.out.println("Opcio incorrecta, has d'escriure 1 o 2");
            System.out.println(
                    "Introdueix el format amb el que vols mostrar el fitxer: \n 1 --> Fitxer amb format CSV \n 2 --> Fitxer amb format binari");
            input = Integer.parseInt(Utils.readLine());
        }
        System.out.println("Indica el nom del fitxer");
        String nom = Utils.readLine();
        String fileName = "/home/dani/M6-Ac1/fitxers/" + nom;
        File fitxer = new File(fileName);
        if (fitxer.exists()) {
            switch (input) {
                case 1:
                    exportador.mostrarCSV(fitxer);
                    break;
                case 2:
                    exportador.mostrarBinari(fitxer);
                    break;
                case 3:
                    exportador.lecturaSerelialitzable();
                    break;
            }
        } else {
            System.out.println("Fitxer no existent: " + fileName);
        }

    }

    
    
}
