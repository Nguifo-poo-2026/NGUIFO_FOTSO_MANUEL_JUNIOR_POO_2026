package IPPlanmanager;

/**
 *
 * 
 */


import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== IPPlanManager : TP7 - Validations avancees =====");

        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        besoins.add(new BesoinReseau("ADMINISTRATION", 50));
        besoins.add(new BesoinReseau("TECHNIQUE", 120));
        besoins.add(new BesoinReseau("WIFI", 80));
        besoins.add(new BesoinReseau("SERVEURS", 20));

        MoteurVLSM moteur = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats = moteur.genererPlan("192.168.1.0", besoins);

        System.out.println("\nPlan genere :");
        for (ResultatVLSM r : resultats) r.afficher();

        ValidateurPlanAdressage validateur = new ValidateurPlanAdressage();
        try {
            validateur.verifierAdresses(resultats);
            validateur.verifierChevauchements(resultats);
            validateur.afficherValidationReussie();
        } catch (AdresseIPInvalideException | ChevauchementReseauException e) {
            System.out.println("Erreur de validation : " + e.getMessage());
        }

        System.out.println("\nTest de conflit VLAN :");
        GestionnaireVLAN gestionnaire = new GestionnaireVLAN();
        try {
            VLAN v1 = new VLAN(10, "ADMIN", resultats.get(0), "Admin");
            VLAN v2 = new VLAN(20, "TECH", resultats.get(1), "Tech");
            VLAN v3 = new VLAN(10, "WIFI", resultats.get(2), "WiFi (conflit)");
            gestionnaire.ajouterVLAN(v1);
            gestionnaire.ajouterVLAN(v2);
            gestionnaire.ajouterVLAN(v3); // Déclenche l'exception
        } catch (ConflitVLANException e) {
            System.out.println("Erreur VLAN : " + e.getMessage());
        }
    }
}
