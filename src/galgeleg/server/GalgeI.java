package galgeleg.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.rmi.Remote;
import java.util.ArrayList;

public interface GalgeI extends Remote, Serializable {

    ArrayList<String> getBrugteBogstaver() throws java.rmi.RemoteException;

    String getSynligtOrd() throws java.rmi.RemoteException;

    String getOrdet() throws java.rmi.RemoteException;

    int getAntalForkerteBogstaver() throws java.rmi.RemoteException;

    boolean erSidsteBogstavKorrekt() throws java.rmi.RemoteException;

    boolean erSpilletVundet() throws java.rmi.RemoteException;

    boolean erSpilletTabt() throws java.rmi.RemoteException;

    boolean erSpilletSlut() throws java.rmi.RemoteException;

    void nulstil() throws java.rmi.RemoteException;


    void gætBogstav(String bogstav) throws java.rmi.RemoteException;

    void logStatus() throws java.rmi.RemoteException;

    void hentOrdFraDr() throws java.rmi.RemoteException;

    void hentOrdFraRegneark(String sværhedsgrader) throws java.rmi.RemoteException;
}
