package ipplanmanager;

public class Main {
    public static void main(String[] args) {
        // Test 1 : Réseau local classique
        ReseauIP r1 = new ReseauIP("192.168.1.0", 24, "LAN Bureau");
        r1.afficher();
        System.out.println();

        // Test 2 : Réseau technique (Question 14)
        ReseauIP r2 = new ReseauIP("172.20.0.0", 16, "Serveurs Tech");
        r2.afficher();
        System.out.println();

        // Test 3 : Réseau public (Question 14)
        ReseauIP r3 = new ReseauIP("8.8.8.0", 24, "DNS Google");
        r3.afficher();
    }
}