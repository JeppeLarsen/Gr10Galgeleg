package galgeleg.server;

import brugerautorisation.data.Bruger;
import brugerautorisation.transport.rmi.Brugeradmin;
import galgeleg.logik.Galgelogik;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Spil extends UnicastRemoteObject implements SpilI {

    protected Spil() throws RemoteException {
    }

    private boolean erBrugerAutoriseret;

    public boolean erBrugerAutoriseret() {
        return erBrugerAutoriseret;
    }


    @Override
    public String startSpil(String brugernavn, String adgangskode) throws RemoteException, NotBoundException, MalformedURLException {

        Brugeradmin ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");

        String valideretBrugernavn = "ILLEGAL USER. ALARM SET TO DEFCON 1";

        try {
            Bruger bruger = ba.hentBruger(brugernavn, adgangskode);

            erBrugerAutoriseret = true;

            valideretBrugernavn = bruger.brugernavn;

            System.out.println("Bruger er autoriseret!");

            Galgelogik galgelogik = new Galgelogik();
            Naming.rebind("rmi://localhost:1099/galgeleg", galgelogik);
            System.out.println("Galgeleg registreret.");


        } catch (Exception e) {
            System.out.println("Bruger ikke autoriseret");
            erBrugerAutoriseret = false;
            Naming.unbind("rmi://localhost:1099/galgeleg");
        }
        return valideretBrugernavn;

    }


}
