package ipplanmanager;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- TEST TP6 : VERIFICATION DES DOUBLONS DE VLAN ---");
        
        MoteurVLSM moteur = new MoteurVLSM();
        ArrayList<BesoinReseau> besoins = new ArrayList<>();
        
        // --- SCÉNARIO Q16 : On ajoute deux fois le service DIRECTION ---
        besoins.add(new BesoinReseau("DIRECTION", 100)); // Aura le VLAN 10
        besoins.add(new BesoinReseau("TECHNIQUE", 50));  // Aura le VLAN 20
        besoins.add(new BesoinReseau("DIRECTION", 5));   // Doit AUSSI avoir le VLAN 10 !

        ArrayList<ResultatVLSM> plan = moteur.genererPlan("192.168.1.0", besoins);
        
        for (ResultatVLSM r : plan) {
            r.afficher();
        }
    }
}