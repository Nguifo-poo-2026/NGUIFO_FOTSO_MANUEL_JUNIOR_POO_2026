package IPPlanmanager;

/**
 *
 * 
 */

public class CalculateurReseau {

    // Calcule le nombre d'hôtes utilisables pour un CIDR donné
    public static int calculerNombreHotes(int cidr) {
        if (cidr < 0 || cidr > 32){
            return 0;
        }
        int bitsHotes = 32 - cidr;
        if (bitsHotes == 0){
            return 1;
        }
        return (int) Math.pow(2, bitsHotes) - 2;
    }

    // Détermine le plus petit CIDR (donc le plus grand masque) capable de contenir nombreHotes
    public static int calculerCidrPourHotes(int nombreHotes) {
        for (int cidr = 32; cidr >= 0; cidr--) {
            int capacite = calculerNombreHotes(cidr);
            if (capacite >= nombreHotes) {
                return cidr;
            }
        }
        return -1;
    }

    // Retourne la classe d'adresse (A, B, C) à partir de l'adresse IP
    public static String obtenirClasseReseau(String adresseIP) {
        try {
            String[] parties = adresseIP.split("\\.");
            int premier = Integer.parseInt(parties[0]);
            if (premier >= 1 && premier <= 126) return "Classe A";
            if (premier >= 128 && premier <= 191) return "Classe B";
            if (premier >= 192 && premier <= 223) return "Classe C";
            return "Classe inconnue (multicast/reserve)";
        } catch (Exception e) {
            return "Adresse invalide";
        }
    }

    // Conversion CIDR -> masque décimal (calcul dynamique, pas de switch limité)
    public static String obtenirMasqueDecimal(int cidr) {
        int masque = 0xffffffff << (32 - cidr);
        int octet1 = (masque >>> 24) & 255;
        int octet2 = (masque >>> 16) & 255;
        int octet3 = (masque >>> 8) & 255;
        int octet4 = masque & 255;
        return octet1 + "." + octet2 + "." + octet3 + "." + octet4;
    }

    // Vérifie si une adresse IP est dans une plage privée (RFC 1918)
    public static boolean estAdressePrivee(String adresseIP) {
        try {
            String[] parties = adresseIP.split("\\.");
            int p1 = Integer.parseInt(parties[0]);
            int p2 = Integer.parseInt(parties[1]);
            if (p1 == 10) return true;
            if (p1 == 172 && p2 >= 16 && p2 <= 31) return true;
            if (p1 == 192 && p2 == 168) return true;
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    // Convertit une IP en entier (pour calculs)
    public static int convertirIpEnEntier(String ip) {
        String[] parties = ip.split("\\.");
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = (result << 8) | Integer.parseInt(parties[i]);
        }
        return result;
    }

    // Convertit un entier en IP
    public static String convertirEntierEnIp(int valeur) {
        return ((valeur >>> 24) & 255) + "." +
               ((valeur >>> 16) & 255) + "." +
               ((valeur >>> 8) & 255) + "." +
               (valeur & 255);
    }

    // Taille du bloc (nombre total d'adresses) pour un CIDR donné
    public static int calculerTailleBloc(int cidr) {
        return (int) Math.pow(2, 32 - cidr);
    }

    // Première adresse utilisable = adresse réseau + 1
    public static String calculerPremiereAdresseUtilisable(String adresseReseau) {
        int ipInt = convertirIpEnEntier(adresseReseau);
        return convertirEntierEnIp(ipInt + 1);
    }

    // Dernière adresse utilisable = adresse broadcast - 1
    public static String calculerDerniereAdresseUtilisable(String adresseReseau, int cidr) {
        int ipInt = convertirIpEnEntier(adresseReseau);
        int tailleBloc = calculerTailleBloc(cidr);
        int broadcastInt = ipInt + tailleBloc - 1;
        return convertirEntierEnIp(broadcastInt - 1);
    }
    
    public static boolean estAdresseIPValide(String ip) { 
    if (ip == null || ip.isEmpty()) { 
        return false; 
    } 
    String[] parties = ip.split("\\."); 
    if (parties.length != 4) { 
        return false; 
    } 
    for (String partie : parties) { 
        try { 
            int valeur = Integer.parseInt(partie); 
            if (valeur < 0 || valeur > 255) { 
                return false; 
            } 
        } catch (NumberFormatException e) { 
            return false; 
        } 
    } 
    return true; 
} 
 
    public static void verifierAdresseIP(String ip) throws AdresseIPInvalideException { 
        if (!estAdresseIPValide(ip)) { 
            throw new AdresseIPInvalideException("Adresse IP invalide : " + ip); 
        } 
    } 
    public static int calculerAdresseFin(String adresseReseau, int cidr) { 
        int debut = convertirIpEnEntier(adresseReseau); 
        int tailleBloc = calculerTailleBloc(cidr); 
        return debut + tailleBloc - 1; 
    } 
    public static boolean reseauxSeChevauchent(String adresse1, int cidr1, String adresse2, int cidr2) { 
        int debut1 = convertirIpEnEntier(adresse1); 
        int fin1 = calculerAdresseFin(adresse1, cidr1); 
        int debut2 = convertirIpEnEntier(adresse2); 
        int fin2 = calculerAdresseFin(adresse2, cidr2); 
        return debut1 <= fin2 && debut2 <= fin1; 
    } 
}
