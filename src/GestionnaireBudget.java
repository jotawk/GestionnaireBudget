import java.util.ArrayList;
import java.util.Scanner;

public class GestionnaireBudget {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(java.util.Locale.FRENCH);
        boolean continuer = true;

        ArrayList<Depense> depenses = new ArrayList<>();
        String categorieRecherchee = "";

        System.out.println("💰 === GESTIONNAIRE DE BUDGET === 💰\n");
        System.out.println("Quel est votre budget mensuel ?");
        double budget = scanner.nextDouble();

        while (continuer) {
            afficherMenu();
            int choix = demanderChoix(scanner);

            if (choix == 1) {
                ajouterDepense(scanner, depenses);
                if (!depenses.isEmpty()) {
                    System.out.println("\n📋 Aperçu de votre dépense :");
                    depenses.get(depenses.size()-1).afficher();
                }
            } else if (choix == 2) {
                afficherToutesDepenses(depenses);
            } else if (choix == 3) {
                System.out.println("Le total des dépenses s'élève à : " + calculerTotal(depenses) + " euros");
            } else if (choix == 4) {
                scanner.nextLine();
                System.out.println("Quelle catégorie voulez-vous consulter ? (Nourriture, Transport, Loyer, Loisirs)");
                categorieRecherchee = scanner.nextLine();
                calculerTotalParCategorie(depenses, categorieRecherchee);
            } else if (choix == 5) {
                afficherStatistiquesVisuelles(depenses);
            } else if (choix == 6) {
                supprimerUneDepense(scanner, depenses);
            } else if (choix == 7) {
                modifierUneDepense(scanner, depenses);
            } else if (choix == 8) {
                continuer = false;
            } else if (choix == 9) {
                afficherBudgetRestant(budget, depenses);
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
                "[4] Voir les dépenses par catégorie\n" +
                "[5] Statistiques visuelles\n" +
                "[6] Supprimer une dépense\n" +
                "[7] Modifier une dépense\n" +
                "[8] Quitter\n" +
                "[9] Voir mon budget restant\n");
    }

    public static void afficherBudgetRestant(double budget, ArrayList<Depense> depenses) {
        double totalDepense = calculerTotal(depenses);
        double reste = budget - totalDepense;

        System.out.println("\n💰 === BUDGET MENSUEL ===");
        System.out.println("Budget mensuel : " + budget + "€");
        System.out.println("Total dépensé : " + totalDepense + "€");
        System.out.println("Il vous reste : " + reste + "€");

        double pourcentageUtilise = (totalDepense / budget) * 100;
        if (pourcentageUtilise < 50) {
            System.out.println("😊 Tout va bien !");
        } else if (pourcentageUtilise < 80) {
            System.out.println("⚠️ Attention à vos dépenses !");
        } else {
            System.out.println("🚨 Budget presque épuisé !");
        }
    }

    public static int demanderChoix(Scanner scanner) {
        System.out.print("\nVotre choix : ");
        return scanner.nextInt();
    }

    public static void ajouterDepense(Scanner scanner, ArrayList<Depense> depenses) {
        scanner.nextLine();
        System.out.println("Quel est le nom de la dépense ?");
        String nomDepense = scanner.nextLine();

        System.out.println("Quel est le montant de la dépense ?");
        double montantDepense = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Quelle est la catégorie de la dépense ? (Nourriture, Transport, Loyer, Loisirs...)");
        String categorie = scanner.nextLine();

        if (categorie.equals("Nourriture") || categorie.equals("Transport") ||
                categorie.equals("Loyer") || categorie.equals("Loisirs")) {

            Depense nouvelleDepense = new Depense(nomDepense, montantDepense, categorie);
            depenses.add(nouvelleDepense);
            System.out.println("✅ Dépense ajoutée !");
        }
    }

