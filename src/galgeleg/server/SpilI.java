package galgeleg.server;

public interface SpilI extends java.rmi.Remote {


    String startSpil(String brugernavn, String adgangskode) throws Exception;
}
