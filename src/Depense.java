public class Depense {

    private String nom;
    private double montant;
    private String categorie;

    public Depense(String nom, double montant, String categorie) {
        this.nom = nom;

        if (montant <= 0) {
            System.out.println("❌ Erreur : Le montant doit être positif ! Montant mis à 0.01€");
            this.montant = 0.01;
        } else {
            this.montant = montant;
        }

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
        if (montant <= 0) {
            System.out.println("❌ Erreur : Le montant doit être positif !");
            return;
        }
        this.montant = montant;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return nom + " : " + montant + "€ (" + categorie + ")";
    }

    public void afficher() {
        System.out.println("┌─────────────────────────");
        System.out.println("│ 💰 " + nom);
        System.out.println("│ 💵 Montant: " + montant + "€");
        System.out.println("│ 📂 Catégorie: " + categorie);
        System.out.println("└─────────────────────────");
    }
}