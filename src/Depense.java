public class Depense {

    private String nom;
    private double montant;
    private String categorie;

    public Depense(String nom, double montant, String categorie) {
        this.nom = nom;
        this.montant = montant;
        this.categorie = categorie;
    }

    public String getNom() {
        return nom;
    }

    public double getMontant() {
        return montant;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}