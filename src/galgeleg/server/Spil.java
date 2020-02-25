package galgeleg.server;

import brugerautorisation.transport.rmi.Brugeradmin;
import galgeleg.logik.Galgelogik;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Spil extends UnicastRemoteObject implements SpilI{

    protected Spil() throws RemoteException {
    }

    private boolean erBrugerAutoriseret;

    public boolean erBrugerAutoriseret() {
        return erBrugerAutoriseret;
    }


    @Override
    public void startSpil(String brugernavn, String adgangskode) throws Exception {

        Brugeradmin ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");

        try {
            ba.hentBruger(brugernavn, adgangskode);

            erBrugerAutoriseret = true;

            System.out.println("Bruger er autoriseret!");

            Galgelogik galgelogik = new Galgelogik();
            Naming.rebind("rmi://localhost:1099/galgeleg", galgelogik);
            System.out.println("Galgeleg registreret.");


        } catch (Exception e) {
            System.out.println("Bruger ikke autoriseret");
            erBrugerAutoriseret = false;

        }


    }


}
