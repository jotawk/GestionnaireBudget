import java.util.Scanner;

public class GestionnaireBudget {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        String[] nomsDepenses = new String[50];
        double[] montantsDepenses = new double[50];
        String[] categories = new String[50];
        int nombreDepenses = 0;

        System.out.println("💰 === GESTIONNAIRE DE BUDGET === 💰\n");

        while (continuer) {
            afficherMenu();
            int choix = demanderChoix(scanner);

            if (choix == 1) {
                nombreDepenses = ajouterDepense(scanner, nomsDepenses, montantsDepenses, categories, nombreDepenses);
            } else if (choix == 2) {
                afficherToutesDepenses(nomsDepenses,
                montantsDepenses,
                categories,
                nombreDepenses);
            } else if (choix == 3) {
                System.out.println("Le total des dépenses s'élève à : " + calculerTotal(montantsDepenses, nombreDepenses) + " euros");
            } else if (choix == 5) {
                continuer = false;
            }
        }

        System.out.println("\n👋 Au revoir !");
        scanner.close();
    }

    public static void afficherMenu() {
        System.out.println("=== MENU ===\n" +
                "[1] Ajouter une dépense\n" +
                "[2] Voir toutes les dépenses\n" +
                "[3] Voir le total\n" +
                "[4] Statistiques visuelles\n" +
                "[5] Quitter");
    }

    public static int demanderChoix(Scanner scanner) {
        System.out.print("\nVotre choix : ");
        return scanner.nextInt();
    }

    public static int ajouterDepense(Scanner scanner,
                                     String[] noms,
                                     double[] montants,
                                     String[] cats,
                                     int nombreActuel) {
        scanner.nextLine();
        System.out.println("Quel est le nom de la dépense ?");
        String nomDepense = scanner.nextLine();
        System.out.println("Quel est le montant de la dépense ?");
        double montantDepense = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Quel est la catégorie de la dépense ? (Nourriture, Transport, Loyer, Loisirs...)");
        String categorie = scanner.nextLine();
        if (categorie.equals("Nourriture") ||
                categorie.equals("Transport") ||
                categorie.equals("Loyer") ||
                categorie.equals("Loisirs")) {

            noms[nombreActuel] = nomDepense;
            montants[nombreActuel] = montantDepense;
            cats[nombreActuel] = categorie;

            System.out.println("✅ Dépense ajoutée !");
        }
        return nombreActuel + 1;
    }
    public static void afficherToutesDepenses(
            String[] nomsDepenses,
            double[] montantsDepenses,
            String[] categories,
            int nombreDepenses) {
        System.out.println("\n📋 TOUTES LES DÉPENSES\n");

        if (nombreDepenses == 0) {
            System.out.println("Aucune dépense enregistrée.");
            return;
        }

        // Boucle pour afficher chaque dépense
        for (int i = 0; i < nombreDepenses; i++) {
        System.out.println((i+1) + " " + nomsDepenses[i] + " : " + montantsDepenses[i] + "euros de catégorie : " + categories[i]);
        }

        System.out.println("\nTotal : " + nombreDepenses + " dépenses");
    }

    public static double calculerTotal(double[] montantsDepenses,
                                       int nombreDepenses) {
        double total = 0;
        for (int i = 0; i < nombreDepenses; i++) {
                total += montantsDepenses[i];
        }
        return total;
    }
}
