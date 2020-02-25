package galgeleg;


import java.rmi.Naming;

public class Galgeserver {

    public static void main(String[] arg) throws Exception {
        // Enten: Kør programmet 'rmiregistry' fra mappen med .class-filerne, eller:
        java.rmi.registry.LocateRegistry.createRegistry(22022); // start i server-JVM

        KontoImpl k = new KontoImpl();
        Naming.rebind("rmi://localhost:1099/kontotjeneste", k);
        System.out.println("Kontotjeneste registreret.");
        k.overførsel(117);
        k.overførsel(1023);
    }

}
