import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Sessio {
    private List<encarrec> encarrecs = new ArrayList<>();
    private static final String CARPETA_FITXERS = "./fitxers/";

    public Sessio() {
        File carpeta = new File(CARPETA_FITXERS);
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }
    }

    public static void main(String[] args) {
        Sessio sessio = new Sessio();
        sessio.menu();
    }

    public void menu() {
        while (true) {
            System.out.println("Introdueix el número de l'acció que vols fer?\n 1 --> Generar un nou encàrrec\n 2 --> Mostrar tots els encàrrecs\n 3 --> Modificar un encàrrec\n 4 --> Sortir");
            int input = Integer.parseInt(Utils.readLine());

            while (input < 1 || input > 4) {
                System.out.println("Opció incorrecta, has d'escriure 1, 2, 3 o 4");
                input = Integer.parseInt(Utils.readLine());
            }
            switch (input) {
                case 1:
                    generarEncarrec();
                    break;
                case 2:
                    mostrarEncarrecs();
                    break;
                case 3:
                    modificarEncarrec();
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
        }
    }

    public void generarEncarrec() {
        boolean mesEnc = true;
        while (mesEnc) {
            encarrec nouEncarrec = crearEncarrec();
            encarrecs.add(nouEncarrec);
            System.out.println("Vols crear un altre encàrrec? (sí/no)");
            mesEnc = Utils.respostaABoolean(Utils.readLine());
        }
        serialitzarEncarrecs(CARPETA_FITXERS + "encarrecs.dat");
        System.out.println("Tots els encàrrecs s'han guardat correctament al fitxer.");
    }

    private encarrec crearEncarrec() {
        encarrec nouEncarrec = new encarrec();
        nouEncarrec.setIdEncarrec(encarrecs.size() + 1);
        System.out.println("Nom del client: ");
        nouEncarrec.setNomClient(Utils.readLine());
        System.out.println("Telèfon del client: ");
        nouEncarrec.setTelef(Utils.readLine());
        System.out.println("Data de l'encàrrec: ");
        nouEncarrec.setData(Utils.readLine());

        boolean mes = true;
        while (mes) {
            Article nouArticle = new Article();
            System.out.println("Quantitat d'articles: ");
            nouArticle.setQuantitat(Float.parseFloat(Utils.readLine()));
            System.out.println("Unitat de l'article: ");
            nouArticle.setUnitat(Utils.readLine());
            System.out.println("Nom de l’article: ");
            nouArticle.setNom(Utils.readLine());
            System.out.println("Preu de l’article: ");
            nouArticle.setPreu(Float.parseFloat(Utils.readLine()));

            nouEncarrec.setArticles(nouArticle);
            System.out.println("Vols afegir un nou article? (sí/no)");
            mes = Utils.respostaABoolean(Utils.readLine());
        }

        nouEncarrec.calcularPreuTotal();
        return nouEncarrec;
    }

    public void mostrarEncarrecs() {
        deserialitzarEncarrecs(CARPETA_FITXERS + "encarrecs.dat");
    }

    public void modificarEncarrec() {
        deserialitzarEncarrecs(CARPETA_FITXERS + "encarrecs.dat");
    
        System.out.println("Indica l'ID de l'encàrrec que vols modificar:");
        int idEncarrec = Integer.parseInt(Utils.readLine());
    
        boolean trobat = false;
    
        for (encarrec enc : encarrecs) {
            if (enc.getIdEncarrec() == idEncarrec) {
                trobat = true;
                System.out.println("Introduïu el nou telèfon:");
                String nouTelef = Utils.readLine();
                System.out.println("Introduïu la nova data:");
                String novaData = Utils.readLine();
    
                enc.setTelef(nouTelef);
                enc.setData(novaData);
                break;
            }
        }
    
        if (!trobat) {
            System.out.println("Encàrrec amb ID " + idEncarrec + " no trobat.");
        } else {
            serialitzarEncarrecs(CARPETA_FITXERS + "encarrecs.dat");
            System.out.println("Encàrrec modificat correctament.");
        }
    }
    

    public void serialitzarEncarrecs(String fitxer) {
        try (FileOutputStream fileOut = new FileOutputStream(fitxer);
             ObjectOutputStream objOut = new ObjectOutputStream(fileOut)) {
            objOut.writeObject(encarrecs);
        } catch (IOException e) {
            System.out.println("Error en la serialització d'encàrrecs: " + e.getMessage());
        }
    }

    public void deserialitzarEncarrecs(String fitxer) {
        try (FileInputStream fileIn = new FileInputStream(fitxer);
             ObjectInputStream objIn = new ObjectInputStream(fileIn)) {
            encarrecs = (ArrayList<encarrec>) objIn.readObject();
            for (encarrec enc : encarrecs) {
                System.out.println(enc);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en la deserialització d'encàrrecs: " + e.getMessage());
        }
    }
}
