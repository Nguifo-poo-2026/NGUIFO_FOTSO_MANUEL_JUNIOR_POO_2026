package IPPlanmanager.service;

/**
 *
 * 
 */

//import IPPlanmanager.exception.ConflitVLANException;
//import IPPlanmanager.model.BesoinReseau;
import IPPlanmanager.model.Recommandation;
//import IPPlanmanager.model.ResultatVLSM;
import IPPlanmanager.model.VLAN;
//import IPPlanmanager.repository.BesoinRepository;
//import IPPlanmanager.repository.FichierPlanRepository;
public class RecommandationAdministration implements RegleRecommandation {
    @Override
    public Recommandation analyser(VLAN vlan) {
        String nom = vlan.getNom().toUpperCase();
        if (nom.contains("ADMIN") || nom.contains("ADMINISTRATION")) {
            return new Recommandation(
                "Acces restreint à l'administration",
                "HAUTE",
                "Le VLAN " + vlan.getNom() + " doit etre accessible uniquement aux administrateurs reseau."
            );
        }
        return null;
    }
}
