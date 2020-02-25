package galgeleg.server;

public interface SpilI extends java.rmi.Remote {


    void startSpil(String brugernavn, String adgangskode) throws Exception;
}
