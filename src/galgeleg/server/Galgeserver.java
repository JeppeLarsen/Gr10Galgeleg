package galgeleg.server;


import java.rmi.Naming;

public class Galgeserver {


    public static void main(String[] arg) throws Exception {

        // Enten: Kør programmet 'rmiregistry' fra mappen med .class-filerne, eller:
        java.rmi.registry.LocateRegistry.createRegistry(1099); // start i server-JVM


        Spil spil = new Spil();
        Naming.rebind("rmi://ec2-18-189-32-7.us-east-2.compute.amazonaws.com:1099/spil", spil);


    }


}


