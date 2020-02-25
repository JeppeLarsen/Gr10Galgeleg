package galgeleg;


import java.rmi.Naming;

public class Galgeserver {

    public static void main(String[] arg) throws Exception {

        // Enten: Kør programmet 'rmiregistry' fra mappen med .class-filerne, eller:
        java.rmi.registry.LocateRegistry.createRegistry(1099); // start i server-JVM


        //modtager bruger og adgangskode fra klienten


        //Tjek om bruger og adgangskode er korrekt
        //Hvis brugeren er autoriseret

        if (autoriserBruger("Jeppe", "Mads")) {

            Galgelogik galgelogik = new Galgelogik();
            Naming.rebind("rmi://localhost:1099/galgeleg", galgelogik);
            System.out.println("Galgeleg registreret.");

        }
    }

    public static boolean autoriserBruger(String brugernavn, String adgangskode) {

        if (brugernavn.equals("Jeppe") && adgangskode.equals("Mads")) {
            return true;

        } else {
            return false;
        }


    }
}


