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
import java.io.Serializable;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;


public class encarrec implements Serializable{

    private static final long serialVersionUID = 1L;
 
    private String nomClient;
    private String telef;
    private String data;
    private List<Article> articles = new ArrayList<>();
    private int preuTotalEncarrec = 0;
    

    

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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getTelef() {
        return telef;
    }

    public void setTelef(String telef) {
        this.telef = telef;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(Article article) {
        articles.add(article);
    }

    public int getPreuTotalEncarrec() {
        return preuTotalEncarrec;
    }

    public void setPreuTotalEncarrec(int preuTotalEncarrec) {
        this.preuTotalEncarrec = preuTotalEncarrec;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
    
        builder.append("Nom del client: ").append(nomClient).append("\n")
           .append("Telefon del client: ").append(telef).append("\n")
           .append("Data de l'encarrec: ").append(data).append("\n\n")
           .append("Quantitat      Unitats    Article\n")
           .append("===========    ========   ===================\n");

        for (Article art : articles) {
            builder.append(String.format("%-12s    %-8s   %s%n", 
                art.getQuantitat(), art.getUnitat(), art.getNom()));
        }
        return builder.toString();
    }

    // Serialització
    public void escripturaerialitzable(String fitxer, ArrayList<encarrec> encarrecs) {
        try (FileOutputStream fileOut = new FileOutputStream(fitxer, true);
             ObjectOutputStream objOut = new ObjectOutputStream(fileOut)) {
            objOut.writeObject(encarrecs);
        } catch (IOException e) {
            System.out.println("Error durant l'escriptura serialitzada: " + e.getMessage());
        }
    }
    
}