
package IPPlanmanager.service;

/**
 *
 * 
 */

import IPPlanmanager.console.ConsoleService; 
import IPPlanmanager.exception.AdresseIPInvalideException; 
import IPPlanmanager.exception.ChevauchementReseauException; 
import IPPlanmanager.exception.ConflitVLANException; 
import IPPlanmanager.model.BesoinReseau; 
import IPPlanmanager.model.Recommandation; 
import IPPlanmanager.model.ResultatVLSM; 
import IPPlanmanager.model.VLAN; 
import IPPlanmanager.repository.BesoinRepository;
import IPPlanmanager.repository.FichierPlanRepository; 
import java.io.IOException; 
import java.util.ArrayList; 

public class ApplicationIPPlanManager {
    private final ConsoleService console;
    private final MoteurVLSM moteurVLSM;
    private GestionnaireVLAN gestionnaireVLAN;
    private final ValidateurPlanAdressage validateur;
    private final MoteurRecommandation moteurRecommandation;
    private final FichierPlanRepository fichierRepository;
    private final BesoinRepository besoinRepository;
    private final RapportService rapportService;

    public ApplicationIPPlanManager() {
        console = new ConsoleService();
        moteurVLSM = new MoteurVLSM();
        gestionnaireVLAN = new GestionnaireVLAN();
        validateur = new ValidateurPlanAdressage();
        moteurRecommandation = new MoteurRecommandation();
        fichierRepository = new FichierPlanRepository();
        besoinRepository = new BesoinRepository();
        rapportService = new RapportService();

        // Initialiser les règles de recommandation
        moteurRecommandation.ajouterRegle(new RecommandationWifiInvite());
        moteurRecommandation.ajouterRegle(new RecommandationServeurs());
        moteurRecommandation.ajouterRegle(new RecommandationGrandVLAN());
    }

    public void demarrer() {
        boolean quitter = false;
        while (!quitter) {
            console.afficherMenu();
            int choix = console.saisirEntier("");
            switch (choix) {
                case 1 -> saisirEtGenerer();
                case 2 -> chargerDepuisCSV();
                case 3 -> {
                    quitter = true;
                    System.out.println("Au revoir !");
                }
                default -> System.out.println("Choix invalide.");
            }
        }
    }

    private void saisirEtGenerer() {
        try {
            String nomProjet = console.saisirTexte("Nom du projet reseau : ");
            String adresseDepart = console.saisirTexte("Adresse reseau de depart : ");
            CalculateurReseau.verifierAdresseIP(adresseDepart);
            ArrayList<BesoinReseau> besoins = console.saisirBesoins();
            executerGenerationComplete(nomProjet, adresseDepart, besoins);
        } catch (AdresseIPInvalideException e) {
            System.out.println("Erreur d'adresse IP : " + e.getMessage());
        }
    }

    private void chargerDepuisCSV() {
        try {
            String cheminFichier = console.saisirTexte("Chemin du fichier CSV (ex: exports/besoins.csv) : ");
            ArrayList<BesoinReseau> besoins = besoinRepository.chargerBesoins(cheminFichier);
            if (besoins.isEmpty()) {
                System.out.println("Aucun besoin trouve dans le fichier.");
                return;
            }
            String nomProjet = console.saisirTexte("Nom du projet pour les fichiers de sortie : ");
            String adresseDepart = console.saisirTexte("Adresse reseau de départ : ");
            CalculateurReseau.verifierAdresseIP(adresseDepart);
            executerGenerationComplete(nomProjet, adresseDepart, besoins);
        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier : " + e.getMessage());
        } catch (AdresseIPInvalideException e) {
            System.out.println("Erreur d'adresse IP : " + e.getMessage());
        }
    }

    private void executerGenerationComplete(String nomProjet, String adresseDepart, ArrayList<BesoinReseau> besoins) {
        try {
            // Génération VLSM
            ArrayList<ResultatVLSM> resultats = moteurVLSM.genererPlan(adresseDepart, besoins);
            if (resultats.isEmpty()) {
                System.out.println("Aucun resultat genere. Verifiez les besoins ou l'adresse.");
                return;
            }

            // Validation des adresses et chevauchements
            validateur.verifierAdresses(resultats);
            validateur.verifierChevauchements(resultats);

            // Création des VLANs
            genererVLANs(resultats);

            // Recommandations
            ArrayList<Recommandation> recommandations = moteurRecommandation.analyserVLANs(gestionnaireVLAN.getVlans());

            // Affichage des résultats
            afficherResultats(resultats, recommandations);

            // Sauvegarde
            sauvegarderResultats(nomProjet, besoins, resultats, recommandations);

            validateur.afficherValidationReussie();
            System.out.println("\nFichiers generes dans le dossier exports/");

        } catch (AdresseIPInvalideException | ChevauchementReseauException | ConflitVLANException e) {
            System.out.println("Erreur de validation : " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erreur de sauvegarde : " + e.getMessage());
        }
    }

    private void genererVLANs(ArrayList<ResultatVLSM> resultats) throws ConflitVLANException {
        gestionnaireVLAN = new GestionnaireVLAN(); // réinitialisation
        int numeroVLAN = 10;
        for (ResultatVLSM r : resultats) {
            VLAN vlan = new VLAN(numeroVLAN, r.getNomBesoin(), r, "VLAN " + r.getNomBesoin());
            gestionnaireVLAN.ajouterVLAN(vlan);
            numeroVLAN += 10;
        }
    }

    private void afficherResultats(ArrayList<ResultatVLSM> resultats, ArrayList<Recommandation> recommandations) {
        System.out.println("\n=== Plan d'adressage propose ===");
        for (ResultatVLSM r : resultats) r.afficher();
        System.out.println("\n=== VLANs generes ===");
        gestionnaireVLAN.afficherTousLesVLANs();
        System.out.println("\n=== Recommandations ===");
        moteurRecommandation.afficherRecommandations(recommandations);
    }

    private void sauvegarderResultats(String nomProjet, ArrayList<BesoinReseau> besoins,ArrayList<ResultatVLSM> resultats,
        ArrayList<Recommandation> recommandations) throws IOException {
        String base = "exports/" + nomProjet.replace(" ", "_");
        fichierRepository.sauvegarderPlanCSV(resultats, base + "_plan.csv");
        fichierRepository.sauvegarderVLANsCSV(gestionnaireVLAN.getVlans(), base + "_vlans.csv");
        fichierRepository.sauvegarderRecommandations(recommandations, base + "_recommandations.txt");
        rapportService.genererRapportComplet(besoins, resultats, gestionnaireVLAN.getVlans(), recommandations, base + "_rapport.txt");
    }
}