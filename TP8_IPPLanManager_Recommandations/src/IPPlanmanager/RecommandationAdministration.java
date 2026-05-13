package IPPlanmanager;

/**
 *
 * 
 */

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
