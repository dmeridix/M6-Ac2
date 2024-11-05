import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class encarrec implements Serializable {

    private static final long serialVersionUID = 1L;
    private int idEncarrec;
    private String nomClient;
    private String telef;
    private String data;
    private List<Article> articles = new ArrayList<>();
    private float preuTotal;

    public void calcularPreuTotal() {
        preuTotal = 0;
        for (Article art : articles) {
            preuTotal += art.getQuantitat() * art.getPreu();
        }
    }

    public int getIdEncarrec() {
        return idEncarrec;
    }

    public void setIdEncarrec(int idEncarrec) {
        this.idEncarrec = idEncarrec;
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

    public float getPreuTotal() {
        return preuTotal;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        calcularPreuTotal();
        builder.append("ID de l'encàrrec: ").append(idEncarrec).append("\n")
            .append("Nom del client: ").append(nomClient).append("\n")
               .append("Telefon del client: ").append(telef).append("\n")
               .append("Data de l'encarrec: ").append(data).append("\n\n")
               .append("Quantitat      Unitats    Article              Preu Unitari\n")
               .append("===========    ========   ===================   ============\n");

        for (Article art : articles) {
            builder.append(String.format("%-12s    %-8s   %-20s   %.2f%n",
                    art.getQuantitat(), art.getUnitat(), art.getNom(), art.getPreu()));
        }

        builder.append("\nPreu total del encàrrec: ").append(String.format("%.2f", preuTotal)).append(" €\n");
        return builder.toString();
    }
}
