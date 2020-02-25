package galgeleg;


import java.rmi.Naming;

public class Galgeserver {

    public static void main(String[] arg) throws Exception {
        // Enten: Kør programmet 'rmiregistry' fra mappen med .class-filerne, eller:
        java.rmi.registry.LocateRegistry.createRegistry(1099); // start i server-JVM

        Galgelogik galgelogik = new Galgelogik();
        Naming.rebind("rmi://localhost:1099/galgeleg", galgelogik);
        System.out.println("Kontotjeneste registreret.");

    }

}
