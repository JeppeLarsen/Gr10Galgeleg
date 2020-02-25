package galgeleg;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Brugerautorisation extends UnicastRemoteObject implements BrugerautorisationI {

    protected Brugerautorisation() throws RemoteException {
    }

    private boolean erBrugerAutoriseret;

    public boolean erBrugerAutoriseret() {
        return erBrugerAutoriseret;
    }


    @Override
    public void autoriserBruger(String brugernavn, String adgangskode) throws Exception {


        if (brugernavn.equals("Jeppe") && adgangskode.equals("Mads")) {
            erBrugerAutoriseret = true;


            System.out.println("Bruger er autoriseret!");

            Galgelogik galgelogik = new Galgelogik();
            Naming.rebind("rmi://localhost:1099/galgeleg", galgelogik);
            System.out.println("Galgeleg registreret.");



        } else {
            erBrugerAutoriseret = false;
        }

    }


}
