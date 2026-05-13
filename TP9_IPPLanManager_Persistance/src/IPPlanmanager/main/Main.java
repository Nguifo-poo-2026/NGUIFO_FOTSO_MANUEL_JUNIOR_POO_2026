
package IPPlanmanager.main;

/**
 *
 * 
 */

import IPPlanmanager.exception.ConflitVLANException; 
import IPPlanmanager.model.BesoinReseau; 
import IPPlanmanager.model.Recommandation; 
import IPPlanmanager.model.ResultatVLSM; 
import IPPlanmanager.model.VLAN; 
import IPPlanmanager.repository.BesoinRepository; 
import IPPlanmanager.repository.FichierPlanRepository; 
import IPPlanmanager.service.GestionnaireVLAN; 
import IPPlanmanager.service.MoteurRecommandation; 
import IPPlanmanager.service.MoteurVLSM; 
import IPPlanmanager.service.RapportService; 
import IPPlanmanager.service.RecommandationGrandVLAN; 
import IPPlanmanager.service.RecommandationServeurs; 
import IPPlanmanager.service.RecommandationWifiInvite; 
import java.io.File;
import java.io.IOException; 
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("===== IPPlan-Manager : TP9 - Persistance =====\n");

        // Vérification que le dossier exports existe
        File exports = new File("exports");
        if (!exports.exists()) {
            System.out.println("Création du dossier exports/");
            exports.mkdir();
        }

        // Scénario IRT
        traiterScenario("exports/besoins.csv",
                "exports/plan_adressage.csv",
                "exports/vlans.csv",
                "exports/recommandations.txt",
                "exports/rapport_complet.txt",
                "10.10.0.0",
                "Campus IRT");

        // Scénario PME (travail demandé)
        traiterScenario("exports/besoins_pme.csv",
                "exports/plan_adressage_pme.csv",
                "exports/vlans_pme.csv",
                "exports/recommandations_pme.txt",
                "exports/rapport_pme.txt",
                "192.168.1.0",
                "PME");

        // Travail supplémentaire : sauvegarder des besoins
        try {
            BesoinRepository repo = new BesoinRepository();
            ArrayList<BesoinReseau> besoinsExemple = new ArrayList<>();
            besoinsExemple.add(new BesoinReseau("TEST1", 100));
            besoinsExemple.add(new BesoinReseau("TEST2", 50));
            repo.sauvegarderBesoins(besoinsExemple, "exports/besoins_sauvegardes.csv");
            System.out.println("\n Travail supplémentaire : exports/besoins_sauvegardes.csv créé.");
        } catch (IOException e) {
            System.out.println("Erreur sauvegarde besoins : " + e.getMessage());
        }
    }

    private static void traiterScenario(String fichierBesoins,
                                        String fichierPlan,
                                        String fichierVlans,
                                        String fichierRecommandations,
                                        String fichierRapport,
                                        String adresseDepart,
                                        String nomScenario) {
        System.out.println("\n=== " + nomScenario + " ===");
        System.out.println("Fichier d'entree : " + fichierBesoins);
        System.out.println("Adresse de départ : " + adresseDepart);

        // Vérifier l'existence du fichier d'entrée
        File f = new File(fichierBesoins);
        if (!f.exists()) {
            System.out.println("Fichier introuvable : " + fichierBesoins);
            System.out.println("Creez-le avec le contenu approprie (voir consignes).");
            return;
        }

        BesoinRepository besoinRepository = new BesoinRepository();
        FichierPlanRepository fichierRepository = new FichierPlanRepository();
        RapportService rapportService = new RapportService();

        try {
            // 1. Charger les besoins
            ArrayList<BesoinReseau> besoins = besoinRepository.chargerBesoins(fichierBesoins);
            if (besoins.isEmpty()) {
                System.out.println("Aucun besoin charge. Verifiez le format du CSV (Nom;Hotes)");
                return;
            }
            System.out.println(" " + besoins.size() + " besoin(s) charge(s) :");
            for (BesoinReseau b : besoins) {
                System.out.println("   " + b.getNom() + " : " + b.getNombreHotes() + " hotes");
            }

            // 2. Générer le plan VLSM
            MoteurVLSM moteur = new MoteurVLSM();
            ArrayList<ResultatVLSM> resultats = moteur.genererPlan(adresseDepart, besoins);
            if (resultats.isEmpty()) {
                System.out.println("Aucun résultat VLSM genere. Verifiez l'adresse de depart ou les besoins.");
                return;
            }
            System.out.println("Plan VLSM genere (" + resultats.size() + " sous-réseaux) :");
            for (ResultatVLSM r : resultats) {
                System.out.println("   " + r.getNomBesoin() + " -> " + r.getAdresseReseau() + "/" + r.getCidr() +
                        " (capacite: " + r.getCapacite() + " hotes)");
            }

            // 3. Créer les VLANs
            GestionnaireVLAN gestionnaire = new GestionnaireVLAN();
            int numeroVLAN = 10;
            for (ResultatVLSM r : resultats) {
                VLAN vlan = new VLAN(numeroVLAN, r.getNomBesoin(), r, "VLAN " + r.getNomBesoin());
                gestionnaire.ajouterVLAN(vlan);
                numeroVLAN += 10;
            }
            System.out.println(" " + gestionnaire.getVlans().size() + " VLAN(s) cree(s).");

            // 4. Générer les recommandations
            MoteurRecommandation moteurRec = new MoteurRecommandation();
            moteurRec.ajouterRegle(new RecommandationWifiInvite());
            moteurRec.ajouterRegle(new RecommandationServeurs());
            moteurRec.ajouterRegle(new RecommandationGrandVLAN());
            ArrayList<Recommandation> recommandations = moteurRec.analyserVLANs(gestionnaire.getVlans());

            // 5. Sauvegarder les fichiers
            fichierRepository.sauvegarderPlanCSV(resultats, fichierPlan);
            fichierRepository.sauvegarderVLANsCSV(gestionnaire.getVlans(), fichierVlans);
            fichierRepository.sauvegarderRecommandations(recommandations, fichierRecommandations);
            rapportService.genererRapportComplet(besoins, resultats, gestionnaire.getVlans(), recommandations, fichierRapport);

            System.out.println("Fichiers generes avec succes :");
            System.out.println("   " + fichierPlan);
            System.out.println("   " + fichierVlans);
            System.out.println("   " + fichierRecommandations);
            System.out.println("   " + fichierRapport);

        } catch (IOException e) {
            System.out.println("Erreur d'entree/sortie : " + e.getMessage()); e.printStackTrace();
        } catch (ConflitVLANException e) {
            System.out.println("Conflit VLAN : " + e.getMessage());
        }
    }
}