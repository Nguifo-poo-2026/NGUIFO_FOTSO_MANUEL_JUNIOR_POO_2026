package ipplanmanager;

import java.util.ArrayList;
import java.util.Collections;

public class MoteurVLSM {
    // --- AJOUT Q15 : Création du gestionnaire de VLAN ---
    private GestionnaireVLAN gestionnaireVlan = new GestionnaireVLAN();

    public ArrayList<ResultatVLSM> genererPlan(String adresseDepart, ArrayList<BesoinReseau> besoins) {
        ArrayList<ResultatVLSM> resultats = new ArrayList<>();
        
        // Tri des besoins (plus gros en premier)
        Collections.sort(besoins, (b1, b2) -> b2.getNombreHotes() - b1.getNombreHotes());

        int adresseActuelle = CalculateurReseau.convertirIpEnEntier(adresseDepart);

        for (BesoinReseau besoin : besoins) {
            int cidr = CalculateurReseau.calculerCidrPourHotes(besoin.getNombreHotes());
            String ipReseau = CalculateurReseau.convertirEntierEnIp(adresseActuelle);
            String masque = CalculateurReseau.obtenirMasqueDecimal(cidr);
            String premiere = CalculateurReseau.calculerPremiereIp(ipReseau);
            String derniere = CalculateurReseau.calculerDerniereIp(ipReseau, cidr);
            
            // --- AJOUT Q15 : On demande un VLAN pour ce service au gestionnaire ---
            VLAN vlan = gestionnaireVlan.obtenirOuCreerVLAN(besoin.getNom());

            // On ajoute le VLAN vlan à la fin du constructeur
            resultats.add(new ResultatVLSM(besoin.getNom(), ipReseau, cidr, masque, 
                                           besoin.getNombreHotes(), premiere, derniere, vlan));
            
            adresseActuelle += CalculateurReseau.calculerTailleBloc(cidr);
        }
        return resultats;
    }
}