package galgeleg;

public interface BrugerautorisationI extends java.rmi.Remote {


    void autoriserBruger(String brugernavn, String adgangskode) throws Exception;
}
