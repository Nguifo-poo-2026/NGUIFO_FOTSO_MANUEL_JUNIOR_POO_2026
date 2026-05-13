package IPPlanmanager.repository;

/**
 *
 * 
 */

import IPPlanmanager.model.ResultatVLSM; 
import IPPlanmanager.model.VLAN; 
import IPPlanmanager.model.Recommandation; 
import java.io.FileWriter; 
import java.io.IOException; 
import java.util.ArrayList; 
public class FichierPlanRepository { 
        public void sauvegarderPlanCSV(ArrayList<ResultatVLSM> resultats, String cheminFichier) throws IOException { 
            try (FileWriter writer = new FileWriter(cheminFichier)) {
                writer.write("Nom;AdresseReseau;CIDR;Capacite\n");
                for (ResultatVLSM r : resultats) {
                    writer.write(r.getNomBesoin() + ";" + r.getAdresseReseau() + ";"
                            + r.getCidr() + ";"
                            + r.getCapacite() + "\n");
                }
            } 
        } 
        public void sauvegarderVLANsCSV(ArrayList<VLAN> vlans, String cheminFichier) throws IOException { 
            try (FileWriter writer = new FileWriter(cheminFichier)) {
                writer.write("ID;Nom;AdresseReseau;CIDR;Capacite\n");
                for (VLAN v : vlans) {
                    if (v.getReseauAssocie() != null) {
                        writer.write(v.getId() + ";" + v.getNom() + ";" + v.getReseauAssocie().getAdresseReseau() + ";"
                                + v.getReseauAssocie().getCidr() + ";"
                                + v.getReseauAssocie().getCapacite() + "\n");
                    }
                }
            } 
        } 
        public void sauvegarderRecommandations(ArrayList<Recommandation> recommandations, String cheminFichier) throws IOException { 
            try (FileWriter writer = new FileWriter(cheminFichier)) {
                writer.write("===== RECOMMANDATIONS IPPLAN-MANAGER =====\n\n");
                if (recommandations.isEmpty()) {
                    writer.write("Aucune recommandation particulière.\n");
                } else {
                    for (Recommandation r : recommandations) {
                        writer.write("[" + r.getPriorite() + "] " + r.getTitre() + " : "
                                + r.getMessage() + "\n");
                    }
                }
            } 
        } 
} 