    public static void modifierUneDepense(Scanner scanner, ArrayList<Depense> depenses) {
        scanner.nextLine();
        afficherToutesDepenses(depenses);

        System.out.println("Quel est le n° de la ligne que vous souhaitez modifier ?");
        int numeroLigne = scanner.nextInt();

        if (numeroLigne < 1 || numeroLigne > depenses.size()) {
            System.out.println("❌ Numéro invalide !");
            return;
        }

        int index = numeroLigne - 1;
        Depense depense = depenses.get(index); // Permet de récupérer la dépense

        System.out.println("Que voulez-vous modifier ?\n" +
                "[1] Nom\n" +
                "[2] Montant\n" +
                "[3] Catégorie");

        int choixModif = scanner.nextInt();

        if (choixModif == 1) {
            scanner.nextLine();
            System.out.println("Quel nouveau nom souhaitez-vous ?");
            String nouveauNom = scanner.nextLine();
            depense.setNom(nouveauNom);

        } else if (choixModif == 2) {
            System.out.println("Quel nouveau montant souhaitez-vous ?");
            double nouveauPrix = scanner.nextDouble();
            depense.setMontant(nouveauPrix);

        } else if (choixModif == 3) {
            scanner.nextLine();
            System.out.println("Quelle nouvelle catégorie souhaitez-vous ?");
            String nouvelleCategorie = scanner.nextLine();
            depense.setCategorie(nouvelleCategorie);
        }

        System.out.println("✅ Dépense modifiée avec succès !");
    }

    public static void afficherToutesDepenses(ArrayList<Depense> depenses) {
        System.out.println("\n📋 TOUTES LES DÉPENSES\n");

        if (depenses.size() == 0) {
            System.out.println("Aucune dépense enregistrée.");
            return;
        }

        for (int i = 0; i < depenses.size(); i++) {
            Depense d = depenses.get(i);
            System.out.println((i+1) + ". " + d);
        }

        System.out.println("\nTotal : " + depenses.size() + " dépenses");
    }

    public static double calculerTotal(ArrayList<Depense> depenses) {
        double total = 0;
        for (int i = 0; i < depenses.size(); i++) {
            total += depenses.get(i).getMontant();
        }
        return total;
    }

    public static double calculerTotalParCategorie(ArrayList<Depense> depenses, String categorieRecherchee) {
        double total = 0;
        int compteur = 0;

        for (int i = 0; i < depenses.size(); i++) {
            Depense d = depenses.get(i);
            if (categorieRecherchee.equals(d.getCategorie())) {
                total += d.getMontant();
                compteur++;
            }
        }

        if (compteur == 0) {
            System.out.println("Aucune dépense dans la catégorie : " + categorieRecherchee);
        } else {
            System.out.println("Total " + categorieRecherchee + " : " + total + " euros (" + compteur + " dépense(s))");
        }
        return total;
    }

    public static double calculerTotalCategorieSilencieuse(ArrayList<Depense> depenses, String categorieRecherchee) {
        double total = 0;

        for (int i = 0; i < depenses.size(); i++) {
            Depense d = depenses.get(i);
            if (categorieRecherchee.equals(d.getCategorie())) {
                total += d.getMontant();
            }
        }

        return total;
    }

    public static void afficherStatistiquesVisuelles(ArrayList<Depense> depenses) {
        System.out.println("\uD83D\uDCCA === STATISTIQUES VISUELLES ===");

        String[] cats = {"Nourriture", "Transport", "Loyer", "Loisirs"};
        double totalGeneral = calculerTotal(depenses);

        for (int i = 0; i < 4; i++) {
            double totalCat = calculerTotalCategorieSilencieuse(depenses, cats[i]);

            double pourcentage = (totalCat / totalGeneral) * 100;
            int longueurBarre = (int) (pourcentage / 4);
            System.out.printf("%-12s | ", cats[i]);

            for (int j = 0; j < longueurBarre; j++) {
                System.out.print("█");
            }
            System.out.println(" : " + totalCat + "€ (" + Math.round(pourcentage) + "%)");
        }
    }

    public static void supprimerUneDepense(Scanner scanner, ArrayList<Depense> depenses) {
        scanner.nextLine();
        afficherToutesDepenses(depenses);

        System.out.println("Quel numéro de dépense voulez-vous supprimer ?");
        int numero = scanner.nextInt();

        if (numero < 1 || numero > depenses.size()) {
            System.out.println("❌ Numéro invalide !");
            return;
        }

        int index = numero - 1;
        depenses.remove(index);
        System.out.println("✅ Dépense supprimée !");
    }
}
