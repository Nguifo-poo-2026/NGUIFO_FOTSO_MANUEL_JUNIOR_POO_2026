package IPPlanmanager.console;

/**
 *
 * 
 */

import IPPlanmanager.model.BesoinReseau;
import java.util.ArrayList; 
import java.util.Scanner; 

public class ConsoleService {
    private final Scanner scanner;

    public ConsoleService() {
        scanner = new Scanner(System.in);
    }

    public String saisirTexte(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public int saisirEntier(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valeur invalide, entrez un nombre entier.");
            }
        }
    }

    public ArrayList<BesoinReseau> saisirBesoins() {
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        int nb = saisirEntier("Nombre de besoins reseau a saisir : ");
        for (int i = 1; i <= nb; i++) {
            System.out.println("Saisie du besoin " + i);
            String nom = saisirTexte("Nom du service ou departement : ");
            int hôtes = saisirEntier("Nombre d'hotes demandes : ");
            besoins.add(new BesoinReseau(nom, hôtes));
        }
        return besoins;
    }

    public void afficherMenu() {
        System.out.println("\n===== MENU IPPLAN-MANAGER =====");
        System.out.println("1. Saisir les besoins et generer un plan complet");
        System.out.println("2. Charger les besoins depuis un fichier CSV");
        System.out.println("3. Quitter");
        System.out.print("Choix : ");
    }
}