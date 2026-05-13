package ipplanmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MoteurVLSM {
    public ArrayList<ResultatVLSM> genererPlan(String adresseDepart, ArrayList<BesoinReseau> besoins) {
        ArrayList<ResultatVLSM> resultats = new ArrayList<>();

        // Tri décroissant obligatoire pour le VLSM
        Collections.sort(besoins, new Comparator<BesoinReseau>() {
            @Override
            public int compare(BesoinReseau b1, BesoinReseau b2) {
                return b2.getNombreHotes() - b1.getNombreHotes();
            }
        });

        int adresseActuelle = CalculateurReseau.convertirIpEnEntier(adresseDepart);

        for (BesoinReseau besoin : besoins) {
            int cidr = CalculateurReseau.calculerCidrPourHotes(besoin.getNombreHotes());
            String masque = CalculateurReseau.obtenirMasqueDecimal(cidr);
            int capacite = CalculateurReseau.calculerNombreHotes(cidr);
            String ipReseau = CalculateurReseau.convertirEntierEnIp(adresseActuelle);

            // Question 16 : Calcul de la plage
            String premiere = CalculateurReseau.calculerPremiereIp(ipReseau);
            String derniere = CalculateurReseau.calculerDerniereIp(ipReseau, cidr);

            resultats.add(new ResultatVLSM(besoin.getNom(), ipReseau, cidr, masque, capacite, premiere, derniere));

            // On décale l'adresse pour le prochain sous-réseau
            adresseActuelle += CalculateurReseau.calculerTailleBloc(cidr);
        }
        return resultats;
    }
}