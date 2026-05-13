package ipplanmanager;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        MoteurVLSM moteur = new MoteurVLSM();

        // SCÉNARIO 1 : Exemple du document
        ArrayList<BesoinReseau> s1 = new ArrayList<>();
        s1.add(new BesoinReseau("TECHNIQUE", 120));
        s1.add(new BesoinReseau("WIFI", 80));
        s1.add(new BesoinReseau("ADMINISTRATION", 50));
        s1.add(new BesoinReseau("SERVEURS", 20));
        s1.add(new BesoinReseau("DIRECTION", 10));
        lancerTest("SCÉNARIO 1 : Entreprise Type", "192.168.1.0", s1, moteur);

        // SCÉNARIO 2 : Petite Entreprise (Question 15)
        ArrayList<BesoinReseau> s2 = new ArrayList<>();
        s2.add(new BesoinReseau("ADMIN", 25));
        s2.add(new BesoinReseau("COMPTABILITE", 12));
        s2.add(new BesoinReseau("WIFI_INVITES", 40));
        s2.add(new BesoinReseau("SERVEURS", 8));
        lancerTest("SCÉNARIO 2 : Petite Entreprise", "10.0.0.0", s2, moteur);

        // SCÉNARIO 3 : Campus (Question 15)
        ArrayList<BesoinReseau> s3 = new ArrayList<>();
        s3.add(new BesoinReseau("ETUDIANTS", 500));
        s3.add(new BesoinReseau("PERSONNEL", 120));
        s3.add(new BesoinReseau("LABORATOIRE", 60));
        s3.add(new BesoinReseau("WIFI PUBLIC", 200));
        lancerTest("SCÉNARIO 3 : Campus Universitaire", "172.16.0.0", s3, moteur);
    }

    public static void lancerTest(String titre, String ip, ArrayList<BesoinReseau> besoins, MoteurVLSM m) {
        System.out.println("\n=== " + titre + " ===");
        ArrayList<ResultatVLSM> plan = m.genererPlan(ip, besoins);
        for (ResultatVLSM r : plan) {
            r.afficher();
        }
    }
}