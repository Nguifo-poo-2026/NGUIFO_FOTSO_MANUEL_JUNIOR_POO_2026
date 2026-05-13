package ipplanmanager;

public class CalculateurReseau {

    // Calcule le nombre d'hôtes : 2^(32-CIDR) - 2
    public static int calculerNombreHotes(int cidr) {
        if (cidr < 0 || cidr > 32) return 0;
        return (int) Math.pow(2, 32 - cidr) - 2;
    }

    // Détermine la classe A, B ou C
    public static String obtenirClasseReseau(String adresseIP) {
        try {
            String[] octets = adresseIP.split("\\.");
            int premier = Integer.parseInt(octets[0]);
            if (premier >= 1 && premier <= 126) return "Classe A";
            if (premier >= 128 && premier <= 191) return "Classe B";
            if (premier >= 192 && premier <= 223) return "Classe C";
            return "Inconnue";
        } catch (Exception e) {
            return "Erreur format";
        }
    }

    // Convertit le CIDR (ex: 24) en masque (ex: 255.255.255.0)
    public static String obtenirMasqueDecimal(int cidr) {
        switch (cidr) {
            case 8: return "255.0.0.0";
            case 16: return "255.255.0.0";
            case 24: return "255.255.255.0";
            case 28: return "255.255.255.240";
            default: return "Masque spécifique";
        }
    }

    // QUESTION 15 : Détection des plages privées
    public static boolean estReseauPrive(String adresseIP) {
        String[] octets = adresseIP.split("\\.");
        int o1 = Integer.parseInt(octets[0]);
        int o2 = Integer.parseInt(octets[1]);

        if (o1 == 10) return true; // Classe A privée
        if (o1 == 172 && (o2 >= 16 && o2 <= 31)) return true; // Classe B privée
        if (o1 == 192 && o2 == 168) return true; // Classe C privée
        return false;
    }
}