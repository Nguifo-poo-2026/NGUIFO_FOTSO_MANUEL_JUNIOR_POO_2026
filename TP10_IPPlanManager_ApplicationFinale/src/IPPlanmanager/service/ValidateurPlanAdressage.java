package IPPlanmanager.service;

/**
 *
 * 
 */
import IPPlanmanager.exception.ConflitVLANException;
import IPPlanmanager.exception.ChevauchementReseauException;
import IPPlanmanager.exception.AdresseIPInvalideException;
//import IPPlanmanager.model.BesoinReseau;
//import IPPlanmanager.model.Recommandation;
import IPPlanmanager.model.ResultatVLSM;
//import IPPlanmanager.model.VLAN;
//import IPPlanmanager.repository.BesoinRepository;
//import IPPlanmanager.repository.FichierPlanRepository;
import java.util.ArrayList;

public class ValidateurPlanAdressage {

    public void verifierChevauchements(ArrayList<ResultatVLSM> resultats) throws ChevauchementReseauException {
        for (int i = 0; i < resultats.size(); i++) {
            ResultatVLSM r1 = resultats.get(i);
            for (int j = i + 1; j < resultats.size(); j++) {
                ResultatVLSM r2 = resultats.get(j);
                if (CalculateurReseau.reseauxSeChevauchent(
                        r1.getAdresseReseau(), r1.getCidr(),
                        r2.getAdresseReseau(), r2.getCidr())) {
                    throw new ChevauchementReseauException(
                        "Chevauchement detecte entre " + r1.getNomBesoin() + " et " + r2.getNomBesoin());
                }
            }
        }
    }

    public void verifierAdresses(ArrayList<ResultatVLSM> resultats) throws AdresseIPInvalideException {
        for (ResultatVLSM r : resultats) {
            CalculateurReseau.verifierAdresseIP(r.getAdresseReseau());
        }
    }

    public void afficherValidationReussie() {
        System.out.println("Validation terminee : aucun conflit critique detecte.");
    }
}