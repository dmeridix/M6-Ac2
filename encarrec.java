import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class encarrec {

    private String nomClient;
    private String telef;
    private String data;
    private List<Article> articles = new ArrayList<>();
    private int preuTotalEncarrec = 0;

    public void generarEncarrec() {
        boolean mes = true;
        System.out.println("Nom del client: ");
        String nomCli = Utils.readLine();
        this.nomClient = nomCli;
        System.out.println("Telefon del client: ");
        String tel = Utils.readLine();
        this.telef = tel;
        System.out.println("Data de l'encarrec: ");
        String dat = Utils.readLine();
        this.data = dat;

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
            articles.add(nouArticle);
            System.out.println("Vols afegir un nou article? ");
            mes = Utils.respostaABoolean(Utils.readLine());
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
                    mostrarCSV(fitxer);
                    break;
                case 2:
                    mostrarBinari(fitxer);
                    break;
            }
        } else {
            System.out.println("Fitxer no existent: " + fileName);
        }

    }

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

    public void generarTxt() {
        String fileName = "/home/dani/M6-Ac1/fitxers/" + "encarrecs_client_" + nomClient + "_"
                + System.currentTimeMillis() + ".txt";
        File fitxer = new File(fileName);
        if (fitxer.exists()) {
            System.out.println("Fitxer ja existent: " + fileName);
        } else {
            try {
                if (fitxer.createNewFile()) {
                    BufferedWriter escriptura = new BufferedWriter(new FileWriter(fitxer));
                    escriptura.write("Nom del client: " + nomClient);
                    escriptura.write("Telefon del client: " + telef);
                    escriptura.write("Data de l'encarrec: " + data);
                    escriptura.write("\n");

                    escriptura.write("Quantitat      Unitats    Article\n");
                    escriptura.write("===========    ========   ===================\n");

                    for (Article art : articles) {
                        escriptura.write(art.getQuantitat() + "            " + art.getUnitat() + "          "
                                + art.getNom() + "\n");
                    }
                    escriptura.close();
                    System.out.println("El fitxer txt s'ha escrit correctament.");
                }
            } catch (IOException e) {
                System.out.println("Error al crear el fitxer txt");
                e.printStackTrace();
            }
        }

    }

    public void generarCSV() {
        String fileName = "/home/dani/M6-Ac1/fitxers/" + "encarrecs_client_" + nomClient + "_"
                + System.currentTimeMillis() + ".csv";
        File fitxer = new File(fileName);
        if (fitxer.exists()) {
            System.out.println("Fitxer ja esxistent: " + fileName);
        } else {
            try {
                if (fitxer.createNewFile()) {
                    BufferedWriter escriptura = new BufferedWriter(new FileWriter(fitxer));
                    escriptura.write(nomClient + ';' + telef + ';' + data + ';' + '\n');

                    for (Article art : articles) {
                        escriptura.write(art.toCSV() + '\n');
                    }
                    escriptura.close();
                    System.out.println("El fitxer csv s'ha escrit correctament.");
                }
            } catch (IOException e) {
                System.out.println("Error al crear el fitxer csv");
                e.printStackTrace();
            }
        }
    }

    public void generarBinari() {
        String fileName = "/home/dani/M6-Ac1/fitxers/" + "encarrecs_client_" + nomClient + "_"
                + System.currentTimeMillis() + ".dat";
        File fitxer = new File(fileName);
        if (fitxer.exists()) {
            System.out.println("Fitxer ja esxistent: " + fileName);
        } else {
            try {
                FileOutputStream fileStream = new FileOutputStream(fileName);
                DataOutputStream stram = new DataOutputStream(fileStream);
                stram.writeUTF(nomClient);
                stram.writeUTF(telef);
                stram.writeUTF(data);

                stram.writeInt(articles.size());

                for (Article art : articles) {
                    stram.writeFloat(art.getQuantitat());
                    stram.writeUTF(art.getUnitat());
                    stram.writeUTF(art.getNom());
                }

                stram.close();
                fileStream.close();
                System.out.println("El fitxer binari s'ha escrit correctament.");
            } catch (FileNotFoundException e) {
                System.out.println("Fitxer no existent");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Error al crear el fitxer binari");
                e.printStackTrace();
            }
        }
    }

}