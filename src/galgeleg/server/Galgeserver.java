package galgeleg.server;


import java.rmi.Naming;

public class Galgeserver {


    public static void main(String[] arg) throws Exception {

        // Enten: Kør programmet 'rmiregistry' fra mappen med .class-filerne, eller:
        java.rmi.registry.LocateRegistry.createRegistry(9985); // start i server-JVM


        Spil spil = new Spil();
        Naming.rebind("rmi://localhost:9985/spil", spil);


    }


}


