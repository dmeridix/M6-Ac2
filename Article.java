public class Article {
    
    private String nom;
    private float quantitat;
    private String unitat;

    public Article(String nom, float quantitat, String unitat) {
        this.nom = nom;
        this.quantitat = quantitat;
        this.unitat = unitat;
    }
    public Article() {
        this.nom = "";
        this.quantitat = 0;
        this.unitat = "";
    }

    @Override
    public String toString() {
        return "article [nom=" + nom + ", quantitat=" + quantitat + ", unitat=" + unitat + "]";
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(float quantitat) {
        this.quantitat = quantitat;
    }

    public String getUnitat() {
        return unitat;
    }

    public void setUnitat(String unitat) {
        this.unitat = unitat;
    }

    public String toCSV(){
        return this.quantitat + ";" + this.unitat + ";" + this.nom + ";" ;
    }

    
}