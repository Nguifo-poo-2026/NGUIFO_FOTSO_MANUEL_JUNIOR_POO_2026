package IPPlanmanager;

/**
 *
 *
 */

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== IPPlanManager : TP8 - Recommandations =====");

        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        besoins.add(new BesoinReseau("ETUDIANTS", 500));
        besoins.add(new BesoinReseau("WIFI_INVITES", 200));
        besoins.add(new BesoinReseau("ENSEIGNANTS", 120));
        besoins.add(new BesoinReseau("LABORATOIRES", 60));
        besoins.add(new BesoinReseau("SERVEURS", 30));

        MoteurVLSM moteur = new MoteurVLSM();
        ArrayList<ResultatVLSM> resultats = moteur.genererPlan("10.10.0.0", besoins);

        GestionnaireVLAN gestionnaire = new GestionnaireVLAN();
        int id = 10;
        try {
            for (ResultatVLSM r : resultats) {
                VLAN vlan = new VLAN(id, r.getNomBesoin(), r, "VLAN " + r.getNomBesoin());
                gestionnaire.ajouterVLAN(vlan);
                id += 10;
            }
        } catch (ConflitVLANException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        System.out.println("\nPlan VLAN genere :");
        gestionnaire.afficherTousLesVLANs();

        MoteurRecommandation moteurRec = new MoteurRecommandation();
        moteurRec.ajouterRegle(new RecommandationWifiInvite());
        moteurRec.ajouterRegle(new RecommandationServeurs());
        moteurRec.ajouterRegle(new RecommandationGrandVLAN());
        moteurRec.ajouterRegle(new RecommandationAdministration()); // travail demande

        ArrayList<Recommandation> recommandations = moteurRec.analyserVLANs(gestionnaire.getVlans());
        System.out.println("\nRecommandations proposees :");
        moteurRec.afficherRecommandations(recommandations);
    }
}