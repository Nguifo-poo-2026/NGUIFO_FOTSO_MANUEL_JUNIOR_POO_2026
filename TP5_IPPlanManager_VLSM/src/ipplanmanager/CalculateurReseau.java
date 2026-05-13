package ipplanmanager;

public class CalculateurReseau {

    public static int calculerNombreHotes(int cidr) {
        if (cidr < 0 || cidr > 32) return 0;
        int bitsHotes = 32 - cidr;
        if (bitsHotes == 0) return 1;
        return (int) Math.pow(2, bitsHotes) - 2;
    }

    public static int calculerCidrPourHotes(int nombreHotes) {
        for (int cidr = 32; cidr >= 0; cidr--) {
            if (calculerNombreHotes(cidr) >= nombreHotes) return cidr;
        }
        return -1;
    }

    public static String obtenirMasqueDecimal(int cidr) {
        int masque = 0xffffffff << (32 - cidr);
        return convertirEntierEnIp(masque);
    }

    public static int convertirIpEnEntier(String ip) {
        String[] parties = ip.split("\\.");
        int resultat = 0;
        for (int i = 0; i < 4; i++) {
            resultat = resultat * 256 + Integer.parseInt(parties[i]);
        }
        return resultat;
    }

    public static String convertirEntierEnIp(int valeur) {
        return ((valeur >>> 24) & 255) + "." + ((valeur >>> 16) & 255) + "." + 
               ((valeur >>> 8) & 255) + "." + (valeur & 255);
    }

    public static int calculerTailleBloc(int cidr) {
        return (int) Math.pow(2, 32 - cidr);
    }

    // --- QUESTION 16 : NOUVELLES MÉTHODES ---
    public static String calculerPremiereIp(String adresseReseau) {
        int ipInt = convertirIpEnEntier(adresseReseau);
        return convertirEntierEnIp(ipInt + 1);
    }

    public static String calculerDerniereIp(String adresseReseau, int cidr) {
        int ipInt = convertirIpEnEntier(adresseReseau);
        int taille = calculerTailleBloc(cidr);
        // Adresse de Broadcast - 1
        return convertirEntierEnIp(ipInt + taille - 2);
    }
